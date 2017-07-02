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
import model.DeleteBookLogic;
import model.EditBookLogic;
import model.GetBookInf;
import model.GetGenreIdLogic;
import model.GetGenreNameLogic;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends ApplicationServlet {
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

		//編集開始時の処理
		if(action == null){
			//bookを定義
			Book registerBook = new Book();
//			HttpSession session = request.getSession();


//			Book registerBook = new Book();
//			registerBook.setBookId(2);

//			registerBook.setBookId(Integer.parseInt(request.getParameter("book_id")));

			//bookIdから図書情報を取得
			GetBookInf getInf = new GetBookInf();
			registerBook=getInf.execute(Integer.parseInt(request.getParameter("book_id")));

//			//図書情報を取得
//			GetBookInf getInf = new GetBookInf();
//			registerBook=getInf.execute(registerBook.getBookId());

			//ジャンル名を取得
			System.out.println(registerBook);
			GetGenreNameLogic NameLogic = new GetGenreNameLogic();
			registerBook.setGenreName(NameLogic.execute(registerBook));


			//セッションに図書情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("registerBook", registerBook);

			forwardPath="/WEB-INF/editBookForm.jsp";
		}
		//登録実行をリクエストされた時の処理
		else if(action.equals("done")){
			//セッションに保存された登録図書を取得
			HttpSession session = request.getSession();
			Book registerBook = (Book) session.getAttribute("registerBook");


			System.out.println("11111111111111111111111111111registerBook:"+registerBook);
			//登録処理
			EditBookLogic EditLogic = new EditBookLogic();
			EditLogic.execute(registerBook);

			//不要になったインスタンス消去
			session.removeAttribute("registerBook");

			//フォワード先
			forwardPath = "/WEB-INF/editBookDone.jsp";
		}else if(action.equals("back")){
			forwardPath="/WEB-INF/editBookForm.jsp";
		}else if(action.equals("delete")){
			//セッションに保存された登録図書を取得
			HttpSession session = request.getSession();
			Book registerBook = (Book) session.getAttribute("registerBook");

			//削除処理
			DeleteBookLogic DeleteLogic = new DeleteBookLogic();
			DeleteLogic.execute(registerBook);
			forwardPath="/WEB-INF/deleteBookDone.jsp";
		}

//		else if (action.equals("back")){
//			HttpSession session = request.getSession();
//			Book registerBook = (Book) session.getAttribute("registerBook");
//System.out.println(":::::::::::::::::::::::::::::::::::registerBook:"+registerBook);
//			//図書情報を取得
//			GetBookInf getInf = new GetBookInf();
//			registerBook=getInf.execute(registerBook.getBookId());
//			//ジャンル名を取得
//
//			System.out.println(registerBook);
//			GetGenreNameLogic NameLogic = new GetGenreNameLogic();
//			registerBook.setGenreName(NameLogic.execute(registerBook));
//
//			//セッションに図書情報を保存
////			HttpSession session = request.getSession();
//			session.setAttribute("registerBook", registerBook);
//
//			forwardPath="/WEB-INF/editBookForm.jsp";
//
//		}

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
		Integer book_id = Integer.parseInt(new String(request.getParameter("book_id").getBytes("ISO-8859-1"), "UTF-8"));

		//System.out.println("title:"+request.getParameter("title"));

		Book registerBook = new Book();
//		//図書情報を取得
//		GetBookInf getInf = new GetBookInf();
//		registerBook=getInf.execute(registerBook.getBookId());

		registerBook.setGenreName(genre_name);
		registerBook.setTitle(title);
		registerBook.setAuthor(author);
		registerBook.setPublisher(publisher);
		registerBook.setPubYear(pub_year);
		registerBook.setRegDate(reg_date);
		registerBook.setIsbn(ISBN);
		registerBook.setComment(comment);
		registerBook.setBookId(book_id);

		if(genre_name != "" && title != "" && author!="" && publisher!="" && pub_year!="" && ISBN!="" &&  comment!=""){
			//入力フォームが正常のとき


			//ジャンルID取得
			GetGenreIdLogic GenreLogic = new GetGenreIdLogic();
			genre_id=GenreLogic.execute(registerBook);
			registerBook.setGenreId(genre_id);



			//セッションに図書情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("registerBook", registerBook);


			//フォワード
			RequestDispatcher dispatcher=
				request.getRequestDispatcher("/WEB-INF/editBookConfirm.jsp");
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
					request.getRequestDispatcher("/WEB-INF/editBookConfirm.jsp");
			dispatcher.forward(request, response);
		}



	}
}
