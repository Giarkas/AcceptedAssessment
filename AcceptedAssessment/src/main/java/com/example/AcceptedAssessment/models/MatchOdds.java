package com.example.AcceptedAssessment.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "matchodds")
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    private String specifier;

    @Column(precision = 10, scale = 2)
    private BigDecimal odd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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

    public static class Builder {
        private Match match;
        private String specifier;
        private BigDecimal odd;

        public Builder match(Match match) {
            this.match = match;
            return this;
        }
        public Builder specifier(String specifier) {
            this.specifier = specifier;
            return this;
        }
        public Builder odd(BigDecimal odd) {
            this.odd = odd;
            return this;
        }
        public MatchOdds build() {
            MatchOdds matchOdds = new MatchOdds();
            matchOdds.setMatch(match);
            matchOdds.setSpecifier(specifier);
            matchOdds.setOdd(odd);
            return matchOdds;
        }

    }
}
