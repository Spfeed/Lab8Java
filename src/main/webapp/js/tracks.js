document.addEventListener("DOMContentLoaded", function () {
  const request = new XMLHttpRequest();

  request.open("GET", "api?action=getAlbums", true);

  request.onload = function () {
    if (request.status === 200) {
      const data = JSON.parse(request.responseText);

      const albumSelect = document.getElementById("albumId");
      albumSelect.innerHTML = "";

      data.forEach(function (album) {
        const option = document.createElement("option");
        option.value = album.albumId;
        option.textContent = album.title;
        albumSelect.appendChild(option);
      });
    } else {
      alert("Ошибка загрузки данных альбомов: " + request.status);
    }
  };

  request.onerror = function () {
    alert("Ошибка, не удалось загрузить данные альбомов. ");
  };

  request.send();
});
