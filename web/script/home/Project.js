"use strict";
class Project {
    constructor(name, pid = 0) {
        this.id = pid;
        this.projectName = name;
    }

    static getProjects() {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'getProjects');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.withCredentials = true;
    
        xhr.onload = function () {
            if (xhr.status == 200) {
                var Jprojects = JSON.parse(xhr.response);
                for (let Jproject of Jprojects) {
                    let project = new Project(Jproject.projectName, Jproject.id);
                    project.guiAddProject();
                    projectsArray.push(project);
                }
            } else {
                console.log(xhr.responseText);
            }
        }
        xhr.send();
    }

    static createProject() {
        var projectName = document.getElementById("project_name").value;
    
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'createProject');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.withCredentials = true;
    
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                var response = JSON.parse(xhr.responseText);
                if (response.success) {
                    alert(response.message);
                    let project = new Project(response.project.projectName, response.project.id);
                    project.guiAddProject();
                    projectsArray.push(project);
                } else {
                    alert(response.message);
                }
            } else {
                alert("Error :" + xhr.status)
            }
        };
        xhr.send('projectname=' + encodeURIComponent(projectName));
    }
    
    static enableCreateProjectMode() {
        document.querySelector(".home").style.display = 'none';
        document.querySelector("header").style.display = 'none';
        document.getElementById('create_project').style.display = 'flex';
    }

    guiAddProject() {
        let project = document.createElement('li');
        project.setAttribute('data-id', this.id);
        project.innerHTML = 
        `
            <div>
                ${this.projectName}
            </div>
            <div>
                <button data-id=${this.id}>Start</button>
            </div>
        `;

        document.querySelector(".home>ul").appendChild(project);
        project.querySelector("button").disabled = true;

        if (!running) {
            project.querySelector('button').onclick = e => start(e);
            project.querySelector('button').disabled = false;
        }
        document.getElementById('create_project').style.display = 'none';
        document.querySelector(".main").style.display = 'flex';
        document.querySelector("header").style.display = 'flex';
    }
}

function doEndOnSameDay(d1 = new Date(), d2 =  new Date()) {
    d1 = new Date(`${d1.getFullYear()}-${d1.getMonth() + 1}-${d1.getDate()}`);
    d2 = new Date(`${d2.getFullYear()}-${d2.getMonth() + 1}-${d2.getDate()}`)
    return (d1.getTime() - d2.getTime() === 0);
}
