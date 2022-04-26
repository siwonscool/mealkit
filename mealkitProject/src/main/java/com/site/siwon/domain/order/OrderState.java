package com.site.siwon.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {

    PREPARING("preparing"),
    DELIVERING("delivering"),
    DELIVERY_COMPLETED("delivery_completed"),
    CANCELED("canceled"),
    PAYMENT_COMPLETED("payment_completed");

    private final String orderState;
}
