package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

import app.Book;




public class GetGenreNameLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public String execute(Book book){
		//ここにデータベース登録処理
			String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
			String user = "webapp";
			String pass = "webapp";
			String s=null;

			//Connection con = null;
			try (
					// データベースへ接続します
					Connection con = DriverManager.getConnection(url, user, pass);

					// SQLの命令文を実行するための準備をおこないます
					Statement stmt = con.createStatement();

					ResultSet rs1 = stmt.executeQuery("select name from tb_mst_genre where genre_id='" + book.getGenreId() + "'");
					) {
				if(rs1.next()){

					s = rs1.getString("name");
				}

			}catch (Exception e) {
				throw new RuntimeException(String.format("ジャンル名の取得中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);

			}
			return s;
		}
}
