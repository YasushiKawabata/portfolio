package beans;

import java.io.Serializable;

public class User implements Serializable {
	private int userID;
	private String mail;
	private String pass;
	private String userName;
	private int secretQuestionId;
	private String answer;
	
	public User(String mail, String pass, String name) {
		this.mail = mail;
		this.pass = pass;
		this.userName = name;
	}
	public User(String mail, String pass, String userName, int secretQuestionId, String answer) {
		this.mail = mail;
		this.pass = pass;
		this.userName =userName;
		this.secretQuestionId = secretQuestionId;
		this.answer = answer;
	}
	public User(int id, String mail, String pass, String userName, int secretQuestionId, String answer) {
		this.userID = id;
		this.mail = mail;
		this.pass = pass;
		this.userName =userName;
		this.secretQuestionId = secretQuestionId;
		this.answer = answer;
	}
	public int getID() {return userID;}
	public String getMail() {return mail;}
	public String getPass() {return pass;}
	public String getUserName() {return userName;}
	public int getSecretQuestionId() {return secretQuestionId;}
	public String getAnswer() {return answer;}
	
}