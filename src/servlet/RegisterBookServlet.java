package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.ApplicationServlet;
import app.Book;
import model.GetGenreIdLogic;
import model.RegisterBookLogic;

@WebServlet("/RegisterBookServlet")
public class RegisterBookServlet extends ApplicationServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//フォワード先
		String forwardPath = null;

		String action = request.getParameter("action");

		//登録開始時の処理
		if(action == null || "clear".equals(action)){
			HttpSession session = request.getSession();
			Book registerBook = (Book) session.getAttribute("registerBook");
			if(registerBook!=null){
				registerBook=null;
				session.setAttribute("registerBook", null);
			}
			forwardPath="/WEB-INF/registerBookForm.jsp";
		}
		//登録実行をリクエストされた時の処理
		else if(action.equals("done")){
			//セッションに保存された登録図書を取得
			HttpSession session = request.getSession();
			Book registerBook = (Book) session.getAttribute("registerBook");

			//registerBook.setBookId(null);

			//登録処理
			RegisterBookLogic logic = new RegisterBookLogic();
			logic.execute(registerBook);

			//不要になったインスタンス消去
			session.removeAttribute("registerBook");

			//フォワード先
			forwardPath = "/WEB-INF/registerBookDone.jsp";
		}else if(action.equals("back")){
			forwardPath="/WEB-INF/registerBookForm.jsp";
		}

		//フォワード実行
		System.out.println("forwardPath:"+forwardPath);
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		System.out.println("dispatcher:"+dispatcher);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//リクエストパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String genre_name = new String(request.getParameter("genre_name").getBytes("ISO-8859-1"), "UTF-8");
		String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
		String author = new String(request.getParameter("author").getBytes("ISO-8859-1"), "UTF-8");
		String publisher = new String(request.getParameter("publisher").getBytes("ISO-8859-1"), "UTF-8");
		String pub_year = new String(request.getParameter("pub_year").getBytes("ISO-8859-1"), "UTF-8");
		Date reg_date = new Date();
		String ISBN = new String(request.getParameter("ISBN").getBytes("ISO-8859-1"), "UTF-8");
		String comment = new String(request.getParameter("comment").getBytes("ISO-8859-1"), "UTF-8");
		Integer genre_id;

		//System.out.println("title:"+request.getParameter("title"));

		Book registerBook = new Book();
		registerBook.setGenreName(genre_name);
		registerBook.setTitle(title);
		registerBook.setAuthor(author);
		registerBook.setPublisher(publisher);
		registerBook.setPubYear(pub_year);
		registerBook.setRegDate(reg_date);
		registerBook.setIsbn(ISBN);
		registerBook.setComment(comment);

		if(!genre_name.equals("") && !title.equals("") && !author.equals("") && !publisher.equals("") && !pub_year.equals("") && !ISBN.equals("") &&  !comment.equals("")){
			//入力フォームが正常のとき

			System.out.println("test1");



			//ジャンルID取得
			GetGenreIdLogic GenreLogic = new GetGenreIdLogic();
			genre_id=GenreLogic.execute(registerBook);
			registerBook.setGenreId(genre_id);





			//セッションに図書情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("registerBook", registerBook);

			//フォワード
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("/WEB-INF/registerBookConfirm.jsp");
			dispatcher.forward(request, response);
		}else{
			//入力に不備があるとき

			//エラー文字を表示
			request.setAttribute("isError", true);


			//セッションに図書情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("registerBook", registerBook);

			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/registerBookForm.jsp");
			dispatcher.forward(request, response);
		}



	}
}
