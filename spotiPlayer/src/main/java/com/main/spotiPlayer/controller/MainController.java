package com.main.spotiPlayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

import com.main.spotiPlayer.classes.TestTable;
import com.main.spotiPlayer.classes.UserClass;
import com.main.spotiPlayer.dao.MainDao;

//import com.main.spotiPlayer.dao.MainDao;

@RestController
public class MainController implements MainDao {

	@GetMapping("/")
	 public ModelAndView index(Model model) {
		ModelAndView map = new ModelAndView("/index");
        String message = "Добро пожаловать в супер крутое и невероятное Web-приложение";
        map.addObject("message", message);
        return map;
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
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView loginPage(Model model) {
		ModelAndView map = new ModelAndView("loginPage");
		System.out.println("dasdasdadas1");
        return map;
    } 
	
	@Secured("ROLE_USER")
	@RequestMapping(value = {"/auth/main"}, method = RequestMethod.GET)
    public ModelAndView main(Model model) {
		ModelAndView map = new ModelAndView("main");
        String message = "Аутенцификация завершена!";
        map.addObject("message", message);
        System.out.println("dasdasdadas");
        return map;
    }
	

	
}