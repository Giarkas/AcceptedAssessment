package com.example.QualcoAssessment.services;

import com.example.QualcoAssessment.enums.Order;
import com.example.QualcoAssessment.enums.OrderColumn;
import com.example.QualcoAssessment.model.dtos.CountryDTO;
import com.example.QualcoAssessment.model.dtos.CountryLanguageDTO;
import com.example.QualcoAssessment.model.dtos.CountryStatsDTO;
import com.example.QualcoAssessment.model.dtos.CountryStatsRecordDTO;

import java.util.List;

public interface CountriesService {
    List<CountryDTO> getAllCountries();

    CountryLanguageDTO getCountryLanguages(int countryId);

    List<CountryStatsRecordDTO> getCountriesRecords();

    List<CountryStatsDTO> getCountriesStats(String continent, String region, int fromYear, int toYear, OrderColumn orderColumn, Order order);
}
