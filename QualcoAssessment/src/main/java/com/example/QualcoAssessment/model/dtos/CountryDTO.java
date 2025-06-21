package com.example.QualcoAssessment.model.dtos;


public class CountryDTO {
    private String countryName;
    private Double area;
    private String country_code2;

    public CountryDTO() {
    }

    public CountryDTO(String countryName, Double area, String country_code2) {
        this.countryName = countryName;
        this.area = area;
        this.country_code2 = country_code2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCountry_code2() {
        return country_code2;
    }

    public void setCountry_code2(String country_code2) {
        this.country_code2 = country_code2;
    }
}
