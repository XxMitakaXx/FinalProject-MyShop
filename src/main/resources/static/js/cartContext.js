const infoContext = document.getElementById("info");
const cartButton = document.getElementById("cart-button");
const productLink = document.getElementById("product-link");
const removeFromCartButton = document.getElementById("remove-from-cart-button");


cartButton.addEventListener("mouseover", () => {
    infoContext.style.visibility = "visible";
    infoContext.style.transform = "translateY(0)";
});

document.addEventListener("click", () => {
    infoContext.style.visibility = "hidden";
    infoContext.style.transform = "translateY(5px)";
});


productLink.addEventListener("mouseover", () => {
    removeFromCartButton.style.visibility = "visible";
    removeFromCartButton.style.transform = "translateY(0)";
});


productLink.addEventListener("mouseout", () => {
    removeFromCartButton.style.visibility = "hidden";
    removeFromCartButton.style.transform = "translateY(-10px)";
});