package com.site.store;

import java.util.ArrayList;
import java.util.Map;

import com.site.vo.ProductVo;
import com.site.vo.StoreVo;

public interface StoreService {

	StoreVo login(String id, String pw);
	
	int registerCheckId(String id);

	int register(StoreVo storeVo);

	ArrayList<ProductVo> storeProductList(String storeId);

	ArrayList<ProductVo> searchProduct(String id, String searchWord);

	StoreVo updateView(String id);

	int update(StoreVo storeVo);

}
