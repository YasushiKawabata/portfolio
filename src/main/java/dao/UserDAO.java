package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
import dao.environment.DBEnv;

public class UserDAO implements DBEnv{
	public UserDAO() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("newで例外");
			e.printStackTrace();
		}
	}
	
	public boolean execute() {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			System.out.println("成功");
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insert(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "INSERT INTO USER(mail, pass, name, secretQuestionID, secretQuestionAnswer) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getMail());
			pStmt.setString(2, user.getPass());
			pStmt.setString(3, user.getUserName());
			pStmt.setInt(4, user.getSecretQuestionId());
			pStmt.setString(5, user.getAnswer());
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public User findByMail(String mail){
		User user = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT * FROM USER WHERE mail = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mail);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("userID"), rs.getString("mail"), rs.getString("pass"), rs.getString("name"), rs.getInt("secretQuestionID"), rs.getString("secretQuestionAnswer"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}