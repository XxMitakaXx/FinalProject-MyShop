const images = document.querySelectorAll(".product-photo-view img");
const mainImage = document.querySelector(".product-main-photo");

images.forEach(image => {
    image.addEventListener("click", () => {
        const clickedImageUrl = image.getAttribute("src");
        mainImage.setAttribute("src", clickedImageUrl);
    });
});

const addReviewButton = document.querySelector(".add-review-button");
const reviewFormBrightnessContainer = document.querySelector(".form-blur-background-container");
const hideAddReviewFormButton = document.querySelector(".hide-add-review-form-button");
const bodyProductDetails = document.querySelector("body");

addReviewButton.addEventListener("click", () => {
    reviewFormBrightnessContainer.classList.toggle("show");
    window.scroll(0, 2080);
    bodyProductDetails.classList.toggle("block-scroll");
});

hideAddReviewFormButton.addEventListener("click", () => {
    reviewFormBrightnessContainer.classList.toggle("show");
    bodyProductDetails.classList.toggle("block-scroll");
});