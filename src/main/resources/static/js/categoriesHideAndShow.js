const categoryButton = document.getElementById("categories-button");
const categoriesNavbar = document.getElementById("categories-navbar");
const body = document.querySelector("body");

categoryButton.addEventListener("click", (e) => {
    categoriesNavbar.classList.toggle("hide");
    body.classList.toggle("block-scroll");
});