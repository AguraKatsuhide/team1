package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

import app.Book;




public class GetGenreIdLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public int execute(Book book){
		//ここにデータベース登録処理
			String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
			String user = "webapp";
			String pass = "webapp";
			Integer i=0;

			//Connection con = null;
			try (
					// データベースへ接続します
					Connection con = DriverManager.getConnection(url, user, pass);

					// SQLの命令文を実行するための準備をおこないます
					Statement stmt = con.createStatement();

					ResultSet rs1 = stmt.executeQuery("select genre_id from tb_mst_genre where name='" + book.getGenreName() + "'");
					) {
				if(rs1.next()){

					i = Integer.parseInt(rs1.getString("genre_id"));
				}

			}catch (Exception e) {
				throw new RuntimeException(String.format("ジャンルIDの生成中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);

			}
			System.out.println("i:"+i);
			return i;
		}


}
