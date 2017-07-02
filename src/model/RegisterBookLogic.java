package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import app.Book;




public class RegisterBookLogic {

	public boolean execute(Book book){
	//ここにデータベース登録処理
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
		String user = "webapp";
		String pass = "webapp";


		//Connection con = null;
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);				) {

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				String sql = "INSERT INTO tb_mst_book (book_id, genre_id, title, author, isbn, publisher, pub_year, reg_date, comment)"
						+ " values (nextval('tb_mst_book_book_id_seq') " + ", '" + book.getGenreId() + "', '" + book.getTitle() + "','" + book.getAuthor() + "',"
						+ "'" + book.getIsbn() + "','" + book.getPublisher() + "','" + book.getPubYear() + "','" + book.getRegDate() + "','" + book.getComment() + "')";


			// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
			int num = stmt.executeUpdate(sql);
		}catch (Exception e) {
			throw new RuntimeException(String.format("データベースへの登録中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}



	return true;
	}
}
