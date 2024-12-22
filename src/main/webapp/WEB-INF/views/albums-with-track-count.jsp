<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Альбомы с количеством треков и общей продолжительностью</title>
</head>
<body>
<h1>Альбомы с количеством треков и общей продолжительностью</h1>
<table border="1">
    <thead>
        <tr>
            <th>Название альбома</th>
            <th>Количество треков</th>
            <th>Общая продолжительность (в секундах)</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Object[]> albums = (List<Object[]>) request.getAttribute("albums");
            for (Object[] album : albums) {
        %>
            <tr>
                <td><%= album[0] %></td>
                <td><%= album[1] %></td>
                <td><%= album[2] %></td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>
<a href="statistics">Вернуться к выбору</a>
</body>
</html>
