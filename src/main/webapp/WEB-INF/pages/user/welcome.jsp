<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page session="false"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>Welcome</title>--%>
<%--</head>--%>
<%--<body>--%>
	<%--<jsp:include page="menu.jsp" />--%>
	<%--� ����--%>
	<%--<h3 style="color: red;">Hello Admin</h3>--%>
<%--</body>--%>
<%--</html>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
Hello Admin
<form id="logoutForm" method="POST" action="/logout">
    <input type="button" value="submit">
</form>


<a href="<c:url value="/logout" />">Logout</a>