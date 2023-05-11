Project.getProjects();
getTimelines();

document.querySelector("#create_project form").onsubmit = Project.enableCreateProjectMode();
