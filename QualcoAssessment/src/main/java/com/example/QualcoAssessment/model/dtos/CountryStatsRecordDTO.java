package com.example.QualcoAssessment.model.dtos;

import com.example.QualcoAssessment.model.CountryStats;

public class CountryStatsRecordDTO {

    private String name;
    private String country_code3;
    private int year;
    private int population;
    private double gdp;

    public CountryStatsRecordDTO() {
    }

    public CountryStatsRecordDTO(String name, String country_code3, int year, int population, double gdp) {
        this.name = name;
        this.country_code3 = country_code3;
        this.year = year;
        this.population = population;
        this.gdp = gdp;
    }

    public CountryStatsRecordDTO(CountryStats cs) {
        this.name = cs.getId().getCountry().getName();
        this.country_code3 = cs.getId().getCountry().getCountry_code3();
        this.year = cs.getId().getYear();
        this.population = cs.getPopulation();
        this.gdp = cs.getGdp();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public void setCountry_code3(String country_code3) {
        this.country_code3 = country_code3;
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
