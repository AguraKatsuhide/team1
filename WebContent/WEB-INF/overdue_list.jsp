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
          <h3 class="page-heade">期限切れの本のリスト</h3>

           <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th>タイトル　ID</th>
				<th>タイトル</th>
				<th>著者</th>
				<th>返却予定日</th>
				<th>利用者の名前</th>
				<th>送信</th>
				<th>催促中</th>
                <th class="table-row-return"></th>
              </tr>
            </thead>
            <tbody>
	            <c:forEach var="overdue" items="${returnList}">
		            <tr>
		              <td>${overdue.bookId}</td>
		              <td>${overdue.title}</td>
		              <td>${overdue.author}</td>
		              <td>${overdue.dueDate}</td>
		              <td>${overdue.name}</td>

		              <c:choose>
 				 <c:when test="${overdue.alert == false}">
   					 <td class="table-row-return"><a href="overdue_send?id=${overdue.bookId}" class="btn btn-primary">送信</a></td>
  				</c:when>

  				<c:otherwise>
    				<td class="table-row-return"><a href="overdue_send?id=${overdue.bookId}" class="btn btn-primary">送信</a></td>
 					 </c:otherwise>
				</c:choose>

					<c:choose>
				 <c:when test="${overdue.alert == false}">
   					<td><span
style="
font-style: normal ;
font-weight: bold;
font-size: 1.0em;
line-height: 0.5em;
color: red;
font-family: arial;
">
未送信
</span></td>
  				</c:when>

  				<c:otherwise>
    				<td><span
style="
font-style: normal ;
font-weight: bold;
font-size: 1.0em;
line-height: 0.5em;
color: navy;
font-family: arial;
">
送信完了
</span></td>
 					 </c:otherwise>
					</c:choose>

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
