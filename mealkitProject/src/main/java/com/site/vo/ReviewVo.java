package com.site.vo;

import lombok.*;

@Getter
public class ReviewVo {
	private int id;

	private int productId;

	private int rate;

	@Setter
	private String memberId;

	private String about;

	private String reviewDate;

	private String name;

	private String nickname;

	private String picture;

}
