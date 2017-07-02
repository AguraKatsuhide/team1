package app;

import java.io.Serializable;
import java.util.Date;

/**
 * 蔵書情報を保持するクラス
 *
 * @author t-ninomiya
 *
 */
public class Book implements Serializable {

	public Book() {
		// 何もしない
	}

	/** 蔵書ID */
	private Integer bookId;

	/** ジャンルID */
	private Integer genreId;

	/** ジャンル名 */
	private String genreName;

	/** タイトル */
	private String title;



	/** 著者 */
	private String author;

	/** ISBN */
	private String isbn;

	/** 出版社 */
	private String publisher;

	/** 出版年 */
	private String pubYear;

	/** 登録年月日 */
	private Date regDate;

	/** 概要 */
	private String comment;

	/** 返却日 */
	private Date dueDate;

	/** 残り日数 */
	private String restDay;

	/** 貸出ステータス */
	private Boolean rentalStatus;

	/** 貸出ステータス名 */
	private String rentalStatusName;



	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubYear() {
		return pubYear;
	}

	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(Boolean rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public String getRentalStatusName() {
		return rentalStatusName;
	}

	public void setRentalStatusName(String rentalStatusName) {
		this.rentalStatusName = rentalStatusName;
	}

	public String getRestDay() {
		return restDay;
	}

	public void setRestDay(String restDay) {
		this.restDay = restDay;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", genreId=" + genreId + ", genreName=" + genreName + ", title=" + title
				+ ", author=" + author + ", isbn=" + isbn + ", publisher=" + publisher + ", pubYear=" + pubYear
				+ ", regDate=" + regDate + ", comment=" + comment + ", dueDate=" + dueDate + ", rentalStatus="
				+ rentalStatus + "]";
	}




}
