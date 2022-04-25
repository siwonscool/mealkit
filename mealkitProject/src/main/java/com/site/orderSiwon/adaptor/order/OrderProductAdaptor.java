package com.site.orderSiwon.adaptor.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderProductAdaptor {
    private Integer id;
    private Integer productId;

    @Builder
    public OrderProductAdaptor(Integer id, Integer productId) {
        this.id = id;
        this.productId = productId;
    }
}
