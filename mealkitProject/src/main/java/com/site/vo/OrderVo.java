package com.site.vo;

import java.sql.Timestamp;

import lombok.*;

@Getter
public class OrderVo {

	private int id;
	private int productId;
	private int productNumber;
	private int price;
	private int amount;

	private String memberId;
	private String refundFlag;
	private String status;
	private String recipient;
	private String sender;
	private String paymentWay;
	private String deliveryArea;

	private String picture;
	private String name;
	private String deliveryNum;
	private String orderDate;

}
