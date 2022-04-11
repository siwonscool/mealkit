package com.site.admin;

import com.site.admin.vo.AdminMemberPageVo;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	private final MemberService memberService;
	
	@GetMapping("/memberList")
	public String memberList() {
		return "/admin/member_list";
	}
	
	@PostMapping("/memberList")
	@ResponseBody
	public void memberList(AdminMemberPageVo memberPageVo, Model model){
		model.addAttribute("memberListNumberingDto", adminService.memberList(memberPageVo));
	}

	@GetMapping("/memberUpdate")
	public String updateMember(@RequestParam String id, Model model) {
		model.addAttribute(memberService.findMemberInfo(id));
		return "/admin/memberUpdate";
	}
	
	@PostMapping("/memberUpdate")
	@ResponseBody
	public int updateMember(MemberVo memberVo) {
		return memberService.updateMemberInfo(memberVo);
	}
	
	@RequestMapping("/memberCheckId")
	@ResponseBody
	public MemberVo checkMemberId(@RequestParam String id) {
		return memberService.findMemberInfo(id);
	}
	
	@RequestMapping("/memberDelete")
	public String deleteMember(@RequestParam String id) {
		adminService.deleteMember(id);
		return "/admin/memberList";
	}

	@GetMapping("/chart")
	public String viewOrderChart(Model model) {
		model.addAttribute("orderView", adminService.viewOrderChart());
		return "/admin/chart";
	}

	@PostMapping("/Search")
	public String SearchOrderChart(@RequestParam String datepicker1,
								   @RequestParam String datepicker2,
								   Model model) {
		model.addAttribute("orderView", adminService.viewOrderPeriod(datepicker1,datepicker2));
		model.addAttribute("datepicker1",datepicker1);
		model.addAttribute("datepicker2",datepicker2);
		return "/admin/chart";
	}
}
