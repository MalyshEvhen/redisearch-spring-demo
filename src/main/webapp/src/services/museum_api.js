const BASE_URL = 'http://localhost:8080';

const MuseumAPI = function () { };

MuseumAPI.parseResponse = async function (response) {
  const body = await response.text();
  const json = JSON.parse(body);

  return json;
};

MuseumAPI.getSuggestions = async function (query) {
  const requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  let actionUrl = `${BASE_URL}/api/search/autocomplete/${encodeURIComponent(query)}`;

  const response = await fetch(actionUrl, requestOptions);

  return MuseumAPI.parseResponse(response);
}

export default MuseumAPI;