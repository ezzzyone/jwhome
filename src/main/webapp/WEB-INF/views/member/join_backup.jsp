<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/join.js"></script>
<script defer src="/js/util.js"></script>
<link href="/css/test.css" rel="stylesheet">
</head>
<body>

<form:form method="post" modelAttribute="memberVO" id="joinfrm">
<div>
    ID<form:input type="text" path="id" id="id" cssClass=""/>
    <form:errors path="id"></form:errors>
    <h5 id="h1" class="warning"></h5>
</div>
<div>
PW<form:password path="pw" id="pw"/>
 <form:errors path="pw"></form:errors>
<h5 id="h2" class="warning"></h5>
</div>
<div>
    PW2<form:password path="pw2" id="pw2"/>
     <form:errors path="pw2"></form:errors>
    <h5 id="h5" class="warning"></h5>
</div>
<div>
NAME<form:input path="name" id="name"/>
  <form:errors path="name"></form:errors>
<h5 id="h3" class="warning"></h5>
</div>
<div>
EMAIL<input type="text" name="email" id="email"/>
<h5 id="h4" class="warning"></h5>
</div>
<div>
<button type="button" id="joinbtn">join</button>
</div>
 </form:form>

<!-- 약관 test-->
<div>
    <div>
        ALL <input type="checkbox" id="all">
    </div>

    <div>
        동의1 <input type="checkbox" class="check" name="" id="">
        <div>
            약관1
        </div>
    </div>

    <div>
        동의2 <input type="checkbox" class="check" name="" id="">
        <div>
            약관2
        </div>
    </div>

    <div>
        동의3 <input type="checkbox" class="check" name="" id="">
        <div>
            약관3
        </div>
    </div>

</div>
</body>
</html>