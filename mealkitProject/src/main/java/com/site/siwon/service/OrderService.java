package com.site.siwon.service;

import com.site.siwon.adaptor.order.OrderProductAdaptor;
import com.site.siwon.domain.order.OrderProduct;

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
