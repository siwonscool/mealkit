package com.site.admin;

import java.util.Map;

public interface AdminService {

	Map<String, Object> memberList(int page, String category, String order, String searchWord);

	int memberDelete(String id);
	
	Map<String, Object> orderView();
	
	
	//검색부분
	Map<String, Object> orderSelectView(String datepicker1, String datepicker2);
	
}
