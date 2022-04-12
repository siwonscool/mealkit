package com.site.order;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.site.vo.OrderVo;
import com.site.vo.ReviewVo;

@Mapper
public interface OrderMapper {

	void insertOrderInfo(String cartId, OrderVo orderVo);

	void updateCartStatus(String cartId);

	void updateProductQuantity(String cartId);

	ArrayList<OrderVo> findOrderListToMember(String id);

	ArrayList<OrderVo> selectStoreOrderList(String id);

	void updateStatusDeliveryNum(String orderId, String deliveryNum);

	void updateStatus6FinalBuy(String orderId);

	/**리뷰 시작**/
	void insertReviewInfo(ReviewVo reviewVo);

	void insertReviewPicture(String safeFile, int productId);

	ArrayList<ReviewVo> findTop3ReviewList();

	String findTop3ReviewPicture(int pictureId);
}
