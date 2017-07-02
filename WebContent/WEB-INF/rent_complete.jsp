<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
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
          <h3 class="page-header">貸出完了</h3>
			<h4>貸出情報</h4>

			<table class="table table-striped table-hover ">
			<tbody>
				<tr>
					<th>蔵書ID</th>
					<td>${bookrental.bookId}</td>
				</tr>
				<tr>
					<th>ジャンル</th>
					<td>${genre_name}</td>
				</tr>
				<tr>
					<th>タイトル</th>
					<td>${rental.title}</td>
				</tr>
				<tr>
					<th>著者</th>
					<td>${rental.author}</td>
				</tr>
				<tr>
					<th>出版社</th>
					<td>${rental.publisher}</td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td>${rental.isbn}</td>
				</tr>
				<tr>
					<th>貸出日</th>
					<td>${bookrental.rentalDate}</td>
				</tr>
				<tr>
					<th>返却期限</th>
					<td>${bookrental.dueDate}</td>
				</tr>
				</tbody>
			</table>
        </div>
      </div>
    </div>


    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
  </body>
</html>
