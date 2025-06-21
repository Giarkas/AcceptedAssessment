package com.example.QualcoAssessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "country_languages")
public class CountryLanguages {

    @EmbeddedId
    @JsonProperty
    private CountryLanguagesId id;

    @JsonProperty
    private boolean official;

    public CountryLanguagesId getId() {
        return id;
    }

    public void setId(CountryLanguagesId id) {
        this.id = id;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }
}
