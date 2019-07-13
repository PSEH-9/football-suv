package com.sapient.suv.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.suv.football.domain.Standing;
import com.sapient.suv.football.service.StandingService;

@RestController
@RequestMapping(path = "/standings")
public class StandingController {

	@Autowired
	private StandingService standingService;

	@GetMapping
	public ResponseEntity<Standing> getStanding(@RequestParam("countryName") String countryName,
			@RequestParam("leagueName") String leagueName, @RequestParam("teamName") String teamName) {
		return new ResponseEntity<>(standingService.getStandings(countryName, leagueName, teamName), HttpStatus.OK);
	}

}
