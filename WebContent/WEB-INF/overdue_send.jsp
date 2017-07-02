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
          <h3 class="page-heade">催促 メール送信</h3>
          <br></br>
<td class="table-row-return"><a href="overdue_list" class="btn btn-primary">戻る</a></td>
      <br></br>
  <c:forEach var="overdueSend" items="${returnList2}">
    <h4><span
style="
font-style: normal ;
font-weight: normal;
font-size: 1.0em;
line-height: 1.0em;
color: black;
font-family: arial;
">
宛先メールアドレス:
</span>
 <span
style="
font-style: italic ;
font-weight: bold;
font-size: 1.0em;
line-height: 1.0em;
color: navy;
font-family: arial;
">
${overdueSend.email}
</span></h4>
<br>
      <h4> <span
style="
font-style: italic ;
font-weight: bold;
font-size: 1.0em;
line-height: 1.0em;
color: navy;
font-family: arial;
">
${overdueSend.name}
</span> 様</h4>
<br>

      <h4>図書管理担当者です。</h4>

      <h4>下記の本の返却期限が超過しています。</h4>
      <h4>速やかに返却をお願いいたします。</h4>

      <h5>---------------------------------------------------------</h5>

      <h4>タイトル：<td></td><span
style="
font-style: italic ;
font-weight: bold;
font-size: 1.0em;
line-height: 0.5em;
color: navy;
font-family: arial;
">
${overdueSend.title}
</span></h4>

      <h4>返却予定日：<td></td><span
style="
font-style: italic ;
font-weight: bold;
font-size: 1.0em;
line-height: 0.5em;
color: navy;
font-family: arial;
">
${overdueSend.dueDate}
</span></h4>

      <h5>---------------------------------------------------------</h5>
        <h4>ソフトバンク株式会社</h4>
          <h4>図書管理担当者</h4>
  <div class="content col-xs-14">
   <div class="col-lg-10 col-lg-offset-2">
  <td class="table-row-return"><a href="overdue_list?status=${overdueSend.bookId}" class="btn btn-primary">送る</a></td>
      </div>
      </div>
        </c:forEach>
        </div>
      </div>
    </div>




    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
  </body>
</html>
