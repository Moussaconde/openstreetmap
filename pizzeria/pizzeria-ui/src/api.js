export const getURL = () => {
  const queryParameters = new URLSearchParams(window.location.search);
  return (
    (queryParameters.get('url') || '').trimEnd('/') || 'https://pizzeria.joxit.dev'
  );
};

export const get = path =>
  fetch(`${getURL()}/pizzas${path}`).then(response => {
    if (response.ok) {
      return response.json();
    } else if (response.status >= 400) {
      return response.text().then(function (text) {
        throw text;
      });
    }
  });
