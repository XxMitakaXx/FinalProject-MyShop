const adminLink = document.getElementById("admin-link");
const adminContext = document.getElementById("admin-context");
const adminContextMenu = document.getElementById("inner-admin-context");

adminLink.addEventListener("mouseover", () => {
    adminContextMenu.style.visibility = "visible";
    adminContextMenu.style.transform = "translateY(0)";
});

document.addEventListener("click", () => {
    adminContextMenu.style.visibility = "hidden";
    adminContextMenu.style.transform = "translateY(10px)";
});