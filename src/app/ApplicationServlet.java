package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * サイドバー描画用データ管理サーブレット
 * only extends
 *
 * @author t-ninomiya
 */

public class ApplicationServlet extends HttpServlet {

    public ApplicationServlet() {
        super();
    }

    String amount = "0";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// get session
		HttpSession session = request.getSession();
		if(session.isNew()){
			response.sendRedirect("index.jsp");
			request.setAttribute("isLogout", true);
		}
		User sessionUser = (User) session.getAttribute("loginUser");

		// set field for DB access
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String DBUser = "webapp";
		String DBPass = "webapp";

		String getRentalAmountSQL = "select \n" +
				"	count(*) as amount \n" +
				"from \n" +
				"	tb_trn_rental \n" +
				"where \n" +
				"	user_id = '" + sessionUser.getUserId() + "' \n" +
				"and \n" +
				"	return_date is null \n" +
				"group by \n" +
				"	user_id; \n";
		String getNewestBookListSQL = "select \n" +
				"	book_id, \n" +
				"	title, \n" +
				"	reg_date \n" +
				"from \n" +
				"	tb_mst_book \n" +
				"order by \n" +
				"	reg_date desc, \n" +
				"	title desc \n" +
				"limit \n" +
				"	5; \n";

		try (
			// connect DB
			Connection con = DriverManager.getConnection(url, DBUser, DBPass);

			// set statement
			Statement stmt = con.createStatement();
			Statement stmt1 = con.createStatement();

			// execute SQL
			ResultSet rs1 = stmt.executeQuery(getRentalAmountSQL);
			ResultSet rs2 = stmt1.executeQuery(getNewestBookListSQL)) {

			// set user rental amount
			if (rs1.next()) {
				amount = rs1.getString("amount");
			}

			// set newest book list array
			List<Book> newestBookList = new ArrayList<Book>();

			while (rs2.next()) {
				Book book = new Book();
				book.setBookId(rs2.getInt("book_id"));
				book.setTitle(rs2.getString("title"));
				book.setRegDate(rs2.getDate("reg_date"));
				newestBookList.add(book);
				System.out.println(book.toString());
			}

			String restAmount = String.valueOf(5 - Integer.parseInt(amount));

			// set scope
			request.setAttribute("user", sessionUser);
			request.removeAttribute("rentalAmount");
			request.removeAttribute("newestBookList");
			request.setAttribute("rentalAmount", restAmount);
			request.setAttribute("newestBookList", newestBookList);

			// 以下、引継ぎ先のサーブレットで処理

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

	}

}
