package com.main.spotiPlayer.dao;

import com.main.spotiPlayer.classes.TestTable;

public interface MainDao {
	public static TestTable testTablerowMapper(int id, int cnt, String name, double kall){ 
		TestTable result = new TestTable();
			result.setId(id);
			result.setCount(cnt);
			result.setName(name);
			result.setKey(kall);		
		return result;
	}
}
