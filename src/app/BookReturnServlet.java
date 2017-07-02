package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookReturnServlet
 */
@WebServlet("/BookReturnServlet")
public class BookReturnServlet extends ApplicationServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookReturnServlet() {
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
		//int book_id = 1;
		HttpSession session = request.getSession(true);
		User sessionUser = (User)session.getAttribute("loginUser");
		String user_id = sessionUser.getUserId();
		//String user_id = (String)session.getAttribute("userId");
		//String user_id = "1";
		//Calendar c = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//String return_date = sdf.format(c.getTime());//今日の日付の文字列

		String sql = "update  \n" +
				"	tb_trn_rental  \n" +
				"set  \n" +
				"	return_date=CURRENT_DATE  \n" +
				"where  \n" +
				"	user_id='" + user_id  + "'  \n" +
				"and  \n" +
				"	book_id=" + book_id +  "\n" +
				"and  \n" +
				"	return_date is null;" ;

		try (Connection con = DriverManager.getConnection(url, user, pass);
				Statement stmt = con.createStatement();

				) {

			int rs = stmt.executeUpdate(sql);
			//System.out.println(rs.getString("return_date"));

			//request.setAttribute("bookrental", bookrental);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/return_complete.jsp");
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