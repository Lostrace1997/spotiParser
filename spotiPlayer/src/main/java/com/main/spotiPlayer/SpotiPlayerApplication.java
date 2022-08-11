package com.main.spotiPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.spotiPlayer.controller.postBD;
import com.main.spotiPlayer.classes.UserClass;
import com.main.spotiPlayer.dao.MainDao;

@SpringBootApplication
public class SpotiPlayerApplication {

	public static void main(String[] args) throws SQLException, JsonProcessingException {
		SpringApplication.run(SpotiPlayerApplication.class, args);
		
		postBD.testConnect();
		
		//postBD.userAdd("Pupsick","bibochka","user");
		//ResultSet result = postBD.quary("select id, login, password, role from users");
		//String res = postBD.userRowMapper(result);
		//System.out.println(res);
	}
}
