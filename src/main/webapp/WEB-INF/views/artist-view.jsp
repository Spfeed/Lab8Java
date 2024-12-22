<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Описание артиста</title>
</head>
<body>
<h1>Описание артиста</h1>
<%
    com.example.models.Artist artist = (com.example.models.Artist) request.getAttribute("artist");
    if (artist != null) {
%>
<p><strong>ID:</strong> <%= artist.getArtistId() %></p>
<p><strong>Имя:</strong> <%= artist.getName() %></p>
<a href="artists?action=form&formAction=update&id=<%= artist.getArtistId() %>">Изменить</a> |
<a href="artists?action=list">Вернуться к списку</a>
<%
    } else {
%>
<p>Артист не найден.</p>
<a href="artists?action=list">Вернуться к списку</a>
<%
    }
%>
</body>
</html>
