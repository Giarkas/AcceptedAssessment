package com.example.AcceptedAssessment.models.dtos;

import java.math.BigDecimal;

public class MatchOddDTO {

    private long id;
    private String specifier;
    private BigDecimal odd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }
}
