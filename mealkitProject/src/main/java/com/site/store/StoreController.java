package com.site.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.site.vo.One_BoardVo;
import com.site.vo.ProductVo;
import com.site.vo.StoreVo;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

	private final StoreService storeService;
	private final OneBoardService oneBoardService;
	private final OrderService orderService;

	@PostMapping("/login")
	@ResponseBody
	public int login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
		int loginFlag = 0;
		StoreVo storeVo = storeService.login(id, pw);
		if (storeVo != null) {
			session.setAttribute("session_nickname", storeVo.getNickname());
			session.setAttribute("session_flag", "success");
			session.setAttribute("session_id", id);
			session.setAttribute("session_user", "store");
			loginFlag = 1;
		}
		return loginFlag;
	}

	@PostMapping("/registerCheckId")
	@ResponseBody
	public int registerCheckId(@RequestParam String id) {
		return storeService.registerCheckId(id);
	}

//	사업자 회원가입
	@PostMapping("/register")
	@ResponseBody
	public int registerStoreMember(StoreVo storeVo) {
		return storeService.register(storeVo);
	}

	@GetMapping("/product_mypage")
	public String storeMypage(HttpSession session, Model model) {
		String storeId = (String) session.getAttribute("session_id");
		ArrayList<ProductVo> map = storeService.storeProductList(storeId);
		StoreVo storeVo = storeService.updateView(storeId);
		model.addAttribute("sVo", storeVo);
		model.addAttribute("map", map);
		ArrayList<One_BoardVo> list = oneBoardService.findMemberBoardList(storeId);
		model.addAttribute("sbList", list);
		return "/store/product_mypage";
	}

	@RequestMapping("/product_search")
	@ResponseBody
	public Map<String, Object> search(HttpSession session, Model model,
			@RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		String id = (String) session.getAttribute("session_id");
		ArrayList<ProductVo> list = storeService.searchProduct(id, searchWord);
		System.out.println("list count" + list.size());
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("searchWord : " + searchWord);
		map.put("list", list);
		return map;
	}

	@GetMapping("/order_mypage")
	public String o_mypage(HttpSession session, Model model) {
		String id = (String) session.getAttribute("session_id");
		//주문 출력 DB 접근
		ArrayList<OrderVo> order_map = orderService.findOrderListToMember(id,"store");
		//게시판 출력 DB 접근
		ArrayList<One_BoardVo> list = oneBoardService.findMemberBoardList(id);
		model.addAttribute("order_map", order_map);
		model.addAttribute("sbList", list);
		return "/store/order_mypage";
	}
	
	@GetMapping("/update")
	public String storeupdate(HttpSession session, Model model) {
		String id = (String) session.getAttribute("session_id");
		StoreVo storeVo = storeService.updateView(id);
		model.addAttribute("storeVo", storeVo);

		return "store/storeupdate";
	}

	@PostMapping("/update")
	@ResponseBody
	public int storeupdate(StoreVo storeVo) {
		return storeService.update(storeVo);
	}

}
