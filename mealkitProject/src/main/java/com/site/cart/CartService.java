package com.site.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.site.vo.CartVo;

public interface CartService {

	int insertProductInCart(CartVo cartVo);

	ArrayList<CartVo> findMemberCartList(String member_id);

	void updateCartAmount(CartVo cartVo);

	void deleteOneProductInCart(CartVo cartVo);

	int deleteAllProductInCart(CartVo cartVo);

	//선택한상품만 주문 리스트 출력
	Map<String, Object> orderCartList(List<String> list);

}
