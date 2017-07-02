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


	<%@ include file="/WEB-INF/header.jsp"%>
	<div class="content col-xs-12">
		<!-- サイドバー -->
		<jsp:include page="/WEB-INF/sidebar.jsp" />
		<div class="content-main col-xs-10">
			<div class="container">

				<!-- Forms
        ================================================== -->
<%@ page import="app.Book"%>
<%
Book registerBook = (Book) session.getAttribute("registerBook");
%>
            <%
   		if(request.getAttribute("isError") != null && (Boolean) request.getAttribute("isError")){ %>
                 <div class="alert alert-dismissible alert-warning">
                  <button type="button" class="close" data-dismiss="alert">×</button>
                  <h4><b>エラー</b></h4>
                  <p>入力に不備があります。すべての入力項目を記入してください。</p>
                </div>
     <% }%>


				<!-- div class="bs-docs-section" -->
					<div class="row">
						<div class="col-lg-12">
							<div class="page-header">
								<h3 id="forms">蔵書登録</h3>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-6">
							<!-- <div class="well bs-component"> -->
							<div>

								<form class="form-horizontal"
									action="./RegisterBookServlet" method="post">
									<fieldset>


												<legend><br>登録する図書の情報を記入してください。</legend>
												<div class="form-group">
													<label for="selectGanre_label"
														class="col-lg-2 control-label">ジャンル</label>
													<div class="col-lg-10">
														<select class="form-control" name="genre_name"
															id="selectGanre_label">
															<option></option>
															<% if(registerBook != null && ("文学").equals(registerBook.getGenreName())){%>
																<option selected>文学</option>
															<% }else{ %>
																<option>文学</option>
															<% }  %>

															<% if(registerBook != null && ("人文・思想").equals(registerBook.getGenreName())){%>
																<option selected>人文・思想</option>
															<% }else{ %>
																<option>人文・思想</option>
															<% }  %>

															<% if(registerBook != null && ("社会・政治・法律").equals(registerBook.getGenreName())){%>
																<option selected>社会・政治・法律</option>
															<% }else{ %>
																<option>社会・政治・法律</option>
															<% }  %>

															<% if(registerBook != null && ("ノンフィクション").equals(registerBook.getGenreName())){%>
																<option selected>ノンフィクション</option>
															<% }else{ %>
																<option>ノンフィクション</option>
															<% }  %>

															<% if(registerBook != null && ("歴史・地理").equals(registerBook.getGenreName())){%>
																<option selected>歴史・地理</option>
															<% }else{ %>
																<option>歴史・地理</option>
															<% }  %>

															<% if(registerBook != null && ("ビジネス・経済・金融").equals(registerBook.getGenreName())){%>
																<option selected>ビジネス・経済・金融</option>
															<% }else{ %>
																<option>ビジネス・経済・金融</option>
															<% }  %>

															<% if(registerBook != null && ("科学・テクノロジー").equals(registerBook.getGenreName())){%>
																<option selected>科学・テクノロジー</option>
															<% }else{ %>
																<option>科学・テクノロジー</option>
															<% }  %>

															<% if(registerBook != null && ("医学").equals(registerBook.getGenreName())){%>
																<option selected>医学</option>
															<% }else{ %>
																<option>医学</option>
															<% }  %>

															<% if(registerBook != null && ("コンピュータ・IT").equals(registerBook.getGenreName())){%>
																<option selected>コンピュータ・IT</option>
															<% }else{ %>
																<option>コンピュータ・IT</option>
															<% }  %>

															<% if(registerBook != null && ("芸術・建築").equals(registerBook.getGenreName())){%>
																<option selected>芸術・建築</option>
															<% }else{ %>
																<option>芸術・建築</option>
															<% }  %>

															<% if(registerBook != null && ("趣味・実用").equals(registerBook.getGenreName())){%>
																<option selected>趣味・実用</option>
															<% }else{ %>
																<option>趣味・実用</option>
															<% }  %>

															<% if(registerBook != null && ("語学").equals(registerBook.getGenreName())){%>
																<option selected>語学</option>
															<% }else{ %>
																<option>語学</option>
															<% }  %>

															<% if(registerBook != null && ("その他").equals(registerBook.getGenreName())){%>
																<option selected>その他</option>
															<% }else{ %>
																<option>その他</option>
															<% }  %>
														</select> <br>
													</div>
												</div>

												<div class="form-group">
													<label for="inputTitle_label"
														class="col-lg-2 control-label">タイトル</label>
													<div class="col-lg-10">
														<input type="text" name="title" class="form-control"
															id="inputTitle_label" placeholder="タイトル" value="${registerBook.title}">
													</div>
												</div>

												<div class="form-group">
													<label for="inputAuthor_label"
														class="col-lg-2 control-label">著者</label>
													<div class="col-lg-10">
														<input type="text" name="author" class="form-control"
															id="inputAuthor_label" placeholder="著者" value="${registerBook.author}">
													</div>
												</div>
												<div class="form-group">
													<label for="inputPublisher_label"
														class="col-lg-2 control-label">出版社</label>
													<div class="col-lg-10">
														<input type="text" name="publisher" class="form-control"
															id="inputPublisher_label" placeholder="出版社" value="${registerBook.publisher}">
													</div>
												</div>

												<div class="form-group">
													<label for="inputPub_Year_label"
														class="col-lg-2 control-label">出版年</label>
													<div class="col-lg-10">
														<input type="text" name="pub_year" class="form-control" maxlength="4" pattern="^[0-9/]+$"
															id="inputPub_Year_label" placeholder="出版年 (yyyy)" value="${registerBook.pubYear}">
													</div>
												</div>

												<div class="form-group">
													<label for="inputISBN_label" class="col-lg-2 control-label">ISBN</label>
													<div class="col-lg-10">
														<input type="text" name="ISBN" class="form-control" pattern="^[0-9-]+$"
															id="inputISBN_label" placeholder="ISBN" value="${registerBook.isbn}">
													</div>
												</div>

												<div class="form-group">
													<label for="inputComment_label"
														class="col-lg-2 control-label">概要</label>
													<div class="col-lg-10">
														<textarea class="form-control" name="comment" rows="3"
															id="inputComment_label"><%  if(registerBook != null){%><%= registerBook.getComment()%><% } %></textarea>
														<span class="help-block">ここに本の概要を記入してください</span>
													</div>
												</div>
												<!--                     <div class="form-group">
                      <div class="col-lg-10 col-lg-offset-2">
                        <button type="reset" class="btn btn-default">クリア</button>
                        <button type="submit" class="btn btn-primary" onclick="location.href='registrationCompletion.jsp'">登録</button>
                      </div>
                    </div> -->

									</fieldset>
									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-8">
											<button type="reset" class="btn btn-default" onclick="location.href='RegisterBookServlet?action=clear'">クリア</button>
											<!-- onclick="location.href='/team1/RegisterBookServlet?action=null'" -->
											<button type="submit" class="btn btn-primary">確認</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!--             <div class="form-group">
              <div class="col-lg-10 col-lg-offset-3">
                <button type="reset" class="btn btn-default">クリア</button>
                <button type="submit" class="btn btn-primary" onclick="location.href='registrationCompletion.jsp'">登録</button>
              </div>
            </div> -->
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
