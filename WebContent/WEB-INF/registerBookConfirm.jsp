<%@ page import="java.text.SimpleDateFormat" %>

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

<!-- Forms ================================================== -->
<%@ page import="app.Book"%>
<%
Book registerBook = (Book) session.getAttribute("registerBook");

%>
			<!--  div class="bs-docs-section" -->
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header" >
							<h3 id="forms">下記の図書を登録します</h3>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div>
							<form class="form-horizontal">
								<input type="hidden" name="action" value="done">
								<fieldset>
									<legend><br>登録内容</legend>
									<div class="col-lg-2">
										ジャンル</div><div class="col-lg-10">：<%= registerBook.getGenreName() %></div>


									<div class="col-lg-2">
										タイトル</div><div class="col-lg-10">：<%= registerBook.getTitle()%></div>
									<div class="col-lg-2">
										著者</div><div class="col-lg-10">：<%= registerBook.getAuthor()%></div>
									<div class="col-lg-2">
										出版社</div><div class="col-lg-10">：<%= registerBook.getPublisher()%></div>
									<div class="col-lg-2">
										出版年</div><div class="col-lg-10">：<%= registerBook.getPubYear()%></div>
									<div class="col-lg-2">
										ISBN</div><div class="col-lg-10">：<%= registerBook.getIsbn()%></div>
									<div class="col-lg-2">
										概要</div><div class="col-lg-10">：<%= registerBook.getComment()%></div>
									<div class="col-lg-2">
										<%
//										String regDateS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(registerBook.getRegDate());
										SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
										String str = new SimpleDateFormat("yyyy/MM/dd")	.format(registerBook.getRegDate());									%>
										登録日</div><div class="col-lg-10">：<%= str%></div>
								</fieldset>
								<div class="form-group">
									<div class="col-lg-10 col-lg-offset-8">
										<button type="reset" class="btn btn-default"
											onclick="location.href='RegisterBookServlet?action=back'">戻る</button>
											<!--	onclick="location.href='/system/RegisterBookServlet'">戻る</button> -->
										<button type="submit" class="btn btn-primary">登録</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			<!-- /div -->
        </div>
      </div>
    </div>


    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
  </body>
</html>
