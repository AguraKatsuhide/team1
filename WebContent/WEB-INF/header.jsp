<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<div class="navbar navbar-default navbar-fixed-top">
  <div class="navbar-container">
    <div class="navbar-header">
      <a href="./myPage" class="navbar-brand">SBooks</a>
    </div>
    <div class="navbar-collapse collapse" id="navbar-main">
      <ul class="nav navbar-nav navbar-right">
        <li>
          <form class="navbar-form" role="search" action="./bookList" method="post">
            <div class="form-group">
              <input type="hidden" name="params" value="title" />
              <input type="text" name="field" class="form-control" placeholder="タイトルを入力" />
            </div>
            <button type="submit" class="btn btn-primary">検索</button>
          </form>
        </li>
        <li class="logout"><a href="./LogoutServlet" class="btn btn-default btn-logout">ログアウト</a></li>
      </ul>

    </div>
  </div>
</div>