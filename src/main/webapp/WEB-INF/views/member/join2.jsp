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
<form action="join" method="post" id="joinfrm">
<div>
    ID<input type="text" name="id" id="id"/>
    <h5 id="h1" class="warning"></h5>
</div>
<div>
PW<input type="text" name="pw" id="pw"/>
<h5 id="h2" class="warning"></h5>
</div>
<div>
    PW2<input type="text" id="pw2"/>
    <h5 id="h5" class="warning"></h5>
</div>
<div>
NAME<input type="text" name="name" id="name"/>
<h5 id="h3" class="warning"></h5>
</div>
<div>
EMAIL<input type="text" name="email" id="email"/>
<h5 id="h4" class="warning"></h5>
</div>
<div>
<button type="button" id="joinbtn">join</button>
</div>
</form>

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