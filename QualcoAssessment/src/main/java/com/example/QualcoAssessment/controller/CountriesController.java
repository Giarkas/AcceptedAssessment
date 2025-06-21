package com.example.QualcoAssessment.controller;

import com.example.QualcoAssessment.enums.Order;
import com.example.QualcoAssessment.enums.OrderColumn;
import com.example.QualcoAssessment.model.dtos.CountryDTO;
import com.example.QualcoAssessment.model.dtos.CountryLanguageDTO;
import com.example.QualcoAssessment.model.dtos.CountryStatsDTO;
import com.example.QualcoAssessment.model.dtos.CountryStatsRecordDTO;
import com.example.QualcoAssessment.services.CountriesService;
import jakarta.websocket.server.PathParam;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountriesController {

    private CountriesService countriesService;

    CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    //Task1 a
    @RequestMapping(method = RequestMethod.GET, path = "/getCountries")
    public List<CountryDTO> getNation(){
        return countriesService.getAllCountries();
    }

    //Task1 b
    @RequestMapping(method = RequestMethod.GET, path = "/getCountryLanguages/{country_id}")
    public CountryLanguageDTO getCountryLanguages(@PathVariable int country_id){
        return countriesService.getCountryLanguages(country_id);
    }

    //Task2
    @RequestMapping(method = RequestMethod.GET, path = "/getCountriesRecords")
    public List<CountryStatsRecordDTO> getCountriesRecords(){
        return countriesService.getCountriesRecords();
    }

    //Task 3
    @RequestMapping(method = RequestMethod.GET, path = "/getCountriesStats")
    public List<CountryStatsDTO> getCountriesStats(@RequestParam(required = false) String continent,
                                                   @RequestParam(required = false) String region,
                                                   @RequestParam(required = false, defaultValue = "0") Integer fromYear,
                                                   @RequestParam(required = false, defaultValue = "9999") Integer toYear,
                                                   @RequestParam(required = false, defaultValue = "CONTINENT") OrderColumn orderColumn,
                                                   @RequestParam(required = false, defaultValue = "ASCENDING") Order order){

        return countriesService.getCountriesStats(continent, region, fromYear, toYear, orderColumn, order);
    }

}
