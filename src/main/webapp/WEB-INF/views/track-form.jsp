<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма создания/редактирования трека</title>
    <script src="js/tracks.js"></script>
    <script src="js/track-suggestions.js"></script>
</head>
<body>
<h1><%= request.getParameter("formAction").equals("save") ? "Добавить трек" : "Редактировать трек" %></h1>
<%
    String formAction = (String) request.getAttribute("formAction");
    String title = "";
    int duration = 0;
    int id = 0;
    int albumId = 0;

    if ("update".equals(formAction)) {
        com.example.models.Track track = (com.example.models.Track) request.getAttribute("track");
        if (track != null) {
            id = track.getTrackId();
            title = track.getTitle();
            duration = track.getDuration();
            albumId = track.getAlbum().getAlbumId();
        }
    }
    List<com.example.models.Album> albums = (List<com.example.models.Album>) request.getAttribute("albums");
%>
<form action="tracks" method="post">
    <input type="hidden" name="formAction" value="<%= formAction %>">
    <% if ("update".equals(formAction)) { %>
        <input type="hidden" name="id" value="<%= id %>">
    <% } %>
    <label for="title">Название:</label>
    <input type="text" id="title" name="title" list="trackSuggestions" value="<%= title %>" required>
    <datalist id="trackSuggestions"></datalist>
    <br>
    <label for="duration">Продолжительность (в секундах):</label>
    <input type="number" id="duration" name="duration" value="<%= duration %>" required><br>
    <label for="albumId">Альбом:</label>
    <select id="albumId" name="albumId" required></select>
    <br>
    <input type="submit" value="<%= formAction.equals("save") ? "Добавить" : "Изменить" %> трек">
</form>
<a href="tracks?action=list">Вернуться к списку</a>
</body>
</html>
