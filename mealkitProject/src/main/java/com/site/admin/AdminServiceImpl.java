package com.site.admin;

import java.util.ArrayList;

import com.site.admin.dto.MemberListNumberingDto;
import com.site.admin.vo.AdminMemberPageVo;
import com.site.admin.vo.ChartListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.site.vo.ChartVo;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final AdminMapper adminMapper;

	@Override
	public MemberListNumberingDto memberList(AdminMemberPageVo adminMemberListVo) {
		return new MemberListNumberingDto(adminMemberListVo,20,5, adminMapper);
	}

	@Override
	public int deleteMember(String id) {
		return adminMapper.deleteMember(id);
	} 

	@Override
	public ChartListVo viewOrderChart() {
		return new ChartListVo(adminMapper.viewOrder(),adminMapper.tableList(),adminMapper.summaryTable());
	}

	@Override
	public ChartListVo viewOrderPeriod(String datepicker1, String datepicker2) {
		int intDatepicker1 = Integer.parseInt(datepicker1);
		int intDatepicker2 = Integer.parseInt(datepicker2);

		if(intDatepicker1 == intDatepicker2) {
			return findOrderPeriod(datepicker1,datepicker2,"day");
		}
		if(intDatepicker2-intDatepicker1 <= 7 && intDatepicker2-intDatepicker1 > 0){
			return findOrderPeriod(datepicker1,datepicker2,"week");
		}
		if(intDatepicker2-intDatepicker1 <= 100 && intDatepicker2-intDatepicker1 > 7) {
			return findOrderPeriod(datepicker1,datepicker2,"month");
		}
		if(intDatepicker2-intDatepicker1 <= 300 && intDatepicker2-intDatepicker1 > 100) {
			return findOrderPeriod(datepicker1,datepicker2,"threeMonth");
		}
		if(intDatepicker2-intDatepicker1 <= 10000 && intDatepicker2-intDatepicker1 > 300) {
			return findOrderPeriod(datepicker1,datepicker2,"year");
		}
		return findOrderPeriod(datepicker1,datepicker2,"default");
	}

	public ChartListVo findOrderPeriod(String datepicker1, String datepicker2, String period){

		datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4);
		datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7);
		datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4);
		datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7);

		return new ChartListVo(defineOrderPeriod(datepicker1,datepicker2,period)
				,adminMapper.findTableList(datepicker1,datepicker2)
				,adminMapper.findTableSummary(datepicker1,datepicker2));
	}

	public ArrayList<ChartVo> defineOrderPeriod(String datepicker1, String datepicker2, String period){
		ArrayList<ChartVo> chartVoList;
		switch (period){
			case "day":
				chartVoList = adminMapper.findOrderView(datepicker1,datepicker2);
				break;
			case "week":
				chartVoList = adminMapper.findWeekOrderView(datepicker1,datepicker2);
				break;
			case "month":
				chartVoList = adminMapper.findMonthOrderView(datepicker1,datepicker2);
				break;
			case "threeMonth":
				chartVoList = adminMapper.findThreeMonthOrderView(datepicker1,datepicker2);
				break;
			case "year":
				chartVoList = adminMapper.findYearOrderView(datepicker1,datepicker2);
				break;
			default:
				chartVoList= adminMapper.viewOrder();
		}
		return chartVoList;
	}

}
