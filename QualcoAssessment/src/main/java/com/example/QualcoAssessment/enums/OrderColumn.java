package com.example.QualcoAssessment.enums;

public enum OrderColumn {
    CONTINENT("continent"),
    REGION("region"),
    COUNTRY("country");

    private String value;

    OrderColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
