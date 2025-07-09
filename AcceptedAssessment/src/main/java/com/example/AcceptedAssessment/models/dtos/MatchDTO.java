package com.example.AcceptedAssessment.models.dtos;

import com.example.AcceptedAssessment.models.SportType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchDTO {

    private long id;
    private String description;
    private LocalDate match_date;
    @Schema(type = "string", format = "time", example = "HH:MM:SS")
    private LocalTime match_time;
    private String team_a;
    private String team_b;
    @Schema(description = "Sport ID (1 for FOOTBALL, 2 for BASKETBALL)", example = "1")
    private int sport;
    private List<MatchOddDTO> match_odds;

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

    public void setMatch_date(LocalDate match_date) {
        this.match_date = match_date;
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

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public List<MatchOddDTO> getMatch_odds() {
        return match_odds;
    }

    public void setMatch_odds(List<MatchOddDTO> match_odds) {
        this.match_odds = match_odds;
    }
}
