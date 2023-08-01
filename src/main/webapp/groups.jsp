<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Group List</title>
</head>
<body>
<h1>Group List</h1>

<form action="groups" method="post">
    <label>Title:</label>
    <input type="text" name="title" required>
    <input type="submit" value="Add new group">
</form>

<ul>
    <c:forEach var="group" items="${groups}">
        <li>${group.id}</li>
        <li>${group.title}</li>
        <li>${group.students}</li>
    </c:forEach>
</ul>
</body>
</html>
