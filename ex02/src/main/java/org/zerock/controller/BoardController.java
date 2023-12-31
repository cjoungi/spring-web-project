package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

@Controller
@Log
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	/*
	@GetMapping("/list")
	public String list(Model model) {

		log.info("list");

		model.addAttribute("list", service.getList());

		return "/board/list";
	}
	*/
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list: " + cri);

		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 15));

		int total = service.getTotal(cri);
		log.info("total : " + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register : " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}

	//특정 게시물의 조회페이지와 수정/삭제 페이지는 동일
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or modify");

		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify : " + board);

		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		//return "redirect:/board/list";


		return "redirect:/board/list" + cri.getListLink();	// getListLink() 메서드를 이용하면 위 코드를 이렇게 바꿀 수 있다
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

		log.info("remove......." + bno);

		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		//return "redirect:/board/list";

		return "redirect:/board/list" + cri.getListLink();	// getListLink() 메서드를 이용하면 위 코드를 이렇게 바꿀 수 있다
	}


	@GetMapping("/register")
	public void register() {
		
	}
}
