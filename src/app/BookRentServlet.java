package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookRentservlet
 */
@WebServlet("/BookRentServlet")
public class BookRentServlet extends ApplicationServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRentServlet() {
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


		Integer book_id = Integer.parseInt(request.getParameter("book_id"));
		//String book_id = "1";
		//int book_id = 5;
		HttpSession session = request.getSession(true);
		User sessionUser = (User)session.getAttribute("loginUser");
		String user_id = sessionUser.getUserId();
		//String user_id = (String)session.getAttribute("userId");
		//String user_id = "1";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String rental_date = sdf.format(c.getTime());//今日の日付の文字列
		int term = Integer.parseInt(request.getParameter("term"));
		//int term = 5;
		c.add(Calendar.DATE, term);
		String due_date = sdf.format(c.getTime());

		String genre_name = "";


		String sql1 = "INSERT INTO tb_trn_rental \n" +
				"(user_id, book_id, rental_date, due_date, return_date, alert) \n" +
				"VALUES \n" +
				"('" + user_id + "'," + book_id + ",to_date('" + rental_date + "','yyyy/mm/dd'),to_date('" + due_date + "','yyyy/mm/dd'),NULL,FALSE);";


		String sql2 = "select b.title as title, b.author as author, b.publisher as publisher,"
						+ " b.isbn as isbn, g.name as genre_name"
						+ " from tb_mst_book as b"
						+ " join tb_mst_genre as g"
						+ " on b.genre_id=g.genre_id"
						+ " where book_id=" + book_id + ";";


		try (Connection con = DriverManager.getConnection(url, user, pass);
				Statement stmt1 = con.createStatement();
				//ResultSet rs1 = stmt1.executeQuery(sql1);
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				) {
			System.out.println("aaa");
			//int rs = stmt1.executeUpdate(sql1);
			stmt1.executeUpdate(sql1);
			System.out.println("bbb");

			BookRental bookrental = new BookRental();
			System.out.println("bb1");
			//bookrental.setRentalId(Integer.parseInt(rs1.getString("rental_id")));
			bookrental.setUserId(user_id);
			System.out.println("b2");
			//bookrental.setBookId(Integer.parseInt(book_id));
			bookrental.setBookId(book_id);
			System.out.println("b3");
			Date ren_date = sdf.parse(rental_date);
			bookrental.setRentalDate(ren_date);
			System.out.println("b4");
			Date d_date = sdf.parse(due_date);
			bookrental.setDueDate(d_date);
			System.out.println("b5");
			bookrental.setReturnDate(null);
			System.out.println("b6");
			bookrental.setAlert(false);
			System.out.println("ccc");

			System.out.println(bookrental.toString());
			System.out.println("ddd");


			//その他の本の情報を獲得
			Book rental = new Book();

			if (rs2.next()) {
			System.out.println("eee");
			//rental.setGenreId(Integer.parseInt(rs2.getString("genre_id")));
			//rental.setGenreId(Integer.parseInt(rs2.getString("genre_id")));
			System.out.println("e1");
			rental.setTitle(rs2.getString("title"));
			System.out.println("e2");
			rental.setAuthor(rs2.getString("author"));
			System.out.println("e3");
			rental.setPublisher(rs2.getString("publisher"));
			System.out.println("e4");
			rental.setIsbn(rs2.getString("isbn"));
			//rental.setPublisher(rs2.getString("genre_name"));
			genre_name = rs2.getString("genre_name");
			System.out.println("fff");
			}

			System.out.println(rental.toString());

			request.setAttribute("bookrental", bookrental);
			request.setAttribute("rental", rental);
			request.setAttribute("genre_name", genre_name);
			//request.setAttribute("id", id);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/rent_complete.jsp");
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

