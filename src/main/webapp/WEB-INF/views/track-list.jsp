<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список треков</title>
</head>
<body>
<h1>Треки</h1>
<a href="tracks?action=form&formAction=save">Добавить трек</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Продолжительность</th>
        <th>Альбом</th>
        <th>Действия</th>
    </tr>
    <%
        List<com.example.models.Track> tracks = (List<com.example.models.Track>) request.getAttribute("tracks");
        if (tracks != null) {
            for (com.example.models.Track track : tracks) {
    %>
    <tr>
        <td><%= track.getTrackId() %></td>
        <td><%= track.getTitle() %></td>
        <td><%= track.getDuration() %></td>
        <td><%= track.getAlbum().getTitle() %></td>
        <td>
            <a href="tracks?action=view&id=<%= track.getTrackId() %>">Подробно</a> |
            <a href="tracks?action=delete&id=<%= track.getTrackId() %>"
               onclick="return confirm('Вы уверены, что хотите удалить этот трек?');">Удалить</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="5">Треки не найдены.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="artists?action=list">Артисты</a>
<a href="albums?action=list">Альбомы</a>
<a href="statistics">Запросы</a>
</body>
</html>
