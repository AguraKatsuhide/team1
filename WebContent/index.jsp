<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>SBooks</title>
    <link rel="stylesheet" href="./assets/css/bootstrap.css" media="screen">
    <link rel="stylesheet" href="./assets/css/custom.min.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="./assets/js/html5shiv.js"></script>
      <script src="./assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body id="aaa">

<div class="navbar-header">
       <h5 class="navbar-brand">
       		<span class="sbook">
				SBooks
			</span>
		</h5>
 </div>
    <%
   		if(request.getAttribute("isError") != null && (Boolean) request.getAttribute("isError")){ %>
                 <div class="alert alert-dismissible alert-warning">
                  <button type="button" class="close" data-dismiss="alert">×</button>
                  <h4>エラー</h4>
                  <p>＜ログインエラー＞社員番号またはパスワードが間違っています。再入力してください。</p>
                </div>
     <% }%>

    <%
   		if(request.getAttribute("isLogout") != null && (Boolean) request.getAttribute("isLogout")){ %>
                 <div class="alert alert-dismissible alert-info">
                  <button type="button" class="close" data-dismiss="alert">×</button>
                  <h4><b>ログアウトしました</b></h4>
                  <p>ログインしなおしてください。</p>
                </div>
     <% }%>

    <div class="content col-xs-12">

      <div class="content-main col-xs-12">
        <div class="container text-center content-login">
         <div class= "col-xs-12">
          <h3 class="page-header login-title col-xs-6 col-xs-offset-3"> ログイン</h3>
          <div class="row">
            <div class="col-xs-12">
                <form class="form-horizontal"
                	action="LoginServlet" method="POST">
                  <fieldset>
                     <div class="form-group">
                      	<label for="inputSyainID" class="col-xs-2 control-label" >社員番号</label>
                     	<div class="col-xs-10">
                        	<input type="text" class="form-control" id="inputSyainID" name="userId" placeholder="社員番号">
	                    </div>
	                 </div>
                     <div class="form-group">
                     	<label for="inputPassword" class="col-lg-2 control-label">パスワード</label>
                      	<div class="col-xs-10">
                        	<input type="password" class="form-control" id="inputPassword"  name="Password" placeholder="パスワード">
                        </div>
                     </div>
					<div class="form-submit col-xs-12">
                     <button type="login" class="btn btn-primary btn-login"> ログイン</button>
                  	</div>
                  </fieldset>
                </form>
              </div>
            </div>
               </div>
          </div>
      </div>
    </div>


    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/custom.js"></script>
​
  </body>

</html>
