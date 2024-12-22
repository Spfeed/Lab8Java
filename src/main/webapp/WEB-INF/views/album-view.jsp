<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Описание альбома</title>
</head>
<body>
<h1>Описание альбома</h1>
<%
    com.example.models.Album album = (com.example.models.Album) request.getAttribute("album");
    if (album != null) {
%>
<p><strong>ID:</strong> <%= album.getAlbumId() %></p>
<p><strong>Название:</strong> <%= album.getTitle() %></p>
<p><strong>Жанр:</strong> <%= album.getGenre() %></p>
<p><strong>Артист:</strong> <%= album.getArtist().getName() %></p>
<a href="albums?action=form&formAction=update&id=<%= album.getAlbumId() %>">Изменить</a> |
<a href="albums?action=list">Вернуться к списку</a>
<%
    } else {
%>
<p>Альбом не найден.</p>
<a href="albums?action=list">Вернуться к списку</a>
<%
    }
%>
</body>
</html>
