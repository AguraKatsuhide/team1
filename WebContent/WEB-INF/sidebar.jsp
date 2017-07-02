<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar col-xs-2">
  <div class="container">
    <div class="row">
      <!-- プロフィール -->
      <div class="sidebar-profile col-xs-12">
        <h5>Welcome</h5>
        <p class="user-name">${user.name}さん</p>
        <p>
	          今日も１日頑張りましょう！<br />
	          あと${rentalAmount}冊借りられます。
        </p>
        <a href="./myPage" class="btn btn-primary btn-mypage">マイページ</a>
      </div>
      <hr>
      <!-- 管理者用メニュー -->
      <c:if test="${user.role}">
	      <div class="sidebar-admin">
	        <h5>Admin</h5>
	        <div class="admin-link">
	          <a href="./RegisterBookServlet" class="btn btn-primary btn-admin">蔵書登録</a>
	        </div>
	        <div class="admin-link">
	          <a href="./overdue_list" class="btn btn-danger btn-admin">貸出期限切れ</a>
	        </div>
	      </div>
	      <hr>
	  </c:if>
      <!-- 蔵書検索 -->
      <div class="sidebar-book">
        <div class="book-search-link">
          <a href="./bookList" class="btn btn-primary btn-book-search">
            <span class="glyphicon glyphicon-book" aria-hidden="true"></span>蔵書を探す
          </a>
        </div>
        <!--  新着書籍リスト -->
        <div class="newest-ranking">
          <h5>NEW!!</h5>
          	<c:forEach var="newBook" items="${newestBookList}">
	          <div class="ranking-book">
	            <p class=ranking-book-title>
	              	<a href="./BookDetailServlet?id=${newBook.bookId}">${newBook.title}</a>
	            </p>
	            <p class=ranking-book-date>
	              ${newBook.regDate}
	            </p>
	          </div>
          	</c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>