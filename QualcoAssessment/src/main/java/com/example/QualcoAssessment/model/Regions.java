package com.example.QualcoAssessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Regions {

    @Id
    @JsonProperty
    private int region_id;

    @JsonProperty
    private String name;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id", referencedColumnName = "continent_id")
    private Continents continent;

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continents getContinent() {
        return continent;
    }

    public void setContinent(Continents continent) {
        this.continent = continent;
    }
}
