package app;

import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String userId = request.getParameter("userId");
		String pass = request.getParameter("Password");

		System.out.println("ユーザID:"+userId + ",パスワード："+pass);


		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定します
		// ※SQLのログを出力するため変数urlの値は基本的な形式から少し変更を加えています。
		// 　そのためシステム構築2で使い回すときは注意下さい！
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String passwd = "webapp";


		//	 エラーが発生するかもしれない処理はtry-catchで囲みます
		//	 この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, passwd);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(
						"select user_id, name, role from tb_mst_user "
								+ "where user_id = '" + userId + "'and password = '"+ pass +"'");){

			// Userインスタンス生成
			User usr = new User();

			boolean okflg = false;

			//ログイン成功時の処理
			if (rs1.next()) {
				usr.setUserId(rs1.getString("user_id"));
				usr.setName(rs1.getString("name"));
				usr.setRole(rs1.getBoolean("role"));

				//セッション情報の新規作成
				HttpSession session = request.getSession(true);
				//セッションオブジェクトに保存する
				session.setAttribute("loginUser", usr);

				// データ取得できたときにOKフラグをtrueにする。
				okflg =true;
			}


			if(okflg == true){
				//ログイン結果画面にフォワード
				response.sendRedirect("./myPage");
			}else{
				//ログイン出来ないとき

				//エラー文字を表示
				request.setAttribute("isError", true);
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}


		}catch (Exception e) {

			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);		}
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	}
}

