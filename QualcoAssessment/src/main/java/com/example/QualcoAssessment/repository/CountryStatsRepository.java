package com.example.QualcoAssessment.repository;


import com.example.QualcoAssessment.model.CountryStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CountryStatsRepository extends JpaRepository<CountryStats, Long> {

    @Query("select cs from country_stats cs order by ?1")
    List<CountryStats> getAllOrdered(String order);
}
