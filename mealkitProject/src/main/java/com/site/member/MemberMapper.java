package com.site.member;

import org.apache.ibatis.annotations.Mapper;

import com.site.vo.MemberVo;

@Mapper
public interface MemberMapper {

	MemberVo findMemberInfo(String id);

	int registerMember(MemberVo memberVo);

	int updateMemberInfo(MemberVo memberVo);


}
