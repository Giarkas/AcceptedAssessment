package com.example.AcceptedAssessment.services;

import com.example.AcceptedAssessment.models.dtos.MatchDTO;

public interface MatchService {
    long addMatch(MatchDTO matchDTO);

    MatchDTO getMatch(long id);

    long updateMatch(MatchDTO matchDTO);

    void deleteMatch(long id);
}
