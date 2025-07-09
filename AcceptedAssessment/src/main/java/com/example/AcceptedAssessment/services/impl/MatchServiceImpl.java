package com.example.AcceptedAssessment.services.impl;

import com.example.AcceptedAssessment.exceptions.*;
import com.example.AcceptedAssessment.models.Match;
import com.example.AcceptedAssessment.models.MatchOdds;
import com.example.AcceptedAssessment.models.SportType;
import com.example.AcceptedAssessment.models.dtos.MatchDTO;
import com.example.AcceptedAssessment.models.dtos.MatchOddDTO;
import com.example.AcceptedAssessment.repositories.MatchOddsRepository;
import com.example.AcceptedAssessment.repositories.MatchRepository;
import com.example.AcceptedAssessment.services.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private MatchOddsRepository matchOddsRepository;

    public MatchServiceImpl(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository) {
        this.matchRepository = matchRepository;
        this.matchOddsRepository = matchOddsRepository;
    }

    @Override
    public long addMatch(MatchDTO matchDTO) {

        Match match = createMatch(matchDTO);

        match = matchRepository.save(match);

        if (!matchDTO.getMatch_odds().isEmpty()){
            List<MatchOdds> odds = getOdds(matchDTO.getMatch_odds(), match);
            matchOddsRepository.saveAll(odds);

        }


        return match.getId();
    }

    @Override
    public MatchDTO getMatch(long id) {
        List<MatchOdds> matchOdds = matchOddsRepository.getByMatchId(id);
        if (matchOdds.isEmpty()) {
            return getMatchDTO(matchRepository.getReferenceById(id));
        }

        return getMatchDTO(matchOdds);
    }

    @Override
    public long updateMatch(MatchDTO matchDTO) {

        validateValues(matchDTO);

        List<MatchOdds> matchOdds = matchOddsRepository.getByMatchId(matchDTO.getId());
        Match match = null;

        if (matchOdds.isEmpty())
            match = matchRepository.getReferenceById(matchDTO.getId());
        else
            match = matchOdds.getFirst().getMatch();

        if (match == null)
            throw new MatchNotFoundException("The match with id '" + matchDTO.getId() + "' was not found!");


        match.fillEntity(matchDTO);

        if (!matchDTO.getMatch_odds().isEmpty()){
            for (MatchOddDTO matchOddDTO : matchDTO.getMatch_odds()) {
                MatchOdds matchOdd = matchOddsRepository.getBySpecifierId(matchOddDTO.getSpecifier());
                if (matchOdd == null){
                    matchOdd = getOdd(matchOddDTO, match);
                } else {
                    matchOdd.setOdd(matchOddDTO.getOdd());
                }
                matchOddsRepository.save(matchOdd);
            }
        }

        return match.getId();

    }

    @Override
    public void deleteMatch(long id) {
        Match match = matchRepository.getById(id);

        matchRepository.delete(match);
    }

    private MatchDTO getMatchDTO(Match match) {

        try {

            MatchDTO matchDTO = new MatchDTO();

            matchDTO.setId(match.getId());
            matchDTO.setMatch_date(match.getMatch_date());
            matchDTO.setMatch_time(match.getMatch_time());
            matchDTO.setDescription(match.getDescription());
            matchDTO.setSport(match.getSport().getValue());
            matchDTO.setTeam_a(match.getTeam_a());
            matchDTO.setTeam_b(match.getTeam_b());

            return matchDTO;
        } catch (EntityNotFoundException e) {
            throw new MatchNotFoundException("The match was not found!");
        }
    }

    private MatchDTO getMatchDTO(List<MatchOdds> matchOdds) {

        MatchDTO matchDTO = new MatchDTO();

        Match match = matchOdds.getFirst().getMatch();


        matchDTO.setId(match.getId());
        matchDTO.setMatch_date(match.getMatch_date());
        matchDTO.setMatch_time(match.getMatch_time());
        matchDTO.setDescription(match.getDescription());
        matchDTO.setSport(match.getSport().getValue());
        matchDTO.setMatch_odds(getMatchOddDTOs(matchOdds));
        matchDTO.setTeam_a(match.getTeam_a());
        matchDTO.setTeam_b(match.getTeam_b());

        return matchDTO;
    }

    private List<MatchOddDTO> getMatchOddDTOs(List<MatchOdds> matchOdds) {

        List<MatchOddDTO> matchOddDTOs = new ArrayList<>();

        for (MatchOdds matchOdd : matchOdds) {
            MatchOddDTO matchOddDTO = new MatchOddDTO();
            matchOddDTO.setId(matchOdd.getId());
            matchOddDTO.setOdd(matchOdd.getOdd());
            matchOddDTO.setSpecifier(matchOdd.getSpecifier());

            matchOddDTOs.add(matchOddDTO);
        }

        return matchOddDTOs;
    }

    private List<MatchOdds> getOdds(List<MatchOddDTO> matchOdds, Match match) {

        List<MatchOdds> odds = new ArrayList<>();

        for (MatchOddDTO matchOddDTO : matchOdds) {
            odds.add(getOdd(matchOddDTO, match));
        }

        return odds;
    }

    private MatchOdds getOdd(MatchOddDTO matchOdd, Match match) {

        return new MatchOdds.Builder().match(match)
                .odd(matchOdd.getOdd())
                .specifier(matchOdd.getSpecifier())
                .build();
    }

    private Match createMatch(MatchDTO matchDTO) {

        validateValues(matchDTO);

       return new Match.Builder().id(matchDTO.getId())
                .description(matchDTO.getDescription())
                .date(matchDTO.getMatch_date())
                .match_time(matchDTO.getMatch_time())
                .team_a(matchDTO.getTeam_a())
                .team_b(matchDTO.getTeam_b())
                .sport_type(SportType.fromId(matchDTO.getSport()))
                .build();
    }

    private void validateValues(MatchDTO matchDTO) {
        if (matchDTO.getMatch_date() == null || matchDTO.getMatch_date().isBefore(LocalDate.now()))
            throw new InvalidDateException("Date is either null or lies in the past");

        if (matchDTO.getTeam_a().isEmpty() || matchDTO.getTeam_a().length() < 3)
            throw new InvalidTeamNameException("Team a's name is invalid! It has to be at least 3 characters!");

        if (matchDTO.getTeam_b().isEmpty() || matchDTO.getTeam_b().length() < 3)
            throw new InvalidTeamNameException("Team a's name is invalid! It has to be at least 3 characters!");

        List<String> specifiers = new ArrayList<>();

        for (MatchOddDTO matchOddDTO : matchDTO.getMatch_odds()){
            if (matchOddDTO.getSpecifier().isEmpty())
                throw new InvalidSpecifierException("Specifier cannot be empty!");

            if (specifiers.contains(matchOddDTO.getSpecifier())) {
                throw new InvalidSpecifierException("Specifier already exists!");
            } else
                specifiers.add(matchOddDTO.getSpecifier());

            if (matchOddDTO.getOdd() == null)
                throw new InvalidOddException("Odd cannot be null!");
        }
    }
}
