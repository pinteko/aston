<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
</head>
<body>
<h1>Student List</h1>

<form action="students" method="post">
    <label>Name:</label>
    <input type="text" name="name" required>
    <label>Age:</label>
    <input type="number" name="age" required>
    <label>Group:</label>
    <input type="number" name="group" required>
    <input type="submit" value="Add new student">
</form>

<ul>
    <c:forEach var="student" items="${students}">
        <li>${student.id}</li>
        <li>${student.name}</li>
        <li>${student.age}</li>
        <li>${student.groups}</li>
    </c:forEach>
</ul>
</body>
</html>
