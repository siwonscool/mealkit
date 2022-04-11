package com.site.vo;

import lombok.*;

@Getter
public class CartVo {
	@Setter
	private int id;
	private int productId;
	private int amount;
	private int quantity;
	@Setter
	private String memberId;
	private String status;
	private String productName;
	private String picture;
	private int price;
	
	
}
