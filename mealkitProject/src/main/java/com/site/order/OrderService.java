package com.site.order;

import java.util.ArrayList;
import java.util.Map;

import com.site.order.vo.Top3ReviewVo;
import com.site.vo.OrderVo;
import com.site.vo.ReviewVo;

public interface OrderService {

	void insertOrderInfo(String cartId, OrderVo orderVo);

	void updateCartStatus(String cartId);

	void updateProductQuantity(String cartId);

	ArrayList<OrderVo> findOrderListToMember(String id, String memberType);

	void updateStatusDeliveryNum(String orderId, String deliveryNum);
	
	void updateStatus6FinalBuy(String orderId);
	
	/** 리뷰시작 **/
	void insertReviewInfo(ReviewVo reviewVo);
	
	void insertReviewPicture(String safeFile, int productId);
	
	Top3ReviewVo Top3reviewList();

}
