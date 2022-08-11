package com.main.spotiPlayer.dao;

import com.main.spotiPlayer.classes.TestTable;
import com.main.spotiPlayer.classes.UserClass;

public interface MainDao {
	public static TestTable testTablerowMapper(int id, int cnt, String name, double kall){ 
		TestTable result = new TestTable();
			result.setId(id);
			result.setCount(cnt);
			result.setName(name);
			result.setKey(kall);		
		return result;
	}
	
	public static UserClass usersRowMapper(int id, String login, String password, String role){ 
		UserClass result = new UserClass();
			result.setId(id);
			result.setLogin(login);
			result.setPassword(password);
			result.setRole(role);
		return result;
	}
}
