package com.jpa.board.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.jpa.board.dto.BoardDto;

@Repository("com.jpa.board.mapper.BoardMapper")
public interface BoardMapper {
	
	public ArrayList<BoardDto> boardlist();
	
	public BoardDto getupdate(Long num);
	
	public void insert(String title, String context);
	
	public void setupdate(Long num, String title, String context);
	
	public void delete(Long num);
	
	public ArrayList<BoardDto> search(String keyword);
}
