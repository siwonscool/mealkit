package com.site.orderSiwon.order.service;

import com.site.orderSiwon.adaptor.order.OrderProductAdaptor;
import com.site.orderSiwon.domain.OrderProduct;

public class OrderService {
    public OrderProductAdaptor orderDirect(int productId, int stock) {
        //미완성
    }

    private OrderProductAdaptor getOrderProductAdaptor(final OrderProduct orderProduct){
        return OrderProductAdaptor.builder()
                .id(orderProduct.getId())
                .build();
    }
}
