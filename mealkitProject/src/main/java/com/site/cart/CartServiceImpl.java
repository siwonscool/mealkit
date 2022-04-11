package com.site.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ArrayList<CartVo> findMemberCartList(String member_id) {
		return cartMapper.findMemberCartList(member_id);
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
		int result = cartMapper.deleteAllProductInCart(cartVo);
		return result;
	}

//	선택한상품만 주문 리스트 출력
	@Override
	public Map<String, Object> orderCartList(List<String> list){
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList<CartVo> lists = new ArrayList<CartVo>();
        CartVo cartVo = null;
        String cart_id="";
        for(int i=0; i<list.size(); i++){
            cartVo = cartMapper.orderCartList(list.get(i));
            lists.add(cartVo);
            if(i==0) {
            	cart_id+=cartVo.getId();
            }else {
            	cart_id+="/"+cartVo.getId();
            }
        }
        map.put("lists_count", lists.size());
        map.put("lists", lists);
        map.put("cart_id", cart_id);
        return map;
    }
	
}
