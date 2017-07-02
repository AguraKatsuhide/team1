package app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * マイページ描画用サーブレット
 *
 * @author t-ninomiya
 */
@WebServlet("/myPage")
public class MyPageServlet extends ApplicationServlet {

	List<Book> rentalBookList;
	User user;

	/**
	 * マイページを描画
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// サイドバー用のデータを取得
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// セッションを取得
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("loginUser");

		// set DB Access
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String DBUser = "webapp";
		String DBPass = "webapp";

		String getBookListSQL = "select \n" +
				"  b.book_id, \n" +
				"  g.name genre_name, \n" +
				"  b.title, \n" +
				"  b.author, \n" +
				"  r.due_date \n" +
				"from \n" +
				"  tb_trn_rental r \n" +
				"inner join \n" +
				"  tb_mst_book b \n" +
				"on \n" +
				"  r.book_id = b.book_id \n" +
				"inner join \n" +
				"  tb_mst_genre g \n" +
				"on \n" +
				"  b.genre_id = g.genre_id \n" +
				"inner join \n" +
				"  tb_mst_user u \n" +
				"on \n" +
				"  r.user_id = u.user_id \n" +
				"where \n" +
				"  r.user_id = '" + sessionUser.getUserId() + "' \n" +
				"and \n" +
				"  r.return_date is null \n" +
				"order by \n" +
				"  due_date asc, \n" +
				"  title asc; \n";

		try (
			// connect DB
			Connection con = DriverManager.getConnection(url, DBUser, DBPass);

			// set statement
			Statement stmt = con.createStatement();

			// execute SQL
			ResultSet rs1 = stmt.executeQuery(getBookListSQL)) {

			// ユーザーがレンタル中の蔵書一覧を取得
			List<Book> userBookList = new ArrayList<Book>();

			while (rs1.next()) {
				Book book = new Book();
				book.setBookId(Integer.parseInt(rs1.getString("book_id")));
				book.setGenreName(rs1.getString("genre_name"));
				book.setTitle(rs1.getString("title"));
				book.setAuthor(rs1.getString("author"));
				book.setDueDate(rs1.getDate("due_date"));
				book.setRestDay(getRestDate(rs1.getDate("due_date")));
				userBookList.add(book);
				System.out.println(book.toString());
			}


			// レスポンスに値をセット
			request.setAttribute("rentalBookList", userBookList);
			request.setAttribute("userRental", String.valueOf(userBookList.size()));

			// ディスパッチャーの遷移先をセット
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/myPage.jsp");

			dispatcher.forward(request, response);

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}

	public String formatRole(boolean role) {
		if(role) {
			return "管理者";
		} else {
			return "一般社員";
		}
	}

	public static String getRestDate(Date dueDate){
		Date dateTo = dueDate;
		Date dateFrom = new Date();

		long dateTimeTo = dateTo.getTime();
		long dateTimeFrom = dateFrom.getTime();

		// 差分の日数を算出
		long dayDiff = ( dateTimeTo - dateTimeFrom  ) / (1000 * 60 * 60 * 24 ) + 1;

		if(dayDiff < 0){
			return "期限切れ";
		} else {
			return "あと" + dayDiff + "日";
		}
	}
}
