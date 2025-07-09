package com.example.AcceptedAssessment.repositories;

import com.example.AcceptedAssessment.models.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    @Query("select mo from matchodds mo where mo.match.id =?1")
    List<MatchOdds> getByMatchId(long id);
    @Query("select mo from matchodds mo where mo.specifier =?1")
    MatchOdds getBySpecifierId(String specifier);
}
