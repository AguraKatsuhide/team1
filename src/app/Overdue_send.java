package app;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sbm005_agura
 *
 */

public class Overdue_send implements Serializable{


		public Overdue_send(){

		}


		private String email;
		private String name;
		private String title;
		private Date dueDate;
		private Integer bookId;


		public String getTitle(){

			return title;
		}

		public void setTitle(String title){
			this.title =  title;
		}



		public String getName(){

			return name;
		}

		public void setName(String name){
			this.name =  name;
		}




		public String getEmail(){

			return email;
		}

		public void setEmail(String email){
			this.email =  email;
		}



		public Date getDueDate(){

			return dueDate;
		}

		public void setDueDate(Date dueDate){
			this.dueDate =  dueDate;
		}

		public Integer getBookId(){

			return bookId;
		}

		public void setBookId(Integer bookId){
			this.bookId =  bookId;
		}



		public String toString() {
			return "overdue_send [email=" + email + ",name=" + name + ", title=" + title + ", dueDate=" + dueDate + ", bookId=" + bookId +  "]";
		}


}
