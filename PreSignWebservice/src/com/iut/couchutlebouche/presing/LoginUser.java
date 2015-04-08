package com.iut.couchutlebouche.presing;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONWriter;

public class LoginUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("login");
		String password = request.getParameter("password");
		User user = new User(username, password);

		JSONWriter writer = new JSONWriter(response.getWriter());
		try {
			User usr = this.testUserLogin(user);
			// Check is user is null. Because if user is name.
			if (usr == null) {
				// If user is null we send and string to let know user is null
				writer.object();
				writer.key("loginStatus").value("no");
				writer.endObject();
			} else {
				// Else user is not null parse it

				// Transforme a model user to parson object
				ModelToJsonFormat.userToJson(user, writer);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * Get an Instance SQL connection
	 * 
	 * @return Object Connection
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Connection test = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			test = DriverManager.getConnection("jdbc:mysql://"
					+ ShareData.address + "/" + ShareData.DBNAME + "?"
					+ "user=" + ShareData.DBLOGIN + "&password="
					+ ShareData.DBPASSWORD);

		} catch (Exception e) {

			e.getMessage();
		}
		// return
		// DriverManager.getConnection("jdbc:mysql://"+ShareData.address+"/"+ShareData.DBLOGIN,
		// ShareData.DBNAME, ShareData.DBPASSWORD);

		return test;
	}

	/**
	 * Verifie the user parameter if its correct
	 * 
	 * @param user
	 *            : User to checik
	 * @return the user checket
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private User testUserLogin(User user) throws Exception,
			IllegalAccessException, ClassNotFoundException, SQLException {

		// The table name is : usertable and it has two columm : username and
		// userpassword
		User usr = null;
		String requestSQL = "select *from usertable where username='"
				+ user.username + "' and userpassword='" + user.userpassword
				+ "'";
		Connection connection = getConnect();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(requestSQL);
			while (resultSet.next()) {
				// the get User parameter in the database
				usr = new User(resultSet.getString("username"),
						resultSet.getString("userpassword"));
			}

		} catch (Exception e) {

			e.getStackTrace();
			System.out.println("erreur");
		}

		return usr;
	}

//	private User testUserInsert(User user) throws Exception,
//					IllegalAccessException,ClassNotFoundException, SQLException{
//		testUserLogin(user);
//		
//		User usr = null;
//		String requestSQL = "INSERT INTO  bdlivres.usertable(username ,userpassword)"
//				+ "VALUES ('"
//				+ user.username +"','" + user.userpassword+"');"; 
//				//+ "select *from usertable where username='"
//				//+ user.username + "' and userpassword='" + user.userpassword
//				//+ "'";
//		Connection connection = getConnect();
//
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(requestSQL);
//			if (resultSet != null) {
//				System.out.println("ok");
//			}
//			///while (resultSet.next()) {
//				// the get User parameter in the database
//			//	usr = new User(resultSet.getString("username"),
//			//			resultSet.getString("userpassword"));
//			//}
//			else {
//				System.out.println("pas ok");
//				
//			}
//		} catch (Exception e) {
//
//			e.getStackTrace();
//			//System.out.println("erreur");
//		}
//
//		return usr;
//		
//		
//		
//		
//		
//	}


}
