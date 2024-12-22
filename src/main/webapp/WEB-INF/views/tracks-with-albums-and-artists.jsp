<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Треки с альбомами и артистами</title>
</head>
<body>
<h1>Треки с альбомами и артистами</h1>
<table border="1">
    <thead>
        <tr>
            <th>Название трека</th>
            <th>Продолжительность (в секундах)</th>
            <th>Название альбома</th>
            <th>Имя артиста</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Object[]> tracks = (List<Object[]>) request.getAttribute("tracks");
            for (Object[] track : tracks) {
        %>
            <tr>
                <td><%= track[0] %></td>
                <td><%= track[1] %></td>
                <td><%= track[2] %></td>
                <td><%= track[3] %></td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>
<a href="statistics">Вернуться к выбору</a>
</body>
</html>
