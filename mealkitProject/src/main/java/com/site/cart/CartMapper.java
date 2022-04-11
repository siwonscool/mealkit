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

	int deleteAllProductInCart(CartVo cartVo);
	
	CartVo findCartInfo(String cartId);

}
