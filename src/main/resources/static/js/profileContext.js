const profileLink = document.getElementById("profile-link");
const profileContext = document.getElementById("profile-context");
const profileContextMenu = document.getElementById("inner-profile-context");

profileLink.addEventListener("mouseover", () => {
    profileContextMenu.style.visibility = "visible";
    profileContextMenu.style.transform = "translateY(0)";
});

document.addEventListener("click", () => {
    profileContextMenu.style.visibility = "hidden";
    profileContextMenu.style.transform = "translateY(10px)";
});