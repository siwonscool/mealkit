package com.site.siwon.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //int 가 아닌 Integer,Long,String ... 을 사용하는 이유는 불변객체 이기 때문인가요?
    private Long id;
    private String refundFlag;
    @Enumerated(EnumType.STRING)
    private OrderState orderStatus;

    private String orderDate;
    private String recipient;
    private String sender;

    //order 테이블 (Pk) <-> cart 테이블 (Fk)
    //cart 테이블 (Pk) <-> product 테이블 (Fk)
    //테이블이 위와같이 설계가 되어있어서
    //Order 엔티티에 cartId 만 저장하는 방식인데
    //추후에 조회할때 불필요한 Join 연산이 발생할거 같은데
    //해결방안을 모르겠습니다..
    private Integer cartId;
    private String deliveryArea;


    @Builder
    public Order(Long id, String refundFlag, OrderState orderStatus, String orderDate, String recipient, String sender, Integer cartId, String deliveryArea) {
        this.id = id;
        this.refundFlag = refundFlag;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.recipient = recipient;
        this.sender = sender;
        this.cartId = cartId;
        this.deliveryArea = deliveryArea;
    }
}
