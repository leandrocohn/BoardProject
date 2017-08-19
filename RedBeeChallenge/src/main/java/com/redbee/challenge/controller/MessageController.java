package com.redbee.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.challenge.exceptions.BoardDAOException;
import com.redbee.challenge.messages.InterestMessage;
import com.redbee.challenge.services.BoardService;

@RestController
@EnableAutoConfiguration
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private BoardService boardService;

	@MessageMapping("/message")
	public void getInterest(InterestMessage message) throws InterruptedException {
		logger.debug("Obteniendo los intereses de " + message.getMessage());

		try {
			boardService.getInterestByBoardName(message.getMessage());
		} catch (BoardDAOException e) {
			logger.debug(e.getMessage());
		}
	}
}
