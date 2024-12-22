<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список альбомов</title>
</head>
<body>
<h1>Альбомы</h1>
<a href="albums?action=form&formAction=save">Добавить альбом</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Жанр</th>
        <th>Артист</th>
        <th>Действия</th>
    </tr>
    <%
        java.util.List<com.example.models.Album> albums =
            (java.util.List<com.example.models.Album>) request.getAttribute("albums");
        if (albums != null) {
            for (com.example.models.Album album : albums) {
    %>
    <tr>
        <td><%= album.getAlbumId() %></td>
        <td><%= album.getTitle() %></td>
        <td><%= album.getGenre() %></td>
        <td><%= album.getArtist().getName() %></td>
        <td>
            <a href="albums?action=view&id=<%= album.getAlbumId() %>">Подробно</a> |
            <a href="albums?action=delete&id=<%= album.getAlbumId() %>"
               onclick="return confirm('Вы уверены, что хотите удалить этот альбом?');">Удалить</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="5">Альбомы не найдены.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="artists?action=list">Артисты</a>
<a href="tracks?action=list">Треки</a>
<a href="statistics">Запросы</a>
</body>
</html>
