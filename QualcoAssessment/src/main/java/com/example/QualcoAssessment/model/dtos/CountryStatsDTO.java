package com.example.QualcoAssessment.model.dtos;

import com.example.QualcoAssessment.model.CountryStats;

public class CountryStatsDTO {
    private String continent;
    private String region;
    private String country;
    private int year;
    private int population;
    private double gdp;

    public CountryStatsDTO(CountryStats countryStats) {
        this.continent = countryStats.getId().getCountry().getRegion().getContinent().getName();
        this.region = countryStats.getId().getCountry().getRegion().getName();
        this.country = countryStats.getId().getCountry().getName();
        this.year = countryStats.getId().getYear();
        this.population = countryStats.getPopulation();
        this.gdp = countryStats.getGdp();
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }
}
