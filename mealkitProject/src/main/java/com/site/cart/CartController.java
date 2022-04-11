package com.site.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.vo.CartVo;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;
	
	@RequestMapping("/cartInsert")
	@ResponseBody
	public int insertProductInCart(CartVo cartVo, HttpSession session) {
		String memberId = (String) session.getAttribute("session_id");
		cartVo.setMemberId(memberId);
		return cartService.insertProductInCart(cartVo);
	}
	
	@RequestMapping("/memberCartList")
	public String findMemberCartList(HttpSession session, Model model) {
		String memberId = (String) session.getAttribute("session_id");
		ArrayList<CartVo> memberCartList = cartService.findMemberCartList(memberId);
		model.addAttribute("memberCartList", memberCartList);
		return "/member/cart";
	}
	
	@RequestMapping("/cartAmountUpdate")
	@ResponseBody
	public void cartAmountUpdate(CartVo cartVo) {
		cartService.updateCartAmount(cartVo);
	}
	
	@RequestMapping("/cartOneDel")
	@ResponseBody
	public void deleteOneProductInCart(CartVo cartVo) {
		cartService.deleteOneProductInCart(cartVo);
	}
	
//	장바구니 선택 삭제
	@RequestMapping("/cartSelectDel")
	@ResponseBody
	public int deleteSelectedProductInCart(@RequestParam int[] id, HttpSession session) {
		String member_id = (String) session.getAttribute("session_id");
		CartVo cartVo = new CartVo();
		cartVo.setMemberId(member_id);
		for(int i=0; i < id.length; i++) {
			cartVo.setId(id[i]);
			cartService.deleteOneProductInCart(cartVo);
		}
		return id.length;
	}
	
	@RequestMapping("/cartAllDel")
	@ResponseBody
	public int deleteAllProductInCart(HttpSession session) {
		String member_id = (String) session.getAttribute("session_id");
		CartVo cartVo = new CartVo();
		cartVo.setMemberId(member_id);
		return cartService.deleteAllProductInCart(cartVo);
	}

}
