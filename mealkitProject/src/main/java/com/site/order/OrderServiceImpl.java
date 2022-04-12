package com.site.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.site.order.vo.Top3ReviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.vo.OrderVo;
import com.site.vo.ReviewVo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void insertOrderInfo(String cartId, String deliveryArea, String recipient, String sender) {
		orderMapper.insertOrderInfo(cartId, deliveryArea,recipient,sender);
	}

	@Override
	public void updateCartStatus(String cartId) {
		orderMapper.updateCartStatus(cartId);
	}

	@Override
	public void updateProductQuantity(String cartId) {
		orderMapper.updateProductQuantity(cartId);
	}
	
	@Override
	public ArrayList<OrderVo> findOrderListToMember(String id, String memberType) {
		if (memberType.equals("member")){
			return orderMapper.findOrderListToMember(id);
		}else{
			return orderMapper.selectStoreOrderList(id);
		}
	}
	
	@Override
	public void updateStatusDeliveryNum(String orderId, String deliveryNum) {
		orderMapper.updateStatusDeliveryNum(orderId, deliveryNum);
	}
	
	@Override
	public void updateStatus6FinalBuy(String orderId) {
		orderMapper.updateStatus6FinalBuy(orderId);
	}
	
	/** 리뷰시작 **/
	@Override
	public void insertReviewInfo(ReviewVo reviewVo) {
		orderMapper.insertReviewInfo(reviewVo);
	}
	
	@Override
	public void insertReviewPicture(String safeFile, int productId) {
		orderMapper.insertReviewPicture(safeFile, productId);
	}

	@Override
	public Top3ReviewVo Top3reviewList() {
		Map<String, Object> map = new HashMap<>();
		ArrayList<ReviewVo> top3ReviewList = orderMapper.findTop3ReviewList();
		List<String> tep3ReviewPictureList = new ArrayList<>();

		for(int i=0; i < top3ReviewList.size(); i++) {
			String r_topPicture = orderMapper.findTop3ReviewPicture(top3ReviewList.get(i).getId());
			tep3ReviewPictureList.add(r_topPicture);
		}

		return new Top3ReviewVo(top3ReviewList,tep3ReviewPictureList);
	}
}
