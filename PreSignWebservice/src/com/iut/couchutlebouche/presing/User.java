package com.iut.couchutlebouche.presing;

public class User {
	
	/** User login*/
	public String username;
	/** User password*/
	public String userpassword;

	/**
	 * Construct a new User with a parameter <code>username</code> and <code>userpasswrod</code>
	 * @param username
	 * @param userpassword
	 */
	public User(String username, String userpassword) {
		super();
		this.username = username;
		this.userpassword = userpassword;
	}


}
