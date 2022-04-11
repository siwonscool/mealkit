package com.site.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.site.cart.vo.SelectedProductListVo;
import com.site.vo.CartVo;

public interface CartService {

	int insertProductInCart(CartVo cartVo);

	ArrayList<CartVo> findMemberCartList(String memberId);

	void updateCartAmount(CartVo cartVo);

	void deleteOneProductInCart(CartVo cartVo);

	int deleteAllProductInCart(CartVo cartVo);

	SelectedProductListVo selectedProductListInCart(List<String> cartIdList);

}
