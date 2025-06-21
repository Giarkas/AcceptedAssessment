package com.example.QualcoAssessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "region_areas")
public class RegionAreas {

    @Id
    @JsonProperty
    private String region_name;

    @JsonProperty
    private String region_area;

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_area() {
        return region_area;
    }

    public void setRegion_area(String region_area) {
        this.region_area = region_area;
    }
}

