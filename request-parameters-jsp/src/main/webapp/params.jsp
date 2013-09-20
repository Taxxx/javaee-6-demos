<%@ page import="java.util.List" %>
<%@ page import="es.rchavarria.webapp.Parameter" %>
<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Rendering request params from a JSP file</title>

    <!-- Bootstrap core CSS -->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding-left: 40px">

<h2>Request parameters:</h2>
<ul class="list-group">
	<c:forEach var="p" items="${params}">
		<li class="list-group-item" style="max-width: 30%">${p.key}: ${p.value}</li>
	</c:forEach>
</ul>

</body>
</html>