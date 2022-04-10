package com.site.admin;

import java.util.Map;

import com.site.admin.vo.AdminMemberListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.member.MemberService;
import com.site.vo.MemberVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MemberService memberService;
	
//	회원정보리스트
	@GetMapping("/memberList")
	public String memberList() {
		return "/admin/member_list";
	}
	
	@PostMapping("/memberList")
	@ResponseBody
	public void memberList(AdminMemberListVo memberListVo, Model model){
		model.addAttribute("memberListMap", adminService.memberList(memberListVo));
	}
	
//	회원정보 수정
	@GetMapping("/memberUpdate")
	public String memberUpdate(@RequestParam String id, Model model) {
		MemberVo memberVo = memberService.findMemberInfo(id);
		model.addAttribute(memberVo);
		return "/admin/member_update";
	}
	
	@PostMapping("/memberUpdate")
	@ResponseBody
	public int memberUpdate(MemberVo memberVo) {
		int result = memberService.update(memberVo);
		return result;
	}
	
//	회원정보 수정 아이디 체크
	@RequestMapping("/memberCheckId")
	@ResponseBody
	public int memberCheckId(@RequestParam String id) {
		int result = memberService.registerCheckId(id);
		return result;
	}
	
//	회원정보 삭제
	@RequestMapping("/memberDelete")
	public String memberDelete(@RequestParam String id) {
		adminService.memberDelete(id);
		return "/admin/member_list";
	}

//	챠트
	@GetMapping("/chart")
	public String chart(Model model) {
		Map<String, Object> orderView = adminService.orderView();
		model.addAttribute("orderView", orderView);
		return "/admin/chart";
	}
//	검색부분
	@PostMapping("/Search")
	public String Search(@RequestParam String datepicker1,
						@RequestParam String datepicker2,
						Model model) {
		Map<String, Object> orderView = adminService.orderSelectView(datepicker1,datepicker2);
		model.addAttribute("orderView", orderView);
		model.addAttribute("datepicker1",datepicker1);
		model.addAttribute("datepicker2",datepicker2);
		
		return "/admin/chart";
	}
}
