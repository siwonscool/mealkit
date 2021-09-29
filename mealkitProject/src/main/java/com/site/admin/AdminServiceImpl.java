package com.site.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.vo.ChartVo;
import com.site.vo.MemberVo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

//	회원정보 리스트 출력
	@Override
	public Map<String, Object> memberList(int page, String category, String order, String searchWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		
//		회원정보 리스트 카운트
		int listCount = adminMapper.memberListCount(category, order, searchWord);
		int limit = 20;
		int numlimit= 5;
		int maxPage = (int)((double)listCount/limit+0.9999);
		int startPage = (((int)((double)page/numlimit+0.9999))-1)*numlimit+1;
		int endPage = startPage+numlimit-1;
		if(endPage>maxPage) endPage = maxPage;
		
		int startRow = (page-1)*limit+1;
		int endRow = startRow +limit-1;
		
		ArrayList<MemberVo> list = adminMapper.memberList(startRow, endRow, category, order, searchWord);
		if(list.isEmpty()) {
			map.put("flag", 1);
		}
		map.put("listCount", listCount);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		map.put("category", category);
		map.put("order", order);
		map.put("searchWord", searchWord);
		
		return map;
	}

//	회원정보 삭제
	@Override
	public int memberDelete(String id) {
		int result = adminMapper.memberDelete(id);
		return result;
	} 
	//차트
	@Override
	public Map<String, Object> orderView() {
		Map<String, Object> orderView = new HashMap<String, Object>();
		ArrayList<ChartVo> list = adminMapper.orderView();
		ArrayList<ChartVo> tableList = adminMapper.tableList();
		ArrayList<ChartVo> tableSummary = adminMapper.tableSummary();
		orderView.put("list", list);
		orderView.put("tableList", tableList);
		orderView.put("tableSummary", tableSummary);
		return orderView;
	}
	//검색부분
	@Override
	public Map<String, Object> orderSelectView(String datepicker1, String datepicker2) {
		Map<String, Object> orderView = new HashMap<String, Object>();
		//비교해야해서 int형으로 바꿔줌
		int a = Integer.parseInt(datepicker1);
		int b = Integer.parseInt(datepicker2);
		//당일 ㄱㄱ
		if(a == b) {
			//비교했으니 다시 String 으로 바꿔줌
			datepicker1 = String.valueOf(a);
			datepicker2 = String.valueOf(b);
			//바꿔줬으니 다시 날짜중간에 - 넣음 
			datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4,datepicker1.length());
			datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7,datepicker1.length());
			datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4,datepicker2.length());
			datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7,datepicker2.length());
			//당일
			ArrayList<ChartVo> list = adminMapper.orderSelectView(datepicker1,datepicker2);
			ArrayList<ChartVo> tableList = adminMapper.tableSelectList(datepicker1,datepicker2);
			ArrayList<ChartVo> tableSummary = adminMapper.tableSelectSummary(datepicker1,datepicker2);
			orderView.put("list", list);
			orderView.put("tableList", tableList);
			orderView.put("tableSummary", tableSummary);
			System.out.println("당일까지의 매출");
			return orderView;
			}//if
		
		//1주일 ㄱㄱ
		if(b-a <= 7 && b-a > 0){
			//비교했으니 다시 String 으로 바꿔줌
			datepicker1 = String.valueOf(a);
			datepicker2 = String.valueOf(b);
			//바꿔줬으니 다시 날짜중간에 - 넣음 
			datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4,datepicker1.length());
			datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7,datepicker1.length());
			datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4,datepicker2.length());
			datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7,datepicker2.length());
			//1주일
			ArrayList<ChartVo> list = adminMapper.orderSelectWeekView(datepicker1,datepicker2);
			ArrayList<ChartVo> tableList = adminMapper.tableSelectList(datepicker1,datepicker2);
			ArrayList<ChartVo> tableSummary = adminMapper.tableSelectSummary(datepicker1,datepicker2);
			orderView.put("list", list);
			orderView.put("tableList", tableList);
			orderView.put("tableSummary", tableSummary);
			System.out.println("최근1주일까지의 매출");
			return orderView;
		//1개월 ㄱㄱ
		}if(b-a <= 100 && b-a > 7) {
			//비교했으니 다시 String 으로 바꿔줌
			datepicker1 = String.valueOf(a);
			datepicker2 = String.valueOf(b);
			//바꿔줬으니 다시 날짜중간에 - 넣음 
			datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4,datepicker1.length());
			datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7,datepicker1.length());
			datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4,datepicker2.length());
			datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7,datepicker2.length());
			//1개월
			ArrayList<ChartVo> list = adminMapper.orderSelectOmonthView(datepicker1,datepicker2);
			ArrayList<ChartVo> tableList = adminMapper.tableSelectList(datepicker1,datepicker2);
			ArrayList<ChartVo> tableSummary = adminMapper.tableSelectSummary(datepicker1,datepicker2);
			orderView.put("list", list);
			orderView.put("tableList", tableList);
			orderView.put("tableSummary", tableSummary);
			System.out.println("최근1개월까지의 매출");
			return orderView;
		//3개월 ㄱㄱ
		}if(b-a <= 300 && b-a > 100) {
			//비교했으니 다시 String 으로 바꿔줌
			datepicker1 = String.valueOf(a);
			datepicker2 = String.valueOf(b);
			//바꿔줬으니 다시 날짜중간에 - 넣음 
			datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4,datepicker1.length());
			datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7,datepicker1.length());
			datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4,datepicker2.length());
			datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7,datepicker2.length());
			//3개월
			ArrayList<ChartVo> list = adminMapper.orderSelectTmonthView(datepicker1,datepicker2);
			ArrayList<ChartVo> tableList = adminMapper.tableSelectList(datepicker1,datepicker2);
			ArrayList<ChartVo> tableSummary = adminMapper.tableSelectSummary(datepicker1,datepicker2);
			orderView.put("list", list);
			orderView.put("tableList", tableList);
			orderView.put("tableSummary", tableSummary);
			System.out.println("최근3개월까지의 매출");
			return orderView;
		//12개월 ㄱㄱ
		}if(b-a <= 10000 && b-a > 300) {
			//비교했으니 다시 String 으로 바꿔줌
			datepicker1 = String.valueOf(a);
			datepicker2 = String.valueOf(b);
			//바꿔줬으니 다시 날짜중간에 - 넣음 
			datepicker1 = datepicker1.substring(0,4)+"-"+datepicker1.substring(4,datepicker1.length());
			datepicker1 = datepicker1.substring(0,7)+"-"+datepicker1.substring(7,datepicker1.length());
			datepicker2 = datepicker2.substring(0,4)+"-"+datepicker2.substring(4,datepicker2.length());
			datepicker2 = datepicker2.substring(0,7)+"-"+datepicker2.substring(7,datepicker2.length());
			//12개월
			ArrayList<ChartVo> list = adminMapper.orderSelectTwsmonthView(datepicker1,datepicker2);
			ArrayList<ChartVo> tableList = adminMapper.tableSelectList(datepicker1,datepicker2);
			ArrayList<ChartVo> tableSummary = adminMapper.tableSelectSummary(datepicker1,datepicker2);
			orderView.put("list", list);
			orderView.put("tableList", tableList);
			orderView.put("tableSummary", tableSummary);
			System.out.println("최근12개월까지의 매출");
			return orderView;
		}
		return orderView;
	}//Map public

}
