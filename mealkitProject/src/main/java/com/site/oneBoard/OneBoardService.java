package com.site.oneBoard;

import java.util.ArrayList;
import java.util.Map;

import com.site.vo.One_BoardVo;

public interface OneBoardService {

	Map<String, Object> selectBoardList(String category, String category1, String searchword);

	void insertBoardWrite(One_BoardVo one_boardVo);

	One_BoardVo selectBoardView(int id);

	One_BoardVo selectBoardViewPre(int id);

	One_BoardVo selectBoardViewNext(int id);

	void deleteBoardDelete(int id);

	int updateBoardModify(One_BoardVo one_boardVo);

	void insertBoardReply(One_BoardVo one_BoardVo);
	
	ArrayList<One_BoardVo> findMemberBoardList(String id);

}
