const images = document.querySelectorAll(".product-photo-view img");
const mainImage = document.querySelector(".product-main-photo");

images.forEach(image => {
    image.addEventListener("click", () => {
        const clickedImageUrl = image.getAttribute("src");
        mainImage.setAttribute("src", clickedImageUrl);
    });
});