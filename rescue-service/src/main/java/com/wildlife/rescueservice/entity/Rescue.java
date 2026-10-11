
package com.wildlife.rescueservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rescue")
public class Rescue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rescueId;
    private String animalName;
    private String rescueDate;
    private String location;
    private String assignedStaff;
    private String description;
    private String status;

    public Rescue() {
    }

    public Long getRescueId() {
        return rescueId;
    }

    public void setRescueId(Long rescueId) {
        this.rescueId = rescueId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getRescueDate() {
        return rescueDate;
    }

    public void setRescueDate(String rescueDate) {
        this.rescueDate = rescueDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
