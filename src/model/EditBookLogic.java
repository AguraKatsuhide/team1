package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import app.Book;




public class EditBookLogic{

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

				System.out.println("book:"+book);

				String sql = "UPDATE tb_mst_book SET "
						+ "genre_id = '" + book.getGenreId()
						+ "', title='" +book.getTitle()
						+ "', author='"+book.getAuthor()
						+ "', isbn='"+book.getIsbn()
						+ "', publisher='"+book.getPublisher()
						+ "', pub_year='"+ book.getPubYear()
						+ "', reg_date='"+book.getRegDate()
						+ "', comment='"+book.getComment()
						+"' WHERE book_id='" + book.getBookId()
						+"'";


//				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
//				int num = stmt.executeUpdate(
//						"INSERT INTO tb_mst_book (genre_id, title, author, isbn, publisher, pub_year, reg_date, comment)"
//						+ " values ('" + book.getGenreId() + "', '" + book.getTitle() + "','" + book.getAuthor() + "',"
//								+ "'" + book.getIsbn() + "','" + book.getPublisher() + "','" + tmpDate + "','" + book.getRegDate() + "','" + book.getComment() + "');");


//				ResultSet rs1 = stmt.executeQuery(
//						"ISERT INTO tb_mst_book (genre_id)"
//						+ " values ('01');");



			// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
			int num = stmt.executeUpdate(sql);
		}catch (Exception e) {
			throw new RuntimeException(String.format("データベースへの登録中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}



	return true;
	}
}
