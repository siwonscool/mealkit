package com.site.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.site.vo.MemberVo;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberVo login(String id, String pw) {
		MemberVo memberVo = memberMapper.findMemberInfo(id);
		if(passwordEncoder.matches(pw, memberVo.getPw())) {
		} else {
			memberVo = null;
		}
		return memberVo;
	}

	@Override
	public int registerMember(MemberVo memberVo) {
		memberVo.setPw(passwordEncoder.encode(memberVo.getPw()));
		return memberMapper.registerMember(memberVo);
	}

	@Override
	public MemberVo findMemberInfo(String id) {
		return memberMapper.findMemberInfo(id);
	}

	@Override
	public int updateMemberInfo(MemberVo memberVo) {
		memberVo.setPw(passwordEncoder.encode(memberVo.getPw()));
		return memberMapper.updateMemberInfo(memberVo);
	}
}
