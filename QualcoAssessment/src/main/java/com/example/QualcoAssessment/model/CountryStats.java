package com.example.QualcoAssessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity(name = "country_stats")
public class CountryStats {

    @EmbeddedId
    private CountryStatsId id;

    @JsonProperty
    private int population;

    @JsonProperty
    private Double gdp;

    public CountryStatsId getId() {
        return id;
    }

    public void setId(CountryStatsId id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public double getGdpPerPopulation() {
        if (population == 0) {
            return 0.0; // Avoid division by zero
        }
        return gdp / population;
    }
}
