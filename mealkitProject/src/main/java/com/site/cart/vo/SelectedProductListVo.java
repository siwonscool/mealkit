package com.site.cart.vo;

import com.site.vo.CartVo;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SelectedProductListVo {
    private List<CartVo> selectedProductList;
    private int selectedProductListCount;
    private String cartId;
}
