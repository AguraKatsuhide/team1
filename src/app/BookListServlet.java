package app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 蔵書一覧・蔵書検索用サーブレット
 *
 * @author t-ninomiya
 */
@WebServlet("/bookList")
public class BookListServlet extends ApplicationServlet {

	Book book;
	List<Book> bookList;

	/**
	 * 蔵書一覧描画用
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		// サイドバー用の値を取得
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		// get DB Access field
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String pass = "webapp";

		String SQL = "select \n" +
				"	b.book_id, \n" +
				"	b.title, \n" +
				"	b.author, \n" +
				"	g.name, \n" +
				"	b.publisher, \n" +
				"	b.pub_year, \n" +
				"	r.rental_id \n" +
				"from \n" +
				"	tb_mst_book b \n" +
				"join \n" +
				"	tb_mst_genre g \n" +
				"on \n" +
				"	b.genre_id = g.genre_id \n" +
				"left outer join \n" +
				"	tb_trn_rental r \n" +
				"on \n" +
				"	( \n" +
				"		b.book_id = r.book_id \n" +
				"	and \n" +
				"		return_date is null \n" +
				"	) \n" +
				"order by \n" +
				"	b.genre_id asc, \n" +
				"	b.title asc; \n";

		try (
			// DB Access
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery(SQL)){

			// 蔵書リスト生成
			List<Book> bookList = new ArrayList<Book>();

			// 蔵書生成
			while (rs1.next()) {
				Book book = new Book();
				book.setBookId(Integer.parseInt(rs1.getString("book_id")));
				book.setTitle(rs1.getString("title"));
				book.setAuthor(rs1.getString("author"));
				book.setGenreName(rs1.getString("name"));
				book.setPublisher(rs1.getString("publisher"));
				book.setPubYear(rs1.getString("pub_year"));
				book.setRentalStatusName(getStatusName(isNotEmpty(rs1.getString("rental_id"))));
				System.out.println(book.toString());
				bookList.add(book);
			}

			this.bookList = bookList;


			// レスポンスにセット
			request.setAttribute("bookList", bookList);

			// ディスパッチャーに遷移先をセット
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookList.jsp");

			dispatcher.forward(request, response);

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}


	/**
	 * 蔵書検索用
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		// サイドバー用の値を取得
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// デフォルト値をセット(全件検索)
		String params = "1";
		String field = "1";

		// パラメータを取得
		try {
			params = new String(request.getParameter("params").getBytes("ISO-8859-1"), "UTF-8");
			field = new String(request.getParameter("field").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		System.out.println(params);
		System.out.println(field);

		// フィールドが空の場合、全件検索を行う
		if(field.equals("1")) {
			field = params;
		}


		// get DB Access field
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String pass = "webapp";

		String SQL = "select \n" +
				"	b.book_id, \n" +
				"	b.title, \n" +
				"	b.author, \n" +
				"	g.name, \n" +
				"	b.publisher, \n" +
				"	b.pub_year, \n" +
				"	r.rental_id \n" +
				"from \n" +
				"	tb_mst_book b \n" +
				"join \n" +
				"	tb_mst_genre g \n" +
				"on \n" +
				"	b.genre_id = g.genre_id \n" +
				"left outer join \n" +
				"	tb_trn_rental r \n" +
				"on \n" +
				"	( \n" +
				"		b.book_id = r.book_id \n" +
				"	and \n" +
				"		return_date is null \n" +
				"	) \n" +
				"where \n" +
				params + " LIKE '%" + field + "%' \n" +
				"order by \n" +
				"	b.genre_id asc, \n" +
				"	b.title asc; \n";

		try (
			// DB Access
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery(SQL)){

			// 蔵書リスト生成
			List<Book> bookList = new ArrayList<Book>();

			// 蔵書をセット
			while (rs1.next()) {
				Book book = new Book();
				book.setBookId(Integer.parseInt(rs1.getString("book_id")));
				book.setTitle(rs1.getString("title"));
				book.setAuthor(rs1.getString("author"));
				book.setGenreName(rs1.getString("name"));
				book.setPublisher(rs1.getString("publisher"));
				book.setPubYear(rs1.getString("pub_year"));
				book.setRentalStatusName(getStatusName(isNotEmpty(rs1.getString("rental_id"))));
				System.out.println(book.toString());
				bookList.add(book);
			}

			this.bookList = bookList;


			// レスポンスをセット
			request.setAttribute("bookList", bookList);

			// ディスパッチャーに遷移先をセット
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookList.jsp");

			dispatcher.forward(request, response);

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}


	public static boolean isNotEmpty(String value) {

	    if ( value == null || value.length() == 0 )
	        return false;
	    else
	        return true;
	}

	public String getStatusName(Boolean status) {
		if(status) {
			return "貸出中";
		} else {
			return "";
		}
	}

}
