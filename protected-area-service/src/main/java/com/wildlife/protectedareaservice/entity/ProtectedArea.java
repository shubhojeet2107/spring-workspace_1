
package com.wildlife.protectedareaservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "protected_area")
public class ProtectedArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaId;
    private String areaName;
    private String areaType;
    private String location;
    private Double totalArea;
    private String status;

    public ProtectedArea() {
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
