package com.site.member;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.site.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.oneBoard.OneBoardService;
import com.site.order.OrderService;
import com.site.vo.MemberVo;
import com.site.vo.One_BoardVo;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final OneBoardService oneBoardService;
	private final OrderService orderService;

	@PostMapping("/login")
	@ResponseBody
	public int login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
		int loginFlag = 0;
		MemberVo memberVo = memberService.login(id, pw);
		if (memberVo != null) {
			session.setAttribute("session_nickname", memberVo.getNickname());
			session.setAttribute("session_id", id);
			session.setAttribute("session_flag", "success");
			session.setAttribute("session_user", "member");
			session.setAttribute("session_rank", memberVo.getRank());
			loginFlag = 1;
		}
		return loginFlag;
	}

	@PostMapping("/registerCheckId")
	@ResponseBody
	public MemberVo checkRegisterId(@RequestParam String id) {
		return memberService.findMemberInfo(id);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public int register(MemberVo memberVo) {
		return memberService.registerMember(memberVo);
	}

	@GetMapping("/popup")
	public String deliveryViewPopup(@RequestParam String deliveryNum, Model model) {
		model.addAttribute("delivery_num", deliveryNum);
		return "popup/popup";
	}

	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		String memberId = (String)session.getAttribute("session_id");
		ArrayList<OrderVo> orderList = orderService.findOrderListToMember(memberId,"member");
		ArrayList<One_BoardVo> boardList = oneBoardService.findMemberBoardList(memberId);
		model.addAttribute("mbList", boardList);
		model.addAttribute("order_map", orderList);
		return "/member/mypage";
	}

	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		String memberId = (String) session.getAttribute("session_id");
		MemberVo memberVo = memberService.findMemberInfo(memberId);
		model.addAttribute("memberVo", memberVo);
		return "member/update";
	}

	@PostMapping("/update")
	@ResponseBody
	public int update(MemberVo memberVo) {
		return memberService.updateMemberInfo(memberVo);
	}

}
