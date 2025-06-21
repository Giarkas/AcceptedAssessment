package com.example.QualcoAssessment.services.impl;

import com.example.QualcoAssessment.enums.Order;
import com.example.QualcoAssessment.enums.OrderColumn;
import com.example.QualcoAssessment.model.Countries;
import com.example.QualcoAssessment.model.CountryLanguages;
import com.example.QualcoAssessment.model.CountryStats;
import com.example.QualcoAssessment.model.dtos.*;
import com.example.QualcoAssessment.repository.CountriesRepository;
import com.example.QualcoAssessment.repository.CountryLanguagesRepository;
import com.example.QualcoAssessment.repository.CountryStatsRepository;
import com.example.QualcoAssessment.repository.RegionsRepository;
import com.example.QualcoAssessment.services.CountriesService;
import jakarta.servlet.Filter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements CountriesService {

    private CountriesRepository countriesRepository;
    private RegionsRepository regionsRepository;
    private CountryLanguagesRepository countryLanguagesRepository;
    private CountryStatsRepository countryStatsRepository;

    CountriesServiceImpl(CountriesRepository countriesRepository, RegionsRepository regionsRepository, CountryLanguagesRepository countryLanguagesRepository, CountryStatsRepository countryStatsRepository) {
        this.countriesRepository = countriesRepository;
        this.regionsRepository = regionsRepository;
        this.countryLanguagesRepository = countryLanguagesRepository;
        this.countryStatsRepository = countryStatsRepository;
    }

    @Override
    public List<CountryDTO> getAllCountries() {

        List<Countries> countries = countriesRepository.getCountriesOrderedByNameDesc();

        List<CountryDTO> countryDTOs =  countries.stream()
                .map( c ->
            new CountryDTO(c.getName(), c.getArea(), c.getCountry_code2())
        ).collect(Collectors.toList());

        return countryDTOs;
    }

    @Override
    public CountryLanguageDTO getCountryLanguages(int countryId) {


        List<CountryLanguages> countryLanguages = countryLanguagesRepository.findByCountryId(countryId);


        if (countryLanguages.isEmpty())
            return new CountryLanguageDTO();


        List<LanguageDTO> languageDTOs = countryLanguages.stream()
                .map(cl -> new LanguageDTO(cl.getId().getLanguages().getLanguage(), cl.isOfficial()))
                .collect(Collectors.toList());



        return new CountryLanguageDTO(countryLanguages.getFirst().getId().getCountry().getName(), languageDTOs);
    }

    @Override
    public List<CountryStatsRecordDTO> getCountriesRecords() {

        List<CountryStats> countryStats = countryStatsRepository.findAll();

        Map<String, Optional<CountryStats>> maxGdpPerPopulationByCountry = countryStats.stream()
                .collect(Collectors.groupingBy(
                        stat -> stat.getId().getCountry().getName(), // Key for grouping: country name
                        Collectors.maxBy(Comparator.comparingDouble(CountryStats::getGdpPerPopulation)) // Downstream collector: find max GDP/Pop
                ));

        List<CountryStatsRecordDTO> filteredStats = maxGdpPerPopulationByCountry.values().stream()
                .filter(Optional::isPresent)
                .map(cs -> new CountryStatsRecordDTO(cs.get()))
                .collect(Collectors.toList());

        return filteredStats;
    }

    @Override
    public List<CountryStatsDTO> getCountriesStats(String continent, String region, int fromYear, int toYear, OrderColumn orderColumn, Order order) {
        List<CountryStats> countryStats = countryStatsRepository.findAll();

        Comparator<CountryStats> comparator;

        switch (orderColumn) {
            case COUNTRY:
                comparator = Comparator.comparing(cs -> cs.getId().getCountry().getName());
                break;
            case CONTINENT:
                comparator = Comparator.comparing(cs -> cs.getId().getCountry().getRegion().getContinent().getName());
                break;
            case REGION: // Assuming this is the default or another specific case
            default:
                comparator = Comparator.comparing(cs -> cs.getId().getCountry().getRegion().getName());
                break;
        }
        switch (order) {
            case DESCENDING:
                comparator = comparator.reversed();
                break;
            default:
                break;
        }


        return countryStats.stream()
                .sorted(comparator)
                .filter(cs -> {
                    if (continent != null && !"".equals(continent)) {
                        return cs.getId().getCountry().getRegion().getContinent().getName().equals(continent);
                    } else {
                        return true;
                    }
                })
                .filter(cs -> {
                    if (region != null && !"".equals(region)) {
                        return cs.getId().getCountry().getRegion().getName().equals(region);
                    } else {
                        return true;
                    }
                })
                .filter(cs -> cs.getId().getYear() >= fromYear && cs.getId().getYear() <= toYear)
                .map(CountryStatsDTO::new)
                .collect(Collectors.toList());
    }

}
