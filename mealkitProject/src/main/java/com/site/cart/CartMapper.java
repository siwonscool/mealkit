package com.site.cart;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.site.vo.CartVo;

@Mapper
public interface CartMapper {

	int findProductInCart(CartVo cartVo);

	int insertProductInCart(CartVo cartVo);

	int updateProductInCart(CartVo cartVo);

	ArrayList<CartVo> findMemberCartList(String memberId);

	void updateCartAmount(CartVo cartVo);

	void deleteOneProductInCart(CartVo cartVo);

//	장바구니 전체 삭제
	int deleteAllProductInCart(CartVo cartVo);
	
//	선택한상품만 주문 리스트 출력
	CartVo orderCartList(String list);

}
