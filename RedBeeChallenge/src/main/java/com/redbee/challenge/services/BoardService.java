package com.redbee.challenge.services;

import java.util.List;

import com.redbee.challenge.exceptions.BoardDAOException;
import com.redbee.challenge.model.Board;

public interface BoardService {

	public List<Board> getAllBoards();

	public Board getBoardByName(String name) throws BoardDAOException;

	public Board insertBoard(Board board) throws BoardDAOException;

	public Board updateBoard(Board board) throws BoardDAOException;

	public void deleteBoard(String name) throws BoardDAOException;

	public void getInterestByBoardName(String name) throws BoardDAOException;

}
