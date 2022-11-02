<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	 <table class="table table-hover">
           <thead>
             <tr>
            <th>Num</th>
            <th>Writer</th>
            <th>Title</th>
            <th>Contents</th>
			</tr>
			</thead>
			<tbody>
			<tr>
			<th>${vo.num}</th>
            <td>${vo.writer}</td>
            <th>${vo.title}</th>
            <td>${vo.contents}</td>
            </tr>
			</tbody>
		</table>
	
<c:forEach items="${vo.qnaFileVOs}" var="my">
<img src="/file/qna/${my.fileName}">
<button type="button" class="btn btn-light" onclick="location.href='/fileDown/qna?fileNum=${my.fileNum}'"> <- Down</button>
</c:forEach>

</body>
</html>