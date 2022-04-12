package com.site.eventBoard;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.site.vo.Notice_BoardVo;

@Controller
public class Event_BoardController {

	@Autowired
	private Event_BoardService event_boardService;
	
	// 파일저장위치
	@Value("${fileUrlBoard}")
	private String fileUrl;

	// 게시판 글쓰기
	@GetMapping("/event_board/event_write")
	public String boardWrite() {
		return "/event_board/event_write";
	}

	@PostMapping("/event_board/event_write")
	public String boardWrite(Notice_BoardVo notice_boardVo, @RequestPart MultipartFile file) throws Exception {

		// 파일이 첨부가 되어 있으면
		if (file.getSize() != 0) {
			String originFileName = file.getOriginalFilename(); // 원본파일이름 저장
			long time = System.currentTimeMillis();
			// 파일이름
			String uploadFileName = String.format("%d_%s", time, originFileName);
			System.out.println("uploadFileName : " + uploadFileName);

			System.out.println("fileUrl : " + fileUrl);
			// 복사할 파일
			File f = new File(fileUrl + uploadFileName);
			// 파일 업로드
			file.transferTo(f);

			// db에 저장하기 위해 vo에 파일이름을 저장시킴.
			notice_boardVo.setNupload(uploadFileName);
		} else {
			notice_boardVo.setNupload(""); // 사진을 널값 처리
		}

		event_boardService.insertBoardWrite(notice_boardVo);
		return "redirect:/event_board/event_list";
	}

	// 게시판 리스트
	@RequestMapping("/event_board/event_list")
	public String boardList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam (defaultValue = "") String category,@RequestParam (defaultValue = "") String category1, @RequestParam @Nullable String searchWord, Model model) {
		System.err.println(category);
		// db에서 모든 게시물 가져옴
		Map<String,Object> map = event_boardService.selectBoardList(page,category,category1,searchWord);
		model.addAttribute("map", map);
		System.out.println("c_category : " + map.get("category"));
		System.out.println("c_searchWord : " + map.get("searchWord"));
		System.out.println(map.get("listCount"));
		return "/event_board/event_list";
	}

	/*
	 * // 게시판 리스트
	 * 
	 * @RequestMapping("search") public String
	 * search(@RequestParam(value="page",defaultValue = "1") int page,
	 * 
	 * @RequestParam String category,@RequestParam String searchWord, Model model) {
	 * //db에서 모든 게시물 가져옴 Map<String, Object> map =
	 * boardService.selectSearch(page,category,searchWord);
	 * model.addAttribute("map",map); return "/board/notice_list"; }
	 */
	// 게시판 리스트2
		@RequestMapping("/event_board/event_list2")
		public String boardList2(@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam (defaultValue = "") String category,@RequestParam (defaultValue = "") String category1, @RequestParam @Nullable String searchWord, Model model) {
			System.err.println(category);
			// db에서 모든 게시물 가져옴
			Map<String,Object> map = event_boardService.selectBoardList2(page,category,category1,searchWord);
			model.addAttribute("map", map);
			System.out.println("c_category : " + map.get("category"));
			System.out.println("c_searchWord : " + map.get("searchWord"));
			System.out.println(map.get("listCount2"));
			return "/event_board/event_list2";
		}
	
	// 게시판 뷰
	@RequestMapping("/event_board/event_view")
	public String boardView(@RequestParam int id, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		// db에서 게시글 1개 가져옴.
		Notice_BoardVo notice_boardVo =event_boardService.selectBoardView(id);
		System.out.println(id);
		// 이전글
		Notice_BoardVo event_boardVoPre = event_boardService.selectBoardViewPre(id);
		// 다음글
		Notice_BoardVo event_boardVoNext = event_boardService.selectBoardViewNext(id);
		model.addAttribute("page", page);
		model.addAttribute("notice_boardVo", notice_boardVo);
		model.addAttribute("event_boardVoPre", event_boardVoPre);
		model.addAttribute("event_boardVoNext", event_boardVoNext);
		return "/event_board/event_view";
	}

	// 게시판 수정
	@GetMapping("/event_board/event_modify")
	public String boardModify(@RequestParam int id, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		// db에서 게시글 1개 가져옴.
		Notice_BoardVo notice_boardVo = event_boardService.selectBoardView(id);
		model.addAttribute("page", page);
		model.addAttribute("notice_boardVo", notice_boardVo);
		return "/event_board/event_modify";
	}

	@PostMapping("/event_board/event_modify")
	public String boardModify(Notice_BoardVo notice_boardVo, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestPart MultipartFile file, @RequestParam String old_nupload, Model model) throws Exception {

		// 파일이 첨부가 되어 있으면
		if (file.getSize() != 0) {
			String originFileName = file.getOriginalFilename(); // 원본파일이름 저장
			long time = System.currentTimeMillis();
			// 파일이름
			String uploadFileName = String.format("%d_%s", time, originFileName);
			System.out.println("uploadFileName : " + uploadFileName);

			System.out.println("fileUrl : " + fileUrl);
			// 복사할 파일
			File f = new File(fileUrl + uploadFileName);
			// 파일 업로드
			file.transferTo(f);

			// db에 저장하기 위해 vo에 파일이름을 저장시킴.
			notice_boardVo.setNupload(uploadFileName);
		} else {
			// 파일첨부가 없을때 기존의 이름을 등록.
			notice_boardVo.setNupload(old_nupload);
		}

		// db에서 게시글 1개 가져옴.
		int result = event_boardService.updateBoardModify(notice_boardVo);
		return "redirect:/event_board/event_list?page=" + page;
	}

	// 게시판 삭제
	@RequestMapping("/event_board/event_delete")
	public String boardDelete(@RequestParam int id, @RequestParam(value = "page", defaultValue = "1") int page) {

		event_boardService.deleteBoardDelete(id);
		return "redirect:/event_board/event_list?page=" + page;
	}
	
	

}
