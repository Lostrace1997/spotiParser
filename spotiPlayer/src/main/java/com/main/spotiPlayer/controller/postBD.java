package com.main.spotiPlayer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.main.spotiPlayer.SpotiPlayerApplication;
import com.main.spotiPlayer.classes.UserClass;
import com.main.spotiPlayer.dao.MainDao;


public class postBD {
	
	
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection = null;
	
	private static void config() {
		try (InputStream input = SpotiPlayerApplication.class.getClassLoader().getResourceAsStream("application.properties")) {

	        Properties prop = new Properties();
	        prop.load(input);

	        url = prop.getProperty("spring.datasource.url");
	        username = prop.getProperty("spring.datasource.username");
	        password = prop.getProperty("spring.datasource.password");


	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void testConnect() {
		
		config();
		System.out.println("Testing connection to PostgreSQL JDBC");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
			return;
		}
		
		System.out.println("PostgreSQL JDBC Driver successfully connected");

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			return;
		}
		
		if (connection != null) {
			System.out.println("You successfully connected to database now");
		} else {
			System.out.println("Failed to make connection to database");
		}		
	}
	
	public static ResultSet quary(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}
	
//	public static ResultSet quaryDataJson(String sql) throws SQLException {
//		
//		ResultSet rs = connection.createStatement().executeQuery(sql);
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int column_count = rsmd.getColumnCount();
//		
//		while (data.next()) {
//            int id = rs.getInt("id");
//            String login = rs.getString("login");
//            String password = data.getString("password");
//            String role = data.getString("role");
//            
//            table.add(MainDao.usersRowMapper(id, login, password, role));
//
//        }
//		
//		//int resCntCol = result.get
//		
//		return 
//	}
	
	public static void update(String sql) throws SQLException {
		connection.createStatement().executeUpdate(sql);
	}
	
	public static String userRowMapper(ResultSet data) throws SQLException, JsonProcessingException {
		List<UserClass> table = new ArrayList<>(); 
				
		while (data.next()) {
            int id = data.getInt("id");
            String login = data.getString("login");
            String password = data.getString("password");
            String role = data.getString("role");
            
            table.add(MainDao.usersRowMapper(id, login, password, role));

        }
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(table);
		return json;

	}
	
	 public static void userAdd(String userLogin, String userPassword, String userRole) throws SQLException {
		
		String login = userLogin;
		String password = userPassword;
		String role = userRole;
		
		
		
		ResultSet rsLogin = quary("select "
				+ "	case when u.cnt = 0 then 'false' "
				+ "	else 'true' "
				+ "	end exist "
				+ "from (select count(login) as cnt from users where login = '"+login+"') u");
		rsLogin.next();
		String loginExist = rsLogin.getString("exist");
		
		if (loginExist.equals("true")) {
			System.out.println("Такой пидр уже существует, придумай другой, еблан");
	 	} else { 
	 		ResultSet rsId = quary("select MAX (id)+1 as id from users"); 
	 		rsId.next();
			int newId = rsId.getInt("id");
			update("INSERT INTO users (id, login, password, role)" + "VALUES ('" + newId + "', '" + login + "', '" + password + "', '" + role + "'); commit;");
			System.out.println("Пидр добавлен");
	 	}
	
	 }
}
