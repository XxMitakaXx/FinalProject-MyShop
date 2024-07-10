const profileLink = document.getElementById("profile-link");
const profileContext = document.getElementById("profile-context");
const contextMenu = document.getElementById("context");

profileLink.addEventListener("mouseover", () => {
    contextMenu.style.visibility = "visible";
    contextMenu.style.transform = "translateY(0)";
});

document.addEventListener("click", () => {
    contextMenu.style.visibility = "hidden";
    contextMenu.style.transform = "translateY(10px)";
});