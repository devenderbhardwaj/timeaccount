package com.myblog.entities;

public class Project {
    private int projectId;
    private String projectName;
    private int projectUserId;

    public Project(String projectName, int projectUser) {
        this.projectName = projectName;
        this.projectUserId = projectUser;
    }
    public Project(int projectId, String projectName, int projectUser) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectUserId = projectUser;
    }
    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public int getProjectUserId() {
        return projectUserId;
    }
    public void setProjectUserId(int projectUser) {
        this.projectUserId = projectUser;
    } 
}
