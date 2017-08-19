package com.redbee.challenge.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.redbee.challenge.dao.BoardRepository;
import com.redbee.challenge.exceptions.BoardDAOException;
import com.redbee.challenge.model.Board;
import com.redbee.challenge.model.Interest;
import com.redbee.challenge.socialnetworks.SocialNetworkListener;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	public static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	BoardRepository repository;
	
	@Autowired @Qualifier("twitterListener")
	SocialNetworkListener twitterListener;
	
	@Autowired @Qualifier("facebookListener")
	SocialNetworkListener facebookListener;

	@Override
	public List<Board> getAllBoards() {
		return repository.findAll();
	}

	@Override
	public Board getBoardByName(String name) throws BoardDAOException {
		if (!repository.exists(name)) {
			throw new BoardDAOException("No se encontró el board con nombre " + name);
		}
		return repository.findByName(name);
	}


	@Override
	public Board insertBoard(Board board) throws BoardDAOException {
		if (board == null || repository.exists(board.getName())) {
			throw new BoardDAOException("El board con nombre " + board.getName() + " ya existe");
		}
		return repository.insert(board);
	}

	@Override
	public Board updateBoard(Board board) throws BoardDAOException {

		if (board == null) {
			throw new BoardDAOException("No hay ningun board para actualizar");
		}

		if (!repository.exists(board.getName())) {
			throw new BoardDAOException("No se encontró el board con nombre " + board.getName());
		}
		return repository.save(board);
	}

	@Override
	public void deleteBoard(String name) throws BoardDAOException {
		if (!repository.exists(name)) {
			throw new BoardDAOException("No se encontró el board con nombre " + name);
		}
		repository.delete(name);
	}

	@Override
	public void getInterestByBoardName(String name) throws BoardDAOException {
		if (!repository.exists(name)) {
			throw new BoardDAOException("No se encontró el board con nombre " + name);
		}
		Board foundBoard = repository.findByName(name);
		for (Interest interest : foundBoard.getInterests()) {
			String hashUser = interest.getHashUser();
			if (StringUtils.isEmpty(hashUser) || !hashUser.contains("@")) {
				logger.error("El interés solicitado está vacio o no contiene el Hash correspondiente");
				return;
			}
			
			String[] interestComponents = hashUser.split("@");
			String interestUser = interestComponents[0];
			String socialNetwork = interestComponents[1];
			if (socialNetwork.equalsIgnoreCase("TWITTER")) {
				twitterListener.listeningUser(interestUser);
			}
		}
			
	}
}
