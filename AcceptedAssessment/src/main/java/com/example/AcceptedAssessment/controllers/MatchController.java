package com.example.AcceptedAssessment.controllers;

import com.example.AcceptedAssessment.models.dtos.MatchDTO;
import com.example.AcceptedAssessment.services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    private MatchService matchService;

    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/match/{id}")
    public ResponseEntity<MatchDTO> match(@PathVariable long id) {

        return new ResponseEntity<>(matchService.getMatch(id), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/match")
    public ResponseEntity<Long> addMatch(@RequestBody MatchDTO matchDTO) {

        long id = matchService.addMatch(matchDTO);

        return  new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/match")
    public ResponseEntity<Long> updateMatch(@RequestBody MatchDTO matchDTO) {

        long id = matchService.updateMatch(matchDTO);

        return  new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/match/{id}")
    public ResponseEntity deleteMatch(@PathVariable long id) {

        matchService.deleteMatch(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
