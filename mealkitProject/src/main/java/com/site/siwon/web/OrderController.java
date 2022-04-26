package com.site.siwon.web;

import com.site.member.MemberService;
import com.site.siwon.adaptor.EmptyJsonResponse;
import com.site.siwon.adaptor.order.OrderProductAdaptor;
import com.site.siwon.service.ProductService;
import com.site.siwon.web.param.OrderParam;
import com.site.siwon.service.OrderService;
import com.site.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    //view controller 는 따로 모아두나요..?
    @GetMapping
    public String getProductFromCart(HttpSession session, Model model){
        String session_id = (String) session.getAttribute("session_id");
        MemberVo memberVo = memberService.findMemberInfo(session_id);
        String tel1 = memberVo.getTel().substring(3,7);
        String tel2 = memberVo.getTel().substring(7,11);
        model.addAttribute("memberVo",memberVo);
        model.addAttribute("tel1",tel1);
        model.addAttribute("tel2",tel2);
        return "/member/order";
    }

    //멘토님이 보내주신 자료를 보고 참고하려 했지만 1도 이해가 안되서..
    //이런식으로 API 설계하고 ResponseEntity 로 넘기는 방식자체가 해본적이없고 잘모르겠습니다..
    /*@PostMapping
    public ResponseEntity<OrderProductAdaptor> orderByDirect(@RequestBody final OrderParam orderParam){
        try{
            return ResponseEntity.ok(this.orderService.orderDirect(orderParam.getProductId(),orderParam.getStock()));
        }catch (Exception e){
            return new ResponseEntity(new EmptyJsonResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    //주문정보 변경
    //구매확정

    //review 작성
    //review
}
