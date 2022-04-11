package com.site.admin;

import com.site.admin.dto.MemberListNumberingDto;
import com.site.admin.vo.AdminMemberPageVo;
import com.site.admin.vo.ChartListVo;

public interface AdminService {

	MemberListNumberingDto memberList(AdminMemberPageVo memberListVo);

	int deleteMember(String id);

	ChartListVo viewOrderChart();
	
	ChartListVo viewOrderPeriod(String datepicker1, String datepicker2);
	
}
