package com.site.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.site.vo.ChartVo;
import com.site.vo.MemberVo;

@Mapper
public interface AdminMapper {

	int countMemberList(String category, String order, String searchWord);

	ArrayList<MemberVo> memberList(int startRow, int endRow, String category, String order, String searchWord);

	int deleteMember(String id);

	ArrayList<ChartVo> viewOrder();

	ArrayList<ChartVo> tableList();

	ArrayList<ChartVo> summaryTable();
	
	ArrayList<ChartVo> findOrderView(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findTableList(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findTableSummary(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findWeekOrderView(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findMonthOrderView(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findThreeMonthOrderView(String datepicker1, String datepicker2);

	ArrayList<ChartVo> findYearOrderView(String datepicker1, String datepicker2);
}
