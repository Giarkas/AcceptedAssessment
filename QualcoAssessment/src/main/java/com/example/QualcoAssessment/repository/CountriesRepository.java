package com.example.QualcoAssessment.repository;

import com.example.QualcoAssessment.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {

    @Query("select c from Countries c order by c.name")
    List<Countries> getCountriesOrderedByNameDesc();
}
