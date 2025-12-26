function viewProducts() {
  fetch("http://localhost:3000/api/products")
    .then(res => res.json())
    .then(data => products.innerHTML =
      JSON.stringify(data, null, 2));
}
