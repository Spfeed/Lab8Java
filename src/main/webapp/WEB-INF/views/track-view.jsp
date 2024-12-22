<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Описание трека</title>
</head>
<body>
<h1>Описание трека</h1>
<%
    com.example.models.Track track = (com.example.models.Track) request.getAttribute("track");
    if (track != null) {
%>
<p><strong>ID:</strong> <%= track.getTrackId() %></p>
<p><strong>Название:</strong> <%= track.getTitle() %></p>
<p><strong>Продолжительность:</strong> <%= track.getDuration() %></p>
<p><strong>Альюом:</strong> <%= track.getAlbum().getTitle() %></p>
<a href="tracks?action=form&formAction=update&id=<%= track.getTrackId() %>">Изменить</a> |
<a href="tracks?action=list">Вернуться к списку</a>
<%
    } else {
%>
<p>Трек не найден.</p>
<a href="tracks?action=list">Вернуться к списку</a>
<%
    }
%>
</body>
</html>
