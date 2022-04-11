package com.site.order;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.site.cart.vo.SelectedProductListVo;
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
	public String selectedProductToOrderInCart(@RequestParam List<String> selectedProductList, HttpSession session, Model model) {
		String session_id = (String) session.getAttribute("session_id");
		SelectedProductListVo selectedProductListVo = cartService.selectedProductListInCart(selectedProductList);
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
	public String orderOk(Model model,
						  @RequestParam String cart_id,
						  @RequestParam String delivery_area,
						  @RequestParam String recipient,
						  @RequestParam String sender,
						  HttpSession session) {
		session.setAttribute("session_payflag", "success");
		System.out.println("cart_id = "+cart_id);
		System.out.println("delivery_area = "+delivery_area);
		String[] cart_list = cart_id.split("/");
		for(int i=0; i<cart_list.length; i++) {
			orderService.orderSuccessInsert(cart_list[i],delivery_area,recipient,sender);//결제완료시 ordered테이블 정보담김
			orderService.cartStatusUpdate(cart_list[i]);//결제완료시 cart테이블 status변경
			orderService.productQuantityUpdate(cart_list[i]);//product의 Quantity(cart의 수량만큼 -1)
		}
		
		return "/order/orderOk";
	}

	
	@RequestMapping("/deliveryStart")
	@ResponseBody
	public void OderDeliveryStart(@RequestParam String delivery_num,
								  @RequestParam String id) {
		//주문 status 2 로 수정, 송장번호 추가
		orderService.deliveryStartupdate(id,delivery_num);
		System.out.println("del_num = "+delivery_num);
		System.out.println("id = "+id);
	}
	
	//구매 확정 버튼 클릭시
	@RequestMapping("buyAccess")
	@ResponseBody
	public void orderBuyAccess(@RequestParam String id) {
		System.out.println("id  : "+id);
		orderService.buyAccessUpdate(id);
	}
	
	
	/** 리뷰시작 **/
	
	@GetMapping("reviewWrite")
	public String reviewWriteInsert(@RequestParam String product_id,Model model) {
		model.addAttribute("product_id", product_id);
		model.addAttribute("result", 0);
		return "order/review_write";
	}
	
	
	@PostMapping("reviewWrite")
	public String reviewWriteInsert(ReviewVo reviewVo,
								   HttpSession session,
								   MultipartHttpServletRequest multi,
								   Model model) {
		
		//사진을 제외한 review 정보 등록
		reviewVo.setMember_id((String)session.getAttribute("session_id"));
		orderService.reviewWriteInsert(reviewVo);
		productService.productRateUpdate(reviewVo.getProduct_id());
		
		//reviewPicture 테이블에 사진저장
		List<MultipartFile> fileList = multi.getFiles("file");
        String src = multi.getParameter("src");

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈
            long time = System.currentTimeMillis();
            String safeFile = path + time + originFileName;
            try {
                mf.transferTo(new File(safeFile));
                orderService.reviewPictureInsert(time + originFileName,reviewVo.getProduct_id());
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        model.addAttribute("result", 1);
        return "order/review_write";
	}
	
}
