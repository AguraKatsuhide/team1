package app;

import java.util.Date;

public class BookRental {
	public BookRental() {
		// 何もしない
	}


	/** 貸出履歴ID */
	private Integer rentalId;

	/** 利用者ID */
	private String userId;

	/** 蔵書ID */
	private Integer bookId;

	/** 貸出日 */
	private Date rentalDate;

	/** 返却予定日 */
	private Date dueDate;

	/** 返却日 */
	private Date returnDate;

	/** 催促中 */
	private Boolean alert;

	/** ジャンル */
	private String genre;

	/** タイトル */
	private String title;

	/** 著者 */
	private String author;

	/** ISBN */
	private String isbn;



	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "BookRental [RentalId=" + rentalId + ", UserId=" + userId + ", BookId=" + bookId + ", RentalDate=" + rentalDate + ", DueDate=" + dueDate + ", ReturnDate=" + returnDate + ", Alert=" + alert + "]";
	}

}
