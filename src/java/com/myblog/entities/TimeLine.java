package com.myblog.entities;

import java.util.Date;

/**
 *
 * @author Devender
 */
public class TimeLine {
    private int timelineId ;
    private int projectId;
    private Date starDate;
    private Date enDate;

    
    public TimeLine(int timelineId, int onwerProjectId, Date starDate, Date enDate) {
        this.timelineId = timelineId;
        this.projectId = onwerProjectId;
        this.starDate = starDate;
        this.enDate = enDate;
    }
    public TimeLine() {
    }
    public TimeLine(int onwerProjectId, Date starDate, Date enDate) {
        this.projectId = onwerProjectId;
        this.starDate = starDate;
        this.enDate = enDate;
    }
    public int getTimelineId() {
        return timelineId;
    }
    public void setTimelineId(int timelineId) {
        this.timelineId = timelineId;
    }
    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int onwerProjectId) {
        this.projectId = onwerProjectId;
    }
    public Date getStarDate() {
        return starDate;
    }
    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }
    public Date getEnDate() {
        return enDate;
    }
    public void setEnDate(Date enDate) {
        this.enDate = enDate;
    }
    
}
