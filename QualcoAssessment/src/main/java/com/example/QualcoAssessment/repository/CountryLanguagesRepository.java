package com.example.QualcoAssessment.repository;

import com.example.QualcoAssessment.model.CountryLanguages;
import com.example.QualcoAssessment.model.CountryLanguagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryLanguagesRepository extends JpaRepository<CountryLanguages, CountryLanguagesId> {

    @Query("select cl from country_languages cl where cl.id.country.country_id = ?1")
    List<CountryLanguages> findByCountryId(int countryId);
}
