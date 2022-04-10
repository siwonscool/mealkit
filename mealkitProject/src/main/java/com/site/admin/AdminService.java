package com.site.admin;

import com.site.admin.vo.AdminMemberListVo;

import java.util.Map;

public interface AdminService {

	Map<String, Object> memberList(AdminMemberListVo memberListVo);

	int deleteMember(String id);
	
	Map<String, Object> orderView();
	
	//검색부분
	Map<String, Object> orderSelectView(String datepicker1, String datepicker2);
	
}
