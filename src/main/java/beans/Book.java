package beans;

import java.io.Serializable;

public class Book implements Serializable {
	private int bookID;
	private String title;
	private String author;
	private String publisher;
	private String bookISBN;
	private String imageURL;
	private String description;
	private String readStatus;
	private int evaluation;
	private String memo;
	
	public Book(String title) {
		this.title = title;
	}

	public Book(String title, String author, String publisher, String readStatus, int evaluation, String memo) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.readStatus = readStatus;
		this.evaluation = evaluation;
		this.memo = memo;
	}

	public Book(String title, String author, String publisher, String bookISBN, String imageURL, String readStatus, int evaluation, String memo) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookISBN = bookISBN;
		this.imageURL = imageURL;
		this.readStatus = readStatus;
		this.evaluation = evaluation;
		this.memo = memo;
	}

	public Book(int bookID, String title, String author, String publisher, String bookISBN, String imageURL) {
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookISBN = bookISBN;
		this.imageURL = imageURL;
	}
	
	public Book(int bookID, String title, String author, String publisher, String readStatus, int evaluation,
			String memo) {
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.readStatus = readStatus;
		this.evaluation = evaluation;
		this.memo = memo;
	}

	public Book(int bookID, String title, String author, String publisher, String bookISBN, String imageURL,
			String readStatus, int evaluation, String memo) {
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookISBN = bookISBN;
		this.imageURL = imageURL;
		this.readStatus = readStatus;
		this.evaluation = evaluation;
		this.memo = memo;
	}
	
	public Book(String title, String author, String publisher, String bookISBN, String imageURL, String description) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookISBN = bookISBN;
		this.imageURL = imageURL;
		this.description = description;
	}

	public int getID() {return bookID;}
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public String getPublisher() {return publisher;}
	public String getISBN() {return bookISBN;}
	public String getImageURL() {return imageURL;}
	public String getDescription() {return description;}
	public String getReadStatus() {return readStatus;}
	public int getEvaluation() {return evaluation;}
	public String getMemo() {return memo;}
	
	public void setID(int bookID) {this.bookID = bookID;}
	public void setDescription(String description) {this.description = description;}
	public void setReadStatus(String readStatus) {this.readStatus = readStatus;}
	public void setEvaluation(int evaluation) {this.evaluation = evaluation;}
	public void setMemo(String memo) {this.memo = memo;}
	
}
