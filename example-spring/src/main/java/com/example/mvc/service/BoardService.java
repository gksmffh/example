package com.example.mvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.framework.data.domain.PageRequestParameter;
import com.example.mvc.domain.Board;
import com.example.mvc.parameter.BoardParameter;
import com.example.mvc.parameter.BoardSearchParameter;
import com.example.mvc.repository.BoardRepository;

/**
 * 게시판 서비스
 */
@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	/**
	 * 목록 리턴.
	 * @param pageRequestParameter
	 * @return
	 */
	public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
		return repository.getList(pageRequestParameter);
	}
	
	/**
	 * 상세 정보 리턴.
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	/**
	 * 등록 처리.
	 * @param parameter
	 */
	public void save(BoardParameter parameter) {
		// 조회하여 리턴된 정보
		Board board = repository.get(parameter.getBoardSeq());
		if (board == null) {
			repository.save(parameter);
		} else {
			repository.update(parameter);
		}
	}
	
	/**
	 * 삭제 처리.
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
}
