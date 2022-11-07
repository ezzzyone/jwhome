<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.error}
${param.message}
<!-- ${msg} -->
<form action="./login" method="post">
<!-- csrf token test -->
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
ID<input type="text" name="id" />
PW<input type="text" name="pw"/>
Check Cookie<input type="checkbox" name="rememberId"/>
자동로그인<input type="checkbox" name="rememberMe"/>
Cookie?<input type="text" value="${cookie.userId.value}" />
<button type="submit" >login</button>
</form>
<script type="text/javascript">
history.replaceState({}, null, location.pathname)
</script>
</body>
</html>