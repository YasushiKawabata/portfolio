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
import dao.UserBookDAO;
import dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String forwardPath = "";
		if(action == null) {
			forwardPath = "/WEB-INF/jsp/login.jsp";
		}else if(action.equals("register")) {
			forwardPath = "/WEB-INF/jsp/user_register.jsp";
		}else if(action.equals("check")) {
			forwardPath = "/WEB-INF/jsp/user_check.jsp";
		}else if(action.equals("logout")){
			request.getSession().invalidate();
			forwardPath = "/index.jsp";
		}else {
			forwardPath = "/WEB-INF/jsp/login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String mail = request.getParameter("user_regiID");
		String pass = request.getParameter("user_regiPass");
		String userName = request.getParameter("user_regiUser");
		String secretQuestion = request.getParameter("user_secretQ");
		String secretAnswer = request.getParameter("user_secretA");
		String forwardPath = "";
		String errorMsg = "";
		if(action.equals("login")) {
			if(mail.isEmpty() || pass.isEmpty()) {
				errorMsg = "未入力の項目があります。";
				request.setAttribute("errorMsg", errorMsg);
				forwardPath = "/WEB-INF/jsp/login.jsp";
			}else {
				UserDAO ud = new UserDAO();
				User user = ud.findByMail(mail);
				if(user == null || !user.getPass().equals(pass)) {
					errorMsg = "IDかパスワードが間違っています。";
					request.setAttribute("errorMsg", errorMsg);
					forwardPath = "/WEB-INF/jsp/login.jsp";
				}else {
					List<Book> bookList = new UserBookDAO().findByUser(user);
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(30*60);
					session.setAttribute("user", user);
					session.setAttribute("bookList", bookList);
					forwardPath = "/WEB-INF/jsp/main.jsp";
				}
			}
		}else if(action.equals("register")) {
			if(mail.isEmpty() || userName.isEmpty() || pass.isEmpty()) {
				errorMsg = "未入力の項目があります。";
				request.setAttribute("errorMsg", errorMsg);
				forwardPath = "/WEB-INF/jsp/user_register.jsp";
			}else if(mail.length() > 50 || userName.length() > 20 || pass.length() > 8 ||
					secretQuestion.length() > 100 || secretAnswer.length() > 60) {
				errorMsg = "入力文字数が長すぎる項目があります。idは50文字以内、"
						+ "ユーザー名は20文字以内、パスワードは8文字以内、"
						+ "秘密の答えは30文字以内（半角入力なら60文字以内）で入力してください";
				request.setAttribute("errorMsg", errorMsg);
				forwardPath = "/WEB-INF/jsp/user_register.jsp";
			}else {
				UserDAO ud = new UserDAO();
				User user = ud.findByMail(mail);
				if(user != null){
					errorMsg = "すでに同じIDが存在します。別のIDを入力してください。";
					request.setAttribute("errorMsg", errorMsg);
					forwardPath = "/WEB-INF/jsp/user_register.jsp";
				}else {
					user = new User(mail, pass, userName, Integer.parseInt(secretQuestion), secretAnswer);
					ud.insert(user);
					user = ud.findByMail(mail);
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					forwardPath = "/WEB-INF/jsp/user_regiResult.jsp";
				}
			}
		}else if(action.equals("check")) {
			if(mail.isEmpty() || secretQuestion.isEmpty() || secretAnswer.isEmpty()) {
				errorMsg = "未入力の項目があります。";
				request.setAttribute("errorMsg", errorMsg);
				forwardPath = "/WEB-INF/jsp/user_check.jsp";
			}else {
				UserDAO dao = new UserDAO();
				User user = dao.findByMail(mail);
				if(user == null) {
					errorMsg = "このメールアドレスは登録されていません。";
					request.setAttribute("errorMsg", errorMsg);
					forwardPath = "/WEB-INF/jsp/user_check.jsp";
				}else if(!(user.getSecretQuestionId() == Integer.parseInt(secretQuestion)) || !user.getAnswer().equals(secretAnswer)) {
					errorMsg = "入力に間違いがあります。";
					request.setAttribute("errorMsg", errorMsg);
					forwardPath = "/WEB-INF/jsp/user_check.jsp";
				}else {
					request.setAttribute("user", user);
					forwardPath = "/WEB-INF/jsp/user_checkResult.jsp";
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
