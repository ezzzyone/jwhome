<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/test.css" rel="stylesheet">
<c:import url="temp/boot.jsp"></c:import>
<script defer src="/js/test.js"></script>
</head>
<body>
<h1>Jiwon Home</h1>

<sec:authentication property="Principal" var="user"/>
<sec:authorize access="isAuthenticated()"> <!-- access="isAuthenticated()" 인증되면 true -->
<h3 style="font-size: small; color: gray;"><spring:message code="welcome" arguments="${user.name}"></spring:message></h3>
<h3 style="font-size: small; color: gray;"><spring:message code="welcome2" arguments="${user.id},${user.name}" argumentSeparator=","></spring:message></h3>
<div>
<button class="btn btn-light" type="button" onclick="location='./qna/list?page=${page}&search=${search}'">QNA List</button>
<button class="btn btn-light" type="button" onclick="location='./qna/write'">QNA Write</button>
<button class="btn btn-light" type="button" onclick="location='./member/mypage'">Mypage</button>
<form action="./member/logout" method="post">
<sec:csrfInput/>
<button class="btn btn-light" type="button" id="kakao">Kakao Logout</button>
<button class="btn btn-light" type="button" onclick="location='./member/logout'">Logout</button>
<button class="btn btn-light" type="button" onclick="location='./member/delete'">Delete</button>
</form>

</div>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<div>
<button class="btn btn-light" type="button" onclick="location='/oauth2/authorization/kakao'">Kakao Login</button>
<button class="btn btn-light" type="button" onclick="location='./member/login'">Login</button>
<button class="btn btn-light" type="button" onclick="location='./member/join'">join</button>
<button class="btn btn-light" type="button" onclick="location='./qna/list?page=${page}&search=${search}'">QNA List</button>
<button class="btn btn-light" type="button" onclick="location='./qna/write'">QNA Write</button>
</div>
</sec:authorize>
<img alt="" src="/resources/img/home.jpeg">


<img alt="" src="/img/home.jpeg">

</body>
<script type="text/javascript">
$("#kakao").click(function() {
	
})
</script>
</html>