package com.site.siwon.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //int 가 아닌 Integer 를 사용하는 이유는 불변객체 이기 때문인가요?
    private Long id;
    private Integer productId;
    private Integer productNumber;
    private Integer totalPrice;
    private Integer orderStock;

    @Enumerated(EnumType.STRING)
    private OrderState orderStatus;

    //@Builder

}
