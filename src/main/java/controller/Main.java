package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.User;
import dao.BookDAO;
import dao.UserBookDAO;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    public void init(ServletConfig config) throws ServletException {
//    	super.init(config);
//    	
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String forwardPath = "";
		if(action == null){
			User user = (User)session.getAttribute("user");
			List<Book> bookList = new UserBookDAO().findByUser(user);
			session.setAttribute("bookList", bookList);
			forwardPath = "/WEB-INF/jsp/main.jsp";
		}else if(action.equals("insert")) {
			forwardPath = "/WEB-INF/jsp/insert.jsp";
		}else if(action.equals("insertByHand")) {
			forwardPath = "/WEB-INF/jsp/insertByHand.jsp";
		}else if(action.equals("insertBySearch")) {
			session.setAttribute("list", null);
			forwardPath = "/WEB-INF/jsp/insertBySearch.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String action = request.getParameter("action");
		String forwardPath = "";
		String errorMsg = "";
		
		if(action.equals("insertByHand")) {
			String title = request.getParameter("title");
			if(title.equals("")) {
				request.setAttribute("errorMsg", "タイトルが入力されていません");
				request.getRequestDispatcher("/WEB-INF/jsp/insert.jsp").forward(request, response);
			}else {
				Book book = new Book(title, request.getParameter("author"), request.getParameter("publisher"), request.getParameter("readStatus"), Integer.parseInt(request.getParameter("evaluation")), request.getParameter("memo"));
				BookDAO bd = new BookDAO();
				book.setID(bd.insert(book));
				UserBookDAO ubd = new UserBookDAO();
				ubd.insert(user, book);
				forwardPath = "/WEB-INF/jsp/insertResult.jsp";
			}
		}else if(action.equals("insertBySearch")){
			String button = request.getParameter("button_name");
			if(button.equals("検索")){
				session.setAttribute("list", null);
				String isbn = request.getParameter("isbn");
				String title = request.getParameter("title");
				List<Book> list = new Search().execute(isbn, title);
				if(list == null) {
					errorMsg = "検索結果はありません。";
					request.setAttribute("errorMsg", errorMsg);
				}else {
					session.setAttribute("list", list);
				}
				forwardPath = "/WEB-INF/jsp/insertBySearch.jsp";
			}else {
				int no = Integer.parseInt(request.getParameter("no"));
				@SuppressWarnings("unchecked")
				List<Book> list = (List<Book>) session.getAttribute("list");
				Book book = list.get(no-1);
				BookDAO bd = new BookDAO();
				book.setID(bd.insert(book));
				request.setAttribute("book", book);
				forwardPath = "/WEB-INF/jsp/insertBySearchEdit.jsp";
			}
		}else if(action.equals("insertBySearchEdit")) {
			Book book = new BookDAO().findByID(Integer.parseInt(request.getParameter("bookId")));
			book.setReadStatus(request.getParameter("readStatus"));
			book.setEvaluation(Integer.parseInt(request.getParameter("evaluation")));
			book.setMemo(request.getParameter("memo"));
			UserBookDAO ubd = new UserBookDAO();
			ubd.insert(user, book);
			forwardPath = "/WEB-INF/jsp/insertResult.jsp";
		}else if(action.equals("detail")) {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
//			System.out.println(bookId);
			if(request.getParameter("bookNo") != null) {
			session.setAttribute("bookNo", request.getParameter("bookNo"));
			}
			UserBookDAO ubd = new UserBookDAO();
			Book book = ubd.findByBookID(user, bookId);
			if(book.getISBN() != null) {
				String isbn = book.getISBN();
				String title = book.getTitle();
				Search search = new Search();
				List<Book> listISBN = search.execute(isbn, "");
				List<Book> listTitle = search.execute("", title);
				here: for(int i=0; i<2; i++) {
					if(i == 0 && listISBN != null && !isbn.equals("未登録")) {
						for(Book tmpBook: listISBN) {
							if(book.getAuthor().equals(tmpBook.getAuthor()) && book.getTitle().equals(tmpBook.getTitle())) {
								book.setDescription(tmpBook.getDescription());
								break here;
							}
						}
					}else if(i == 1 && listTitle != null) {
						for(Book tmpBook: listTitle) {
							if(book.getAuthor().equals(tmpBook.getAuthor()) && book.getTitle().equals(tmpBook.getTitle())) {
								book.setDescription(tmpBook.getDescription());
								break here;
							}
						}
					}
				}
			}
			session.setAttribute("book", book);
			forwardPath = "/WEB-INF/jsp/detail.jsp";
		}else if(action.equals("delete")) {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			UserBookDAO ubd =new UserBookDAO();
			Book book = ubd.findByBookID(user, bookId);
			ubd.delete(user.getID(), bookId);
			if(book.getISBN() == null) {
				BookDAO bd = new BookDAO();
				bd.delete(bookId);
			}
			forwardPath = "/WEB-INF/jsp/deleteResult.jsp";
		//detail -> edit
		}else if(action.equals("update")) {
//			int bookId = Integer.parseInt(request.getParameter("bookId"));
//			UserBookDAO dao = new UserBookDAO();
//			Book book = dao.findByBookID(user, bookId);
//			request.setAttribute("book", book);
			forwardPath = "/WEB-INF/jsp/edit.jsp";
		//edit -> 
		}else if(action.equals("edit")) {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book(bookId, request.getParameter("titleName"), request.getParameter("author"), request.getParameter("publisher"), request.getParameter("readStatus"), Integer.parseInt(request.getParameter("evaluation")), request.getParameter("memo"));
			new UserBookDAO().update(user.getID(), book);
			new BookDAO().update(book);
			request.setAttribute("book", book);
			forwardPath = "/WEB-INF/jsp/editResult.jsp";
		}else if(action.equals("search")) {
			String searchWard = request.getParameter("text");
			request.setAttribute("text", searchWard);
			UserBookDAO ubd = new UserBookDAO();
			List<Book> bookList = ubd.findByTitle(user, searchWard);
			request.setAttribute("bookList", bookList);
			forwardPath = "/WEB-INF/jsp/main.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
