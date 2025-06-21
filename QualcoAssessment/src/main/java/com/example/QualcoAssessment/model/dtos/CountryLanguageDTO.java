package com.example.QualcoAssessment.model.dtos;


import java.util.List;

public class CountryLanguageDTO {

    private String country_name;
    private List<LanguageDTO> languageDTOS;

    public CountryLanguageDTO() {
    }
    public CountryLanguageDTO(String country_name, List<LanguageDTO> languageDTOS) {
        this.country_name = country_name;
        this.languageDTOS = languageDTOS;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<LanguageDTO> getLanguageDTOS() {
        return languageDTOS;
    }

    public void setLanguageDTOS(List<LanguageDTO> languageDTOS) {
        this.languageDTOS = languageDTOS;
    }
}
