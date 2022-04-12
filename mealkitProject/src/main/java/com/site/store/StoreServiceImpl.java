package com.site.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.site.vo.ProductVo;
import com.site.vo.StoreVo;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public StoreVo login(String id, String pw) {
		StoreVo storeVo = storeMapper.selectId(id);
		if(passwordEncoder.matches(pw, storeVo.getPw())) {
		} else {
			storeVo = null;
		}
		return storeVo;
	}

	@Override
	public int registerCheckId(String id) {
		int result = storeMapper.registerCheckId(id);
		return result;
	}
	
	@Override
	public int register(StoreVo storeVo) {
		storeVo.setPw(passwordEncoder.encode(storeVo.getPw()));
		int result = storeMapper.register(storeVo);
		return result;
	}
	
	@Override
	public Map<String, Object> storeProductList(String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		ArrayList<ProductVo> pList = storeMapper.storeProductList(id);
		map.put("pList", pList);
		return map;
	}

	@Override
	public ArrayList<ProductVo> searchProduct(String id,String searchWord) {
		ArrayList<ProductVo> list = storeMapper.searchListSelect(id,searchWord);
		return list;
	}

	@Override
	public StoreVo updateView(String id) {
		StoreVo storeVo = storeMapper.selectId(id);
		return storeVo;
	}

	@Override
	public int update(StoreVo storeVo) {
		int rs = storeMapper.update(storeVo);
		return rs;
	}

}
