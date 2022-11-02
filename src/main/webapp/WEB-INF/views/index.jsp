<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<div>
<button class="btn btn-light" type="button" onclick="location='./qna/list?page=${page}&search=${search}'">QNA List</button>
<button class="btn btn-light" type="button" onclick="location='./qna/write'">QNA Write</button>
</div>
<img alt="" src="/resources/img/home.jpeg">


<img alt="" src="/img/home.jpeg">

</body>
</html>