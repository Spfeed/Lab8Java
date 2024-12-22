<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список артистов</title>
</head>
<body>
<h1>Артисты</h1>
<a href="artists?action=form&formAction=save">Добавить артиста</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Действия</th>
    </tr>
    <%
        java.util.List<com.example.models.Artist> artists =
            (java.util.List<com.example.models.Artist>) request.getAttribute("artists");
        if (artists != null) {
            for (com.example.models.Artist artist : artists) {
    %>
    <tr>
        <td><%= artist.getArtistId() %></td>
        <td><%= artist.getName() %></td>
        <td>
            <a href="artists?action=view&id=<%= artist.getArtistId() %>">Подробно</a> |
            <a href="artists?action=delete&id=<%= artist.getArtistId() %>"
               onclick="return confirm('Вы уверены, что хотите удалить этого артиста?');">Удалить</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="3">Артисты не найдены.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="albums?action=list">Альбомы</a>
<a href="tracks?action=list">Треки</a>
<a href="statistics">Запросы</a>
</body>
</html>