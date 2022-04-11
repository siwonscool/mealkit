package com.site.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ChartVo {

	//Summary & List 용
	private int refundAmount;
	private int refundPrice;
	
	private String name;
	private int totalAmount;
	private int totalPrice;
	
	private String refundFlag;

	private String month;
	private String year;
	
	private String productName;
	private int amount; 
	private int quantity; 
	
	private int day;
	private int week;

}
