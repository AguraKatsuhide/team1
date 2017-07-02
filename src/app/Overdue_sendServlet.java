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


@WebServlet("/overdue_send")
public class Overdue_sendServlet extends ApplicationServlet{

	List<Overdue_send> returnList2;


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

		 String id =request.getParameter("id");

		try (


				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt =  con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs2 = stmt.executeQuery(
						"select \n" +
						"  \n" +
						"  c.email, \n" +
						"  c.name, \n" +
						"  a.title, \n" +
						"  b.due_date, \n" +
						"  b.book_id \n" +
						" \n" +
						"  from \n" +
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
						"where a.book_id = '" + id + "' ; \n"  );) {

			// 社員情報を保持するため、Employee型の変数empを宣言
			// 変数empはJSPに渡すための社員情報を保持させます


			// SQL実行結果を保持している変数rsから社員情報を取得
			// rs.nextは取得した社員情報表に次の行があるとき、trueになります
			// 次の行がないときはfalseになります
		List<Overdue_send> list2 = new ArrayList<Overdue_send>();

			while (rs2.next()) {
				Overdue_send overDueSend = new Overdue_send();
				overDueSend.setEmail(rs2.getString("email"));
				overDueSend.setName(rs2.getString("name"));// SQL実行結果のsyainname列の値を取得し変数empに代入します
				overDueSend.setTitle(rs2.getString("title"));
				overDueSend.setDueDate(rs2.getDate("due_date")); // SQL実行結果の「imagefilename」列の値を取得し変数empに代入します
				overDueSend.setBookId(rs2.getInt("book_id"));
				list2.add(overDueSend);
				System.out.println(overDueSend.toString());


			}

			this.returnList2 =list2;



			// JSPで利用するため変数empをリクエストスコープにセット
			request.setAttribute("returnList2", returnList2);

			// 箱"emp"を渡す宛先のJSPファイルを指定します
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/overdue_send.jsp");

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
