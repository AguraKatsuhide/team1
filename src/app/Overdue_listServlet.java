package app;

import java.io.IOException;
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
 * @author sbm005_agura
 *
 */

@WebServlet("/overdue_list")
public class Overdue_listServlet extends ApplicationServlet {

	List<Overdue_list> returnList;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			super.doGet(request, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String pass = "webapp";

		 //String status =request.getParameter("status");


		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (


				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt =  con.createStatement();
				Statement stmt1 =  con.createStatement();


				) {

			String status1 =request.getParameter("status");

			if(status1 != null){
			stmt1.executeUpdate("update tb_trn_rental \n" +
					"	set alert = true \n" +
					"	where book_id = '"+ status1 +"'; \n"   );


			}


			// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
			ResultSet rs1 = stmt.executeQuery("select \n" +
					"  a.book_id, \n" +
					"  a.title, \n" +
					"  a.author, \n" +
					"  b.due_date, \n" +
					"  c.user_id, \n" +
					"  c.name, \n" +
					"  b.alert \n" +
					" \n" +
					"from \n" +
					"  tb_trn_rental as b \n" +
					" \n" +
					"left join \n" +
					"  tb_mst_book as a \n" +
					"on \n" +
					"  a.book_id = b.book_id \n" +
					" \n" +
					"left join \n" +
					"  tb_mst_user as c \n" +
					"on \n" +
					"  b.user_id = c.user_id \n" +
					" \n" +
					"where \n" +
					"  b.return_date IS NULL \n" +
					"and \n" +
					"  b.due_date < current_date "
					+ "\n" +
					" \n" +
					"order by \n" +
					"  b.due_date asc, \n" +
					"  a.title asc; \n" );

			List<Overdue_list> list = new ArrayList<Overdue_list>();

			while (rs1.next()) {
				Overdue_list overDue = new Overdue_list();
				overDue.setBookId(rs1.getInt("book_id"));
				overDue.setTitle(rs1.getString("title"));// SQL実行結果のsyainname列の値を取得し変数empに代入します
				overDue.setAuthor(rs1.getString("author")); // SQL実行結果の「syusinttdfkn」列の値を取得し変数empに代入します
				overDue.setDueDate(rs1.getDate("due_date")); // SQL実行結果の「imagefilename」列の値を取得し変数empに代入します
				overDue.setUserId(rs1.getString("user_id")); // 以下、同様なので以下省略します
				overDue.setName(rs1.getString("name"));
				overDue.setAlert(rs1.getBoolean("alert"));
				list.add(overDue);
				//System.out.println(overDue.getDueDate());
				System.out.println(overDue.toString());


			}

			this.returnList =list;

			// JSPで利用するため変数empをリクエストスコープにセット
						request.setAttribute("returnList", returnList);

						// 箱"emp"を渡す宛先のJSPファイルを指定します
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/overdue_list.jsp");

						// JSPファイルに移動します
						dispatcher.forward(request, response);




		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}

}
