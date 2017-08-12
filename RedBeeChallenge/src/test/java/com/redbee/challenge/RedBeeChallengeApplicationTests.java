package com.redbee.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redbee.challenge.dao.BoardRepository;
import com.redbee.challenge.model.Board;
import com.redbee.challenge.model.Interest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedBeeChallengeApplicationTests {

	@Autowired
	BoardRepository repository;


	@Before
	public void setUp() {

		repository.deleteAll();

		// save a couple of customers
		List<Interest> interests = new ArrayList<Interest>();
		Interest interest = new Interest("lean@Twitter");
		interests.add(interest);
		repository.save(new Board("pepe", interests));

	}


	@Test
	public void findsByLastName() {

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		//			for (Board customer : repository.findAll()) {
		//				System.out.println(customer);
		//			}
		//			System.out.println();
		//
		//			// fetch an individual customer
		//			System.out.println("Customer found with findByFirstName('Alice'):");
		//			System.out.println("--------------------------------");
		//			System.out.println(repository.findByName("pepe"));	       

		assertThat(repository.findAll()).hasSize(1).extracting("name").contains("pepe");
	}
}
