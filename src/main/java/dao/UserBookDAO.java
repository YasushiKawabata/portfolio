package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.User;
import dao.environment.DBEnv;

public class UserBookDAO implements DBEnv{
	public UserBookDAO() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("newで例外");
			e.printStackTrace();
		}
	}
	
//	public Book findByISBN(int bookISBN){
//		Book book = null;
//		try(Connection conn = DriverManager.getConnection(JSBC_URL, DB_USER, DB_PASS);) {
//			String sql = "SELECT * FROM BOOK WHERE ISBN = ?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, bookISBN);
//			ResultSet rs = pStmt.executeQuery();
//			while(rs.next()) {
//				book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getInt("ISBN"), rs.getString("imageURL"));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return book;
//	}

	public boolean insert(User user, Book book) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "INSERT INTO USER_BOOK VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getID());
			pStmt.setInt(2, book.getID());
			pStmt.setString(3, book.getReadStatus());
			pStmt.setInt(4, book.getEvaluation());
			pStmt.setString(5, book.getMemo());
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(int userID, Book book) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
//			String sql = "UPDATE (SELECT title, author, publisher, ISBN, imageURL, readStatus, evaluation, memo FROM USER_BOOK JOIN BOOK ON USER_BOOK.bookID = BOOK.bookID WHERE userID = ? AND USER_BOOK.bookID = ?) AS SUB SET title=?, author=?, publisher=?, ISBN=?, imageURL=?, readStatus=?, evaluation=?, memo=?";
			String sql = "UPDATE USER_BOOK SET readStatus=?, evaluation=?, memo=? WHERE userID = ? AND bookID = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, book.getReadStatus());
			pStmt.setInt(2, book.getEvaluation());
			pStmt.setString(3, book.getMemo());
			pStmt.setInt(4, userID);
			pStmt.setInt(5, book.getID());
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int userID, int bookID) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "DELETE FROM USER_BOOK WHERE userID = ? AND bookID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userID);
			pStmt.setInt(2, bookID);
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Book> findByUser(User user){
		List<Book> bookList = new ArrayList<Book>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT USER_BOOK.bookID, title, author, publisher, ISBN, imageURL, readStatus, evaluation, memo FROM USER_BOOK JOIN BOOK ON USER_BOOK.bookID = BOOK.bookID WHERE userID = ? ORDER BY bookID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getID());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				bookList.add(new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("imageURL"), rs.getString("readStatus"), rs.getInt("evaluation"), rs.getString("memo")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return bookList;
	}
	
	public Book findByBookID(User user, int bookID){
		Book book = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT USER_BOOK.bookID, title, author, publisher, ISBN, imageURL, readStatus, evaluation, memo FROM USER_BOOK JOIN BOOK ON USER_BOOK.bookID = BOOK.bookID WHERE userID = ? AND USER_BOOK.bookID = ? ORDER BY bookID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getID());
			pStmt.setInt(2, bookID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("imageURL"), rs.getString("readStatus"), rs.getInt("evaluation"), rs.getString("memo"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return book;
	}
	
	public List<Book> findByTitle(User user, String title){
		List<Book> bookList = new ArrayList<Book>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT USER_BOOK.bookID, title, author, publisher, ISBN, imageURL, readStatus, evaluation, memo FROM USER_BOOK JOIN BOOK ON USER_BOOK.bookID = BOOK.bookID WHERE userID = ? AND title LIKE ? ORDER BY bookID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getID());
			pStmt.setString(2, "%"+title+"%");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				bookList.add(new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("imageURL"), rs.getString("readStatus"), rs.getInt("evaluation"), rs.getString("memo")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return bookList;
	}

}