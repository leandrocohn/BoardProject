package com.redbee.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.redbee.challenge.model.Board;

public interface BoardRepository extends MongoRepository<Board, String> {

	/**
	 * Find a board by a name
	 * @param name
	 * @return
	 */
	public Board findByName(String name);

}
