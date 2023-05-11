
function getTimelines() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'gettimelines');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.withCredentials = true;

    xhr.onload = function () {
        if (xhr.status == 200) {
            let response = JSON.parse(xhr.response);
            console.log(response);
            if (response.success) {
                let array = response.TimeLines;
                array.forEach(element => {
                    var timeline = new TimeLine(element['project_id'], element['id']);
                    timeline.startTime = new Date(+element['startTime']);
                    timeline.endTime = new Date(+element['endTime']);
                    timelines.push(timeline);
                });
                console.log({ timelines })
                addTimeLines();
            } else {
                console.log("Not success");
            }
        } else {
            console.log("Else block");
            console.log(xhr.responseText);
        }
    }
    xhr.send("name=devender");
}

function addTimeLines() {
    let TimeLinediv = document.querySelector(".timelineArea");
    console.log("I am here");
    for (let index = 0; index < timelines.length; index++) {
        const element = timelines[index];
        if (index == 0) {
            let daydiv = document.createElement('div');
            daydiv.setAttribute('class', "day");
            lastDate = new Date(`${element.endTime.getFullYear()}-${element.endTime.getMonth() + 1}-${element.endTime.getDate()}`);
            let dateLine = document.createElement('div');
            dateLine.setAttribute('class', 'dateLine');
            dateLine.innerHTML = `${lastDate.getDate()}-${month[lastDate.getMonth()]}-${lastDate.getFullYear()}`
            daydiv.prepend(dateLine);
            let dayList = document.createElement('div');
            let timeLinelement = document.createElement('div');
            timeLinelement.setAttribute('class', 'timeline');
            timeLinelement.innerHTML = 
            `<div class="left">${projectsArray.find(findProject, element).projectName}</div>
            <div class="right">
                <div class="time">${element.startTime.getHours()}:${element.startTime.getMinutes()} ---- ${element.endTime.getHours()}:${element.endTime.getMinutes()}
                </div>
                <div class=elapsedTime>${element.elapsedTimestr()}</div>
            </div>`;
            dayList.prepend(timeLinelement)
            daydiv.appendChild(dayList);
            TimeLinediv.prepend(daydiv);
        } else {
            let thisdate = new Date(`${element.endTime.getFullYear()}-${element.endTime.getMonth() + 1}-${element.endTime.getDate()}`);
            console.log({ thisdate });
            console.log(thisdate.getTime() - lastDate.getTime());
            if ((thisdate - lastDate) === 0) {
                let timeLinelement = document.createElement('div');
                timeLinelement.setAttribute('class', 'timeline');
                timeLinelement.innerHTML = `<div class="left">${projectsArray.find(findProject, element).projectName}</div>
                <div class="right">
                    <div class="time">${element.startTime.getHours()}:${element.startTime.getMinutes()} ---- ${element.endTime.getHours()}:${element.endTime.getMinutes()} </div>
                    <div class=elapsedTime>${element.elapsedTimestr()}</div>
                </div>`;
                document.querySelector(".timelineArea .day *:last-child").prepend(timeLinelement);
            } else {
                let daydiv = document.createElement('div');
                daydiv.setAttribute('class', "day");
                lastDate = new Date(`${element.endTime.getFullYear()}-${element.endTime.getMonth() + 1}-${element.endTime.getDate()}`);
                let dateLine = document.createElement('div');
                dateLine.setAttribute('class', 'dateLine');
                dateLine.innerHTML = `${lastDate.getDate()}-${month[lastDate.getMonth() ]}-${lastDate.getFullYear()}`;
                daydiv.appendChild(dateLine);
                let dayList = document.createElement('div');
                let timeLinelement = document.createElement('div');
                timeLinelement.setAttribute('class', 'timeline');
                timeLinelement.innerHTML = `<div class="left">${projectsArray.find(findProject, element).projectName}</div>
                <div class="right">  
                    <div class="time">${element.startTime.getHours()}:${element.startTime.getMinutes()} ---- ${element.endTime.getHours()}:${element.endTime.getMinutes()} </div>
                    <div class=elapsedTime>${element.elapsedTimestr()}</div>
                </div>`;
                dayList.prepend(timeLinelement);
                daydiv.appendChild(dayList);
                TimeLinediv.prepend(daydiv);
            }
        }
    }
}

