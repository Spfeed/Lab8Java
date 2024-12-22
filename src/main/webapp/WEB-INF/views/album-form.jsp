<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма добавления/редактирования альбома</title>
    <script src="js/albums.js"></script>
    <script src="js/album-suggestions.js"></script>
</head>
<body>
<h1><%= request.getParameter("formAction").equals("save") ? "Добавить альбом" : "Изменить альбом" %></h1>
<%
    String formAction = (String) request.getAttribute("formAction");
    String title = "";
    String genre = "";
    int id = 0;
    int artistId = 0;

    if ("update".equals(formAction)) {
        com.example.models.Album album =
            (com.example.models.Album) request.getAttribute("album");
        if (album != null) {
            id = album.getAlbumId();
            title = album.getTitle();
            genre = album.getGenre();
            artistId = album.getArtist().getArtistId();
        }
    }
    List<com.example.models.Artist> artists =
            (List<com.example.models.Artist>) request.getAttribute("artists");
%>
<form action="albums" method="post">
    <input type="hidden" name="formAction" value="<%= formAction %>">
    <% if ("update".equals(formAction)) { %>
        <input type="hidden" name="id" value="<%= id %>">
    <% } %>
    <label for="title">Название:</label>
    <input type="text" id="title" name="title" list="albumSuggestions" value="<%= title %>" required>
    <datalist id="albumSuggestions"></datalist>
    <br>
    <label for="genre">Жанр:</label>
    <input type="text" id="genre" name="genre" value="<%= genre %>" required>
    <br>
    <label for="artistId">Артист:</label>
    <select id="artistId" name="artistId" required></select>
    <br>
    <input type="submit" value="<%= formAction.equals("save") ? "Добавить" : "Изменить" %> альбом">
</form>
<a href="albums?action=list">Вернуться к списку</a>
</body>
</html>
