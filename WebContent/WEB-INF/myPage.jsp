<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>SBooks</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="./assets/css/bootstrap.css" media="screen">
    <link rel="stylesheet" href="./assets/css/custom.min.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="./assets/js/html5shiv.js"></script>
      <script src="./assets/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <%@ include file="/WEB-INF/header.jsp" %>
    <div class="content col-xs-12">
      <!-- サイドバー -->
      <jsp:include page="/WEB-INF/sidebar.jsp" />
      <div class="content-main col-xs-10">
        <div class="container">
          <h2 class="page-header">${user.name}さんのマイページ</h2>
          <% if(!request.getAttribute("userRental").equals("0")){ %>
          	  <h4>貸出中の書籍</h4>
	          <table class="table table-striped table-hover">
	            <thead>
	              <tr>
	              	<th>蔵書ID</th>
	                <th>ジャンル</th>
	                <th>タイトル</th>
	                <th>著者</th>
	                <th>返却期限</th>
	                <th class="table-row-return"></th>
	              </tr>
	            </thead>
	            <tbody>
		            <c:forEach var="book" items="${rentalBookList}">
			            <tr>
			              <td>${book.bookId}</td>
			              <td>${book.genreName}</td>
			              <td>${book.title}</td>
			              <td>${book.author}</td>
			              <td>${book.restDay}</td>
			              <td class="table-row-return"><a href="./BookReturnServlet?id=${book.bookId}" class="btn btn-primary">返却</a></td>
			            </tr>
		            </c:forEach>
	            </tbody>
	          </table>
	      </div>
        <% } else { %>
          </div>
          <div class="content-no-rental">
        	<h4>まだレンタルしている本はありません。</h4><br>
        	<p>書物の新しいページを1ページ、1ページ読むごとに、私はより豊かに、より強く、より高くなっていく。<p>
        	<p class="author">チェーホフ</p>
        	<a href="./bookList" class="btn btn-primary btn-book-list">本を探しにいく</a>
          </div>
        <% } %>

      </div>



    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
  </body>
</html>
