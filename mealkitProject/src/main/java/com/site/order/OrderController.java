package com.site.order;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.site.cart.vo.SelectedProductListVo;
import com.site.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.site.cart.CartService;
import com.site.member.MemberService;
import com.site.product.ProductService;
import com.site.vo.MemberVo;
import com.site.vo.ReviewVo;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;
	private final MemberService memberService;
	private final ProductService productService;
	
	@Value("${fileUrlReview}")
    private String path;
	
	@RequestMapping("/order")
	public String selectedProductToOrderInCart(@RequestParam List<String> selectedCartIdList, HttpSession session, Model model) {
		String session_id = (String) session.getAttribute("session_id");
		SelectedProductListVo selectedProductListVo = cartService.selectedProductListInCart(selectedCartIdList);
		model.addAttribute("selectedProductListVo",selectedProductListVo);
		MemberVo memberVo = memberService.findMemberInfo(session_id);
		String tel1 = memberVo.getTel().substring(3,7);
		String tel2 = memberVo.getTel().substring(7,11);
		model.addAttribute("memberVo",memberVo);
		model.addAttribute("tel1",tel1);
		model.addAttribute("tel2",tel2);
		return "/member/order";
	}
	
	@RequestMapping("/success")
	public String paymentProduct(OrderVo orderVo, @RequestParam String cartIds, HttpSession session) {
		session.setAttribute("session_payflag", "success");
		String[] selectedCartList = cartIds.split("/");
		for(int i = 0; i < selectedCartList.length; i++) {
			orderService.insertOrderInfo(selectedCartList[i], orderVo);
			orderService.updateCartStatus(selectedCartList[i]);
			orderService.updateProductQuantity(selectedCartList[i]);
		}
		
		return "/order/orderOk";
	}

	@RequestMapping("/deliveryStart")
	@ResponseBody
	public void startDelivery(@RequestParam String delivery_num, @RequestParam String id) {
		orderService.updateStatusDeliveryNum(id, delivery_num);
	}
	
	@RequestMapping("buyAccess")
	@ResponseBody
	public void finalBuyAccess(@RequestParam String orderId) {
		orderService.updateStatus6FinalBuy(orderId);
	}

	@GetMapping("reviewWrite")
	public String reviewWriteInsert(@RequestParam String productId, Model model) {
		model.addAttribute("product_id", productId);
		model.addAttribute("result", 0);
		return "order/review_write";
	}

	@PostMapping("reviewWrite")
	public String reviewWriteInsert(ReviewVo reviewVo,
								   HttpSession session,
								   MultipartHttpServletRequest multi,
								   Model model) {
		String memberId = (String)session.getAttribute("session_id");
		reviewVo.setMemberId(memberId);

		orderService.insertReviewInfo(reviewVo);
		productService.updateProductRate(reviewVo.getProductId());
		
		List<MultipartFile> fileList = multi.getFiles("file");

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename();
            long time = System.currentTimeMillis();
            String safeFile = path + time + originFileName;
            try {
                mf.transferTo(new File(safeFile));
                orderService.insertReviewPicture(time + originFileName,reviewVo.getProductId());
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        model.addAttribute("result", 1);
        return "order/review_write";
	}
	
}
