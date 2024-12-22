document.addEventListener("DOMContentLoaded", function () {
  const nameInput = document.getElementById("name");

  nameInput.addEventListener("input", function () {
    const query = nameInput.value.trim();
    if (query.length < 1) {
      return;
    }

    const request = new XMLHttpRequest();
    request.open(
      "GET",
      `api?action=getArtistSuggestions&query=${encodeURIComponent(query)}`,
      true
    );

    request.onload = function () {
      if (request.status === 200) {
        const suggestions = JSON.parse(request.responseText);

        const datalist = document.getElementById("artistSuggestions");
        if (!datalist) {
          console.error("Не найден элемент <datalist> с id artistSuggestions");
          return;
        }
        datalist.innerHTML = "";

        suggestions.forEach(function (suggestion) {
          const option = document.createElement("option");
          option.value = suggestion;
          datalist.appendChild(option);
        });
      } else {
        console.error("Ошибка загрузки подсказок: " + request.status);
      }
    };
    request.send();
  });
});
