
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
			<!-- div class="bs-docs-section" -->
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h3 id="forms">下記の内容で登録します</h3>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div>
							<form class="form-horizontal">
								<input type="hidden" name="action" value="done">
								<fieldset>
									<legend>登録内容</legend>
									<div>
										ジャンル：<%= registerBook.getGenreName() %></div>
									<div>
										タイトル：<%= registerBook.getTitle()%></div>
									<div>
										著者：<%= registerBook.getAuthor()%></div>
									<div>
										出版社：<%= registerBook.getPublisher()%></div>
									<div>
										出版年：<%= registerBook.getPubYear()%></div>
									<div>
										ISBN：<%= registerBook.getIsbn()%></div>
									<div>
										概要：<%= registerBook.getComment()%></div>
									<div>
										<%
//										String regDateS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(registerBook.getRegDate());
										SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
										String str = new SimpleDateFormat("yyyy/MM/dd")	.format(registerBook.getRegDate());									%>
										登録日：<%= str%></div>
								</fieldset>
								<div class="form-group">
									<div class="col-lg-10 col-lg-offset-8">
										<button type="reset" class="btn btn-default"
											 onclick="location.href='EditBookServlet?action=back'">戻る</button>
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
