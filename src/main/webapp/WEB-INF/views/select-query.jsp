<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Выберите запрос</title>
</head>
<body>
<h1>Выберите запрос для выполнения</h1>
<form action="statistics" method="get">
    <p>
        <label>
            <input type="radio" name="action" value="tracks" required>
            Просмотр треков с исполинтелями и альбомами
        </label>
    </p>
    <p>
        <label>
            <input type="radio" name="action" value="albums" required>
            Просмотр альбомов с количеством треков и общей длительностью
        </label>
    </p>
    <p>
        <button type="submit">Submit</button>
    </p>
</form>
<a href="artists?action=list">Артисты</a>
<a href="albums?action=list">Альбомы</a>
<a href="tracks?action=list">Треки</a>
</body>
</html>
