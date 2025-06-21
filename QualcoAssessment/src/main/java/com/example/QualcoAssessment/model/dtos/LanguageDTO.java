package com.example.QualcoAssessment.model.dtos;

public class LanguageDTO {

    private String language;
    private boolean official;

    public LanguageDTO() {}

    public LanguageDTO(String language, boolean official) {
        this.language = language;
        this.official = official;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }
}
