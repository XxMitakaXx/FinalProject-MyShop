const categoryButton = document.getElementById("categories-button");
const categoriesNavbar = document.getElementById("categories-navbar");

categoryButton.addEventListener("click", (e) => {
    categoriesNavbar.classList.toggle("hide");
});