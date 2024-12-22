document.addEventListener("DOMContentLoaded", function () {
  const request = new XMLHttpRequest();

  request.open("GET", "api?action=getArtists", true);

  request.onload = function () {
    if (request.status === 200) {
      const data = JSON.parse(request.responseText);

      const artistSelect = document.getElementById("artistId");
      artistSelect.innerHTML = "";

      data.forEach(function (artist) {
        const option = document.createElement("option");
        option.value = artist.artistId;
        option.textContent = artist.name;
        artistSelect.appendChild(option);
      });
    } else {
      alert("Ошибка загрузки данных артистов: " + request.status);
    }
  };

  request.onerror = function () {
    alert("Ошибка, не удалось загрузить данные артистов. ");
  };

  request.send();
});
