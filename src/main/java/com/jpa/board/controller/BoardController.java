package com.jpa.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jpa.board.dto.BoardDto;
import com.jpa.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping("/")
	public String list(Model model) {
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		ArrayList<BoardDto> blist = bm.boardlist();
		model.addAttribute("boardList",blist);
		return "board/list";
	}
	
	@GetMapping("post")
	public String insert() {
		return "board/write";
	}
	
	@PostMapping("/post")
	public String insert(HttpServletRequest request) {
		String title = request.getParameter("title");
		String context = request.getParameter("context");
		
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		bm.insert(title, context);
		return "redirect:/";
	}
	@GetMapping("/post/{num}")
	public String detail(@PathVariable("num")Long num,Model model) {
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		BoardDto bdto = bm.getupdate(num);
		model.addAttribute("boardDto",bdto);
		return "board/detail";
	}
	
	@GetMapping("/post/edit/{num}")
	public String getupdate(@PathVariable("num") Long num, Model model) {
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		BoardDto bdto = bm.getupdate(num);
		model.addAttribute("boardDto",bdto);
		return "board/update";
	}
	
	@PutMapping("/post/edit/{num}")
	public String setupdate(@PathVariable("num") Long num, HttpServletRequest request) {
		String title = request.getParameter("title");
		String context = request.getParameter("context");
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		bm.setupdate(num, title, context);
		return "redirect:/";
	}
	
	@DeleteMapping("/post/{num}")
	public String delete(@PathVariable("num") Long num) {
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		bm.delete(num);
		return "redirect:/";
				
	}
	
	@GetMapping("/board/search")
	public String search(HttpServletRequest request, Model model) {
		String keyword = request.getParameter("keyword");
		
		BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
		ArrayList<BoardDto> bdto = bm.search(keyword);
		model.addAttribute("boardList",bdto);
		return "board/list";
	}
	
}
