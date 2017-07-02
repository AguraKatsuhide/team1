package app;

import java.io.Serializable;
import java.util.Date;
/**
 * @author sbm005_agura
 *
 */
public class Overdue_list implements Serializable {

			public Overdue_list(){


			}

			private Integer bookId;

			private String title;

			private String author;

			private Date dueDate;

			private String userId;

			private String name;

			private boolean alert;


			public Integer getBookId(){

				return bookId;
			}

			public void setBookId(Integer bookId){
				this.bookId =  bookId;
			}


			public String getTitle(){

				return title;
			}

			public void setTitle(String title){
				this.title =  title;
			}


			public String getAuthor(){

				return author;
			}

			public void setAuthor(String author){
				this.author =  author;
			}



			public Date getDueDate(){


				return dueDate;
			}

			public void setDueDate(Date dueDate){
				this.dueDate =  dueDate;
			}



			public String getUserId(){

				return userId;
			}

			public void setUserId(String userId){
				this.userId =  userId;
			}

			public String getName(){

				return name;
			}

			public void setName(String name){
				this.name =  name;
			}


			public Boolean getAlert(){

				return alert;
			}

			public void setAlert(Boolean alert){
				this.alert =  alert;
			}

			@Override
			public String toString() {
				return "overdue_list [bookId=" + bookId + ", title=" + title + ", author=" + author + ", dueDate=" + dueDate + ", userId=" + userId + ", name=" + name + ", alert=" + alert +  "]";
			}


}
