package com.site.siwon.web.dto;

import com.site.siwon.domain.order.Order;
import com.site.siwon.domain.order.OrderState;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class OrderSaveRequestDto {
    private String recipient;
    private String sender;
    private Integer cartId;
    private String deliveryArea;

    public Order toEntity(){
        LocalDateTime now = LocalDateTime.now();
        return Order.builder()
                .refundFlag("N")
                .orderStatus(OrderState.PREPARING)
                .orderDate(now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss")))
                .recipient(recipient)
                .sender(sender)
                .cartId(cartId)
                .deliveryArea(deliveryArea)
                .build();
    }
}
