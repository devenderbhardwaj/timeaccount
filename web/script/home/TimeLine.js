
class TimeLine {
    constructor(project_id, timeline_id = 0) {
        this.id = timeline_id;
        this.projectId = project_id;
        this.startTime = new Date();
        this.endTime = new Date();
    }
    
    elapsedTimestr() {
        let timediff = Math.abs(this.startTime - this.endTime);
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
        elapsedTimeString += `${seconds} sec `;
        return elapsedTimeString;
    }
}
