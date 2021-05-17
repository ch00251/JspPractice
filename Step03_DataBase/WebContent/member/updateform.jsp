<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 파라미터로 전달되는 수정할 회원의 번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	//2. MemberDao 객체를 이용해서 수정할 회원의 정보를 얻어온다.
	MemberDto dto=MemberDao.getInstance().getData(num);
	//3. 수정할 회원의 정보를 form 에 잘 출력해서 응답한다. 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	/* navbar 가 상단에 고정(fixed) 되어 있기 때문에 공간이 남는다. */
	body{
		padding-top: 50px;
	}
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">Acorn</a>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath }/member/list.jsp">회원관리</a></li>
			<li><a href="#">게임</a></li>
			<li><a href="#">쇼핑</a></li>
		</ul>
	</div>
</div>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="list.jsp">목록</a></li>
		<li>회원정보 수정 폼</li>
	</ol>
	<h1>회원정보 수정 폼 입니다.</h1>
	<form action="update.jsp" method="post">
		<div class="form-group">
			<label for="num">번호</label>
			<input class="form-control" type="text" id="num" value="<%=num%>" disabled/>
			<!-- disabled 된 input 요소는 form 전송 했을때 전송이 되지 않는다.
			따라서 번호도 같이 전송되게 하려면 input type="hidden" 으로 만들어 놓고
			form 전송했을때 번호도 같이 전송되게 한다. -->
			<input type="hidden" name="num" value="<%=num%>"/>
		</div>
		<div class="form-group">
			<label for="name">이름</label>
			<input class="form-control" type="text" name="name" id="name" value="<%=dto.getName() %>" />
		</div>
		<div class="form-group">
			<label for="addr">주소</label>
			<input class="form-control" type="text" name="addr" id="addr" value="<%=dto.getAddr() %>" />
		</div>
		<button class="btn btn-primary" type="submit">수정확인</button>
		<button class="btn btn-warning" type="reset">취소</button>
	</form>
</div>
</body>
</html>