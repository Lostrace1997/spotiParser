package com.main.spotiPlayer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

import com.main.spotiPlayer.classes.TestTable;
import com.main.spotiPlayer.dao.MainDao;

//import com.main.spotiPlayer.dao.MainDao;
@RestController
public class MainController implements MainDao {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	
	public int data = 0;
	@GetMapping("/counter")
	public String counter() {
		data += 1;
		String itemId = String.valueOf(data);
		return itemId;
	}
	
	@GetMapping("/testTable")
	public String testTable() throws JsonProcessingException {
		List<TestTable> table = new ArrayList<>(); 
		
		table.add(MainDao.testTablerowMapper(1, 11, "Fiksik", 0.1));
		table.add(MainDao.testTablerowMapper(1, 11, "Fiksik", 0.1));
		table.add(MainDao.testTablerowMapper(1, 11, "Fiksik", 0.1));
		table.add(MainDao.testTablerowMapper(1, 11, "Fiksik", 0.1));
		table.add(MainDao.testTablerowMapper(1, 11, "Fiksik", 0.1));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(table);
		
		return json ;
	}

}