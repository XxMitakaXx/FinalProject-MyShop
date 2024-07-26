const userAddress = document.querySelector(".user-address");
const addressInput = document.getElementById("location");

userAddress.addEventListener("change", () => {
    addressInput.value = userAddress.value;
})