function findProject(currentValue) {
    return currentValue.id == this.projectId;
}

// Start tracking time
function start(even) {
    running = true;
    let pTarget = even.target;
    pTarget.className = "bg-red";
    const timeline = new TimeLine(pTarget.dataset['id']);
    pTarget.innerHTML = 'Stop';
    let timelineelement = document.createElement('div');
    timelineelement.setAttribute('class', "timeline active");
    timelineelement.innerHTML =
    `<div class="left">${projectsArray.find(findProject,timeline).projectName}</div>
        <div class="right">
        <div class="time">${timeline.startTime.getHours()}:${timeline.startTime.getMinutes()} ---- ??:?? </div>
        <div class=elapsedTime>??</div>
    </div>`;

    let timerid = setInterval(updateTimeElapsed,1000,timeline.startTime);
    
    let thisdate = new Date(`${timeline.startTime.getFullYear()}-${timeline.startTime.getMonth() + 1}-${timeline.startTime.getDate()}`);
    if (doEndOnSameDay(thisdate, lastDate)) {
        document.querySelector(".timelineArea .day *:last-child").prepend(timelineelement);
    } else {
        lastDate = thisdate;
        let daydiv = document.createElement('div');
        daydiv.setAttribute('class', "day");
        let dateLine = document.createElement('div');
        dateLine.setAttribute('class', 'dateLine');
        dateLine.innerHTML = `${thisdate.getDate()}-${month[thisdate.getMonth()]}-${thisdate.getFullYear()}`;
        daydiv.appendChild(dateLine);
        let dayList = document.createElement('div');
        dayList.prepend(timelineelement);
        daydiv.appendChild(dayList);
        document.querySelector(".timelineArea").prepend(daydiv);
    }

    // Removing onclick handler 
    document.querySelectorAll(".home ul li button").forEach(element => {element.onclick = null; element.disabled = true;});
    
    // changing onclick handeler for for active to stop
    pTarget.disabled = false;
    pTarget.onclick = e => (stop(e, timeline,timerid));
    

}

// Stop tracking time
function stop(e, timeline,timer) {
    clearInterval(timer);
    timeline.endTime = new Date();
    running = false;
    timelines.push(timeline);
    document.querySelector(".home .timeline.active").classList.remove('active');
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "saveTimeLine");
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    xhr.withCredentials = true;
    xhr.onload = function () {
        if (xhr.status == 200) {
            var response = JSON.parse(xhr.response);
            if (response.success) {
                document.querySelector(".home .timeline .right .time").innerHTML = `<div class="time">${timeline.startTime.getHours()}:${timeline.startTime.getMinutes()} ---- ${timeline.endTime.getHours()}:${timeline.endTime.getMinutes()} </div>`;
                document.querySelector(".home .timeline .right .elapsedTime").innerHTML = timeline.elapsedTimestr();
            }
        } else {
            console.log("Else block");
            console.log(xhr.responseText);
        }
    }

    xhr.send('startime=' + encodeURIComponent(timeline.startTime.getTime()) +
        '&endtime=' + encodeURIComponent(timeline.endTime.getTime()) +
        '&projectid=' + encodeURIComponent(timeline.projectId));

    e.target.className = "";
    e.target.innerHTML = 'Start';
    // Reactivating onclick handeler for projects
    document.querySelectorAll(".home ul li button").forEach(element => {element.onclick = e => (start(e)); element.disabled = false})
}

function updateTimeElapsed(date) {
    let timediff = Math.abs(date - new Date());
    let hours = Math.floor(timediff / (3600 * 1000));
    let minuts = Math.floor((timediff - hours * 3.6e6) / (60 * 1000));
    let seconds = Math.floor((timediff - hours * 3.6e6 - minuts * 6e4) / (1000));
    let elapsedTimeString = "";
    if (hours > 0) {
        elapsedTimeString += `${hours} hr `;
    }
    if (minuts > 0) {
        elapsedTimeString += `${minuts} min `;
    }
    elapsedTimeString += `${seconds} sec`;
   
    document.querySelector(".timeline .right :last-child").innerHTML = elapsedTimeString;
}