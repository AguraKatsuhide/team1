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
          <h3 class="page-header">詳細</h3>
			<h4>蔵書情報</h4>
			<c:if test="${user.role}">
				<form action="EditBookServlet" class="form-horizontal">
					<div class="form-group">
				      <div class="col-lg-10 col-lg-offset-2">
				        <input type="hidden" value="${detail.bookId}" name="book_id"/>
				        <button type="submit" class="btn btn-primary" style="float:right;">編集</button>
				      </div>
				    </div>
				</form>
			</c:if>

			<table class="table table-striped table-hover ">
				<tbody>
					<tr>
						<th>蔵書ID</th>
						<td>${detail.bookId} </td>
					</tr>
					<tr>
						<th>ジャンル</th>
						<td>${detail.genreName}</td>
					</tr>
					<tr>
						<th>タイトル</th>
						<td>${detail.title}</td>
					</tr>
					<tr>
						<th>著者</th>
						<td>${detail.author}</td>
					</tr>
					<tr>
						<th>出版社</th>
						<td>${detail.publisher}</td>
					</tr>
					<tr>
						<th>ISBN</th>
						<td>${detail.isbn}</td>
					</tr>
					<tr>
						<th>概要</th>
						<td>${detail.comment}</td>
					</tr>
					<tr>
						<th>貸出ステータス</th>
						<td>${detail.rentalStatusName}</td>
					</tr>
					<tr>
						<th>返却期限</th>
						<td>${detail.dueDate}</td>
					</tr>
					<tr>
						<th>借りている人</th>
						<td>${rentalUserName}</td>
					</tr>
				</tbody>
			</table>
			<c:if test="${detail.rentalStatusName=='貸出可能' && rentalAmount > 0}">
				<div class="col-xs-4 col-xs-offset-4">
					レンタル期間
					<form action="BookRentServlet" class="form-horizontal">
						<div class="form-group">
					      <label for="select" class="col-lg-4 col-lg-offset-1 control-label">今日から</label>
					      <div class="col-lg-6">
					        <select class="form-control" id="select" name="term">
					          <option>1</option>
					          <option>2</option>
					          <option>3</option>
					          <option>4</option>
					          <option>5</option>
					          <option>6</option>
					          <option>7</option>
					          <option>8</option>
					          <option>9</option>
					          <option>10</option>
					          <option>11</option>
					          <option>12</option>
					          <option>13</option>
                    		  <option>14</option>
					        </select>
					      </div>
					      <label for="select" class="col-lg-1 control-label">日</label>
					    </div>
					    <div class="form-group">
					      <div class="col-lg-12">
					        <input type="hidden" value="${detail.bookId}" name="book_id"/>
					        <button type="submit" class="btn btn-primary" style="width: 100%;">借りる</button>
					      </div>
					    </div>
					</form>
				</div>
			</c:if>
			<c:if test="${detail.rentalStatusName=='貸出可能' && rentalAmount <=0}">
				<p><font color="red">これ以上レンタルできません。借りる場合は、読み終えた本を返却してください。</font></p>
			</c:if>
        </div>
      </div>
    </div>


    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
  </body>
</html>
