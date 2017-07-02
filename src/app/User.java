package app;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 社員情報を保持するクラス
 *
 * @author t-ninomiya
 *
 */
public class User implements Serializable {

	public User() {
		super();
	}

	/** ユーザーID */
	private String userId;

	/** 名前 */
	private String name;

	/** メールアドレス */
	private String email;

	/** 役割 */
	private Boolean role;

	/** 貸出中蔵書リスト */
	private List<Book> bookList = new ArrayList<>();


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", email=" + email + ", role=" + role + ", bookList=" + bookList + "]";
	}

}
