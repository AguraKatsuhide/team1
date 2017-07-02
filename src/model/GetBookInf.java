package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

import app.Book;




public class GetBookInf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Book execute(int BookID){
		//ここにデータベース登録処理
		String url = "jdbc:postgresql://team1.sbm.itcamp.io:5432/postgres";
			String user = "webapp";
			String pass = "webapp";
			Book Bookinf= new Book();

			//Connection con = null;
			try (
					// データベースへ接続します
					Connection con = DriverManager.getConnection(url, user, pass);

					// SQLの命令文を実行するための準備をおこないます
					Statement stmt = con.createStatement();
					Statement stmt2 = con.createStatement();
					ResultSet rs1 = stmt.executeQuery("select * from tb_mst_book where book_id='" + BookID + "'");
					ResultSet rs2 = stmt2.executeQuery("select count(*) from tb_trn_rental where return_date is null and book_id='"+ BookID +"'");
					) {
				if(rs1.next()){

					Bookinf.setBookId(Integer.parseInt(rs1.getString("book_id")));
					Bookinf.setGenreId(Integer.parseInt(rs1.getString("genre_id")));
					Bookinf.setTitle(rs1.getString("title"));
					Bookinf.setAuthor(rs1.getString("author"));
					Bookinf.setPublisher(rs1.getString("publisher"));
					Bookinf.setPubYear(rs1.getString("pub_year"));
					Bookinf.setIsbn(rs1.getString("isbn"));
					Bookinf.setComment(rs1.getString("comment"));

				}

				if(rs2.next()){
					if(Integer.parseInt(rs2.getString("count"))==0){
						Bookinf.setRentalStatus(false);
					}else{
						Bookinf.setRentalStatus(true);
					}

				}

			}catch (Exception e) {
				throw new RuntimeException(String.format("図書情報の取得中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);

			}
			return Bookinf;
		}


}
