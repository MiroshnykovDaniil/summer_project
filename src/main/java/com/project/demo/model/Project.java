package com.project.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    private int projectId;
    private String name;
    private String shortName;
    private String description;
    private int initialEffort;
    private String timeStart;
    private String timeEnd;
    private String risks;
    private int resultEffort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResultEffort() {
        return resultEffort;
    }

    public void setResultEffort(int resultEffort) {
        this.resultEffort = resultEffort;
    }

    public Project(){

    }

    public Project( int projectId,String projectName) {
        this.name=projectName;
        this.projectId=projectId;
    }

    public void setTimeStart(String timeStart){
        this.timeStart=timeStart;
    }

    public void setTimeEnd(String timeEnd){
        this.timeEnd=timeEnd;
    }

    public String getRisks() { return risks; }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public int getInitialEffort() {
        return initialEffort;
    }

    public void setInitialEffort(int initialEffort) {
        this.initialEffort = initialEffort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProjectName() {
        return name;
    }

    public void setProjectName(String projectName) {
        this.name = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
