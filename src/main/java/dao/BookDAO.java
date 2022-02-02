package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import beans.Book;
import dao.environment.DBEnv;

public class BookDAO implements DBEnv{
	public BookDAO() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("newで例外");
			e.printStackTrace();
		}
	}
	
	public Book findByID(int bookID){
		Book book = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT * FROM BOOK WHERE bookID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bookID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("imageURL"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return book;
	}
	
	public Book findByISBN(String bookISBN){
		Book book = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "SELECT * FROM BOOK WHERE ISBN = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, bookISBN);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("imageURL"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return book;
	}

	public int insert(Book book) {
		int bookID = 0;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "INSERT INTO BOOK(title, author, publisher, ISBN, imageURL) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, book.getTitle());
			pStmt.setString(2, book.getAuthor());
			pStmt.setString(3, book.getPublisher());
			pStmt.setString(4, book.getISBN());
			pStmt.setString(5, book.getImageURL());
			int result = pStmt.executeUpdate();
			sql = "SELECT * FROM BOOK WHERE bookID = (SElECT MAX(bookID) FROM BOOK)";
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				bookID = rs.getInt("bookID");
			}
			if(result != 1) { return 0; }
		}catch(SQLIntegrityConstraintViolationException e) {
			try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
				String sql = "SELECT * FROM BOOK WHERE ISBN = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, book.getISBN());
				ResultSet rs = pStmt.executeQuery();
				while(rs.next()) {
					bookID = rs.getInt("bookID");
				}
			}catch(SQLException exception) {
				exception.printStackTrace();
				return 0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return bookID;
	}

	public boolean update(Book book) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "UPDATE BOOK SET title=?, author=?, publisher=?, ISBN=?, imageURL=? WHERE bookID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, book.getTitle());
			pStmt.setString(2, book.getAuthor());
			pStmt.setString(3, book.getPublisher());
			pStmt.setString(4, book.getISBN());
			pStmt.setString(5, book.getImageURL());
			pStmt.setInt(6, book.getID());
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(int bookID) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
			String sql = "DELETE FROM BOOK WHERE bookID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bookID);
			int result = pStmt.executeUpdate();
			if(result != 1) { return false; }
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}