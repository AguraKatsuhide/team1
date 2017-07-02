package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends ApplicationServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String pass = "webapp";

		String book_id = request.getParameter("id");
		String rentalUserName = "";


		String sql =
				"select * \n" +
						"from \n" +
						"(select \n" +
						"    b.book_id, \n" +
						"    b.title, \n" +
						"    b.author, \n" +
						"    g.name, \n" +
						"    b.publisher, \n" +
						"	b.isbn, \n" +
						"    b.pub_year, \n" +
						"    r.rental_id, \n" +
						"    r.due_date, \n" +
						"	g.name, \n" +
						"	b.comment, \n" +
						"	u.name as uname \n" +
						"from \n" +
						"    tb_mst_book b \n" +
						"join \n" +
						"    tb_mst_genre g \n" +
						"on \n" +
						"    b.genre_id = g.genre_id \n" +
						"left outer join \n" +
						"    tb_trn_rental r \n" +
						"on \n" +
						"    ( \n" +
						"        b.book_id = r.book_id \n" +
						"    and \n" +
						"        return_date is null \n" +
						"    ) \n" +
						"left outer join \n" +
						"	tb_mst_user u \n" +
						"on \n" +
						"	r.user_id = u.user_id \n" +
						"order by \n" +
						"    b.genre_id asc, \n" +
						"    b.title asc) as t \n" +
				"where t.book_id =" + book_id + ";";


		try (Connection con = DriverManager.getConnection(url, user, pass);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {

			Book detail = new Book();

			if (rs.next()) {
				detail.setBookId(Integer.parseInt(rs.getString("book_id")));
				detail.setGenreName(rs.getString("name"));
				detail.setTitle(rs.getString("title"));
				detail.setAuthor(rs.getString("author"));
				detail.setPublisher(rs.getString("publisher"));
				detail.setIsbn(rs.getString("isbn"));
				detail.setComment(rs.getString("comment"));
				detail.setDueDate(rs.getDate("due_date"));

				Integer i = rs.getInt("rental_id");
				System.out.println(i);
				String status = "貸出可能";
				if(i != 0){
					status = "貸出中";
				}
				detail.setRentalStatusName(status);

				rentalUserName = rs.getString("uname");
			}


			System.out.println(detail.toString());

			request.setAttribute("detail", detail);
			request.setAttribute("rentalUserName", rentalUserName);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detail.jsp");
			dispatcher.forward(request, response);


		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
