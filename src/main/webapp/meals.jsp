<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        table, th, td {
            border: 1px solid black;
            width: 50%;
        }

        th {
            text-align: center;
        }

        tr.red {
            color: red;
        }

        tr.green {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>
            Дата
        </th>
        <th>
            Описание
        </th>
        <th>
            Калории
        </th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.excess ? 'red':'green' }">
            <td>${fn:formatToString(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.request.contextPath}/meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="${pageContext.servletContext.contextPath}/meals" method="post">
    <table>
        <legend> Добавить прием пищи </legend>
        <tr>
            <td><b>Дата</b></td>
            <td><input type="datetime-local" name="dateTime" value="Время приема пищи"></td>
        </tr>

        <tr>
            <td><b>Описание</b></td>
            <td><input type="text" name="description" value="Описание"></td>
        </tr>

        <tr>
            <td><b>Калории</b></td>
            <td><input type="number" name="calories" value="1000"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>

</body>
</html>