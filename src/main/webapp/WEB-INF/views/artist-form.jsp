<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Форма добавления/редактирования артистов</title>
    <script src="js/artist-suggestions.js"></script>
  </head>
  <body>
    <h1>
      <%= request.getParameter("formAction").equals("save") ? "Добавить артиста"
      : "Изменить артиста" %>
    </h1>
    <% String formAction = (String) request.getAttribute("formAction"); String
    name = ""; int id = 0; if ("update".equals(formAction)) {
    com.example.models.Artist artist = (com.example.models.Artist)
    request.getAttribute("artist"); if (artist != null) { id =
    artist.getArtistId(); name = artist.getName(); } } %>
    <form action="artists" method="post">
      <input type="hidden" name="formAction" value="<%= formAction %>" />
      <% if ("update".equals(formAction)) { %>
      <input type="hidden" name="id" value="<%= id %>" />
      <% } %>
      <label for="name">Имя:</label>
      <input
        type="text"
        id="name"
        name="name"
        list="artistSuggestions"
        value="<%= name %>"
        required
      />
      <datalist id="artistSuggestions"></datalist>
      <br />
      <button type="submit">
        <%= "save".equals(formAction) ? "Добавить" : "Изменить" %>
      </button>
    </form>
    <a href="artists?action=list">Отменить</a>
  </body>
</html>
