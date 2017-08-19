package com.redbee.challenge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.challenge.exceptions.BoardDAOException;
import com.redbee.challenge.model.Board;
import com.redbee.challenge.services.BoardService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/boards")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;


	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getBoards() {
		return new ResponseEntity<List<Board>>(boardService.getAllBoards(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{name}/interests/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getBoardByName(@PathVariable String name) {
		logger.debug("Obteniendo los intereses de " + name);
		
		try {
			boardService.getInterestByBoardName(name);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (BoardDAOException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertBoard(@RequestBody Board board) {
		logger.debug("Agregando los intereses: " + board); 
		
		try {
			return new ResponseEntity<Board>(boardService.insertBoard(board), HttpStatus.CREATED);
		} catch (BoardDAOException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
		}
		
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> updateBoard(@PathVariable String name, @RequestBody Board board) {
		logger.debug("Actualizando el board de: " + name + " con: " + board);
		
		// Utilizando el nombre como PK para actualizar
		board.setName(name);
		try {
			return new ResponseEntity<Board>(boardService.updateBoard(board), HttpStatus.OK);
		} catch (BoardDAOException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteBoard(@PathVariable("name") String name) {

		logger.debug("Borrando el board de: " + name); 
		try {
			boardService.deleteBoard(name);
			return new ResponseEntity<Board>(HttpStatus.NO_CONTENT);
		} catch (BoardDAOException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}	
}
