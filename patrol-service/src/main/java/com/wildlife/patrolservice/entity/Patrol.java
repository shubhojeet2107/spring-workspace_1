
package com.wildlife.patrolservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patrol")
public class Patrol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patrolId;

    private String patrolDate;
    private String staffName;
    private String patrolTeam;
    private String protectedArea;
    private String route;
    private String activityDetails;
    private String status;

    public Patrol() {
    }

    public Long getPatrolId() {
        return patrolId;
    }

    public void setPatrolId(Long patrolId) {
        this.patrolId = patrolId;
    }

    public String getPatrolDate() {
        return patrolDate;
    }

    public void setPatrolDate(String patrolDate) {
        this.patrolDate = patrolDate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPatrolTeam() {
        return patrolTeam;
    }

    public void setPatrolTeam(String patrolTeam) {
        this.patrolTeam = patrolTeam;
    }

    public String getProtectedArea() {
        return protectedArea;
    }

    public void setProtectedArea(String protectedArea) {
        this.protectedArea = protectedArea;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
