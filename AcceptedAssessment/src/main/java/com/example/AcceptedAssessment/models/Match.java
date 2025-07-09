package com.example.AcceptedAssessment.models;

import com.example.AcceptedAssessment.converters.SportTypeConverter;
import com.example.AcceptedAssessment.models.dtos.MatchDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private LocalDate match_date;

    private LocalTime match_time;

    private String team_a;

    private String team_b;

    @Convert(converter = SportTypeConverter.class)
//    @Enumerated(EnumType.STRING)
    private SportType sport;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMatch_date() {
        return match_date;
    }

    public void setMatch_date(LocalDate date) {
        this.match_date = date;
    }

    public LocalTime getMatch_time() {
        return match_time;
    }

    public void setMatch_time(LocalTime match_time) {
        this.match_time = match_time;
    }

    public String getTeam_a() {
        return team_a;
    }

    public void setTeam_a(String team_a) {
        this.team_a = team_a;
    }

    public String getTeam_b() {
        return team_b;
    }

    public void setTeam_b(String team_b) {
        this.team_b = team_b;
    }

    public SportType getSport() {
        return sport;
    }

    public void setSport(SportType sport_type) {
        this.sport = sport_type;
    }

    public void fillEntity(MatchDTO matchDTO){
        this.setTeam_a(matchDTO.getTeam_a());
        this.setTeam_b(matchDTO.getTeam_b());
        this.setSport(SportType.fromId(matchDTO.getSport()));
        this.setDescription(matchDTO.getDescription());
        this.setMatch_date(matchDTO.getMatch_date());
        this.setMatch_time(matchDTO.getMatch_time());
    }

    public static class Builder{
        private long id;
        private String description;
        private LocalDate date;
        private LocalTime match_time;
        private String team_a;
        private String team_b;
        private SportType sport_type;

        public Builder id(long id) {
            this.id = id;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }
        public Builder match_time(LocalTime match_time) {
            this.match_time = match_time;
            return this;
        }
        public Builder team_a(String team_a) {
            this.team_a = team_a;
            return this;
        }
        public Builder team_b(String team_b) {
            this.team_b = team_b;
            return this;
        }
        public Builder sport_type(SportType sport_type) {
            this.sport_type = sport_type;
            return this;
        }

        public Match build() {
            Match match = new Match();
            match.setId(id);
            match.setDescription(description);
            match.setMatch_date(date);
            match.setMatch_time(match_time);
            match.setTeam_a(team_a);
            match.setTeam_b(team_b);
            match.setSport(sport_type);
            return match;
        }
    }
}

