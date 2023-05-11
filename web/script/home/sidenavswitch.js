let homebar = document.querySelector("body>nav .navhome");
homebar.onclick = e => switchTohome();
function reset() {
    document.querySelectorAll("body .main").forEach(element => {
        element.style.display = 'none';
    });
    document.querySelectorAll("body>nav>div").forEach(element => {
        element.classList.remove("activeNavbar");
    })
}
function switchTohome() {
   reset();
    document.querySelector("body #home").style.display = 'flex';
    homebar.classList.add('activeNavbar');
}

let projectbar = document.querySelector("body>nav .navprojects");
projectbar.onclick = e => switchToProject();
function switchToProject() {
    reset();
    document.querySelector("body #projects").style.display = 'flex';
    projectbar.classList.add('activeNavbar');

}