package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import app.Book;




public class DeleteBookLogic {

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
				Statement stmt0 = con.createStatement();
				Statement stmt = con.createStatement();

//				System.out.println("book:"+book);
				System.out.println("--------------------------book:"+book.getBookId());

				String sql0 = "delete from tb_trn_rental"
						+" WHERE book_id='" + book.getBookId()
						+"';";
				String sql = "delete from tb_mst_book"
						+" WHERE book_id='" + book.getBookId()
						+"';";



			// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
			int num0 = stmt0.executeUpdate(sql0);
			int num = stmt.executeUpdate(sql);
		}catch (Exception e) {
			throw new RuntimeException(String.format("削除中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}



	return true;
	}
}