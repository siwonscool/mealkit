package com.site.member;

import com.site.vo.MemberVo;

public interface MemberService {

	MemberVo login(String id, String pw);
	
	int registerMember(MemberVo memberVo);

	MemberVo findMemberInfo(String id);

	int updateMemberInfo(MemberVo memberVo);

}
