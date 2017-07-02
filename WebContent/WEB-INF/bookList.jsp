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
        <div class="container book-list-search">
          <form class="form-horizontal search-form" action="./bookList" method="post">
            <fieldset>
              <legend>表示条件</legend>
              <div class="col-lg-3 col-lg-offset-1">
                <label class="col-lg-3 control-label"></label>
                <div class="col-lg-9">
                  <div class="radio">
                    <label>
                      <input type="radio" name="params" id="optionsParams1" value="name" checked="true">
                     	 ジャンル
                    </label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio" name="params" id="optionsParams2" value="title">
                     	 タイトル
                    </label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio" name="params" id="optionsParams3" value="publisher">
                      	出版社
                    </label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio" name="params" id="optionsParams4" value="comment">
                      	キーワード
                    </label>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 search-field">
                  <input type="text" name="field" class="form-control" id="inputField" placeholder="検索内容">
              </div>
              <div class="col-lg-3 search-field">
                <button type="submit" class="btn btn-primary">検索</button>
                <a href="./bookList" class="btn btn-default btn-search-reset">クリア</a>
              </div>
            </fieldset>
          </form>
        </div>
        <div class="container">
          <h3 class="page-header">蔵書一覧</h3>
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th>ジャンル</th>
                <th>タイトル</th>
                <th>著者</th>
                <th>出版社</th>
                <th>出版年</th>
                <th>ステータス</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach var="book" items="${bookList}">
	              <tr>
	                <td>${book.genreName}</td>
	                <td><a href="./BookDetailServlet?id=${book.bookId}" class="book-title" target="_blank">${book.title}</a></td>
	                <td>${book.author}</td>
	                <td>${book.publisher}</td>
	                <td>${book.pubYear}</td>
	                <td>${book.rentalStatusName}</td>
	              </tr>
	            </c:forEach>
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
