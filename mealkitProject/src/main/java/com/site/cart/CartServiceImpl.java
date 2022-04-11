package com.site.cart;

import java.util.ArrayList;
import java.util.List;

import com.site.cart.vo.SelectedProductListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.site.vo.CartVo;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartMapper cartMapper;

	@Override
	public int insertProductInCart(CartVo cartVo) {
		if(cartMapper.findProductInCart(cartVo) >= 1) {
			return cartMapper.updateProductInCart(cartVo);
		} else {
			return cartMapper.insertProductInCart(cartVo);
		}
	}

	@Override
	public ArrayList<CartVo> findMemberCartList(String memberId) {
		return cartMapper.findMemberCartList(memberId);
	}

	@Override
	public void updateCartAmount(CartVo cartVo) {
		cartMapper.updateCartAmount(cartVo);
	}

	@Override
	public void deleteOneProductInCart(CartVo cartVo) {
		cartMapper.deleteOneProductInCart(cartVo);
	}

	@Override
	public int deleteAllProductInCart(CartVo cartVo) {
		return cartMapper.deleteAllProductInCart(cartVo);
	}

	@Override
	public SelectedProductListVo selectedProductListInCart(List<String> cartIdList){
        List<CartVo> cartList = new ArrayList<>();
        StringBuilder cartId = new StringBuilder();

        for(int i = 0; i < cartIdList.size(); i++){
            CartVo cartVo = cartMapper.findCartInfo(cartIdList.get(i));
            cartList.add(cartVo);
            if( i == 0) {
            	cartId.append(cartVo.getId());
            }else {
            	cartId.append("/" + cartVo.getId());
            }
        }

        return new SelectedProductListVo(cartList,cartList.size(),cartId.toString());
    }
	
}
