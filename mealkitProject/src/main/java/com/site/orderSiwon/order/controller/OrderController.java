package com.site.orderSiwon.order.controller;

import com.site.orderSiwon.adaptor.EmptyJsonResponse;
import com.site.orderSiwon.adaptor.order.OrderProductAdaptor;
import com.site.orderSiwon.order.controller.param.OrderParam;
import com.site.orderSiwon.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //view controller 는 따로 모아두나요..?
    @GetMapping
    public String getProductFromCart(){
        return "/member/order";
    }

    @PostMapping
    public ResponseEntity<OrderProductAdaptor> orderByDirect(@RequestBody final OrderParam orderParam){
        try{
            return ResponseEntity.ok(this.orderService.orderDirect(orderParam.getProductId(),orderParam.getStock()));
        }catch (Exception e){
            return new ResponseEntity(new EmptyJsonResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //주문정보 변경

    //구매확정

    //review 작성

    //review
}
