package com.site.vo;

import lombok.*;

@Getter
public class CartVo {
	@Setter
	private int id;
	
	private int productId;

	private int productAmount;

	private int productQuantity;

	private int productPrice;

	@Setter
	private String memberId;

	private String orderStatus;

	private String productName;

	private String productPicture;

	public CartVo(String memberId) {
		this.memberId = memberId;
	}
}
