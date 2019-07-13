package com.sapient.suv.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		validate(countryName, leagueName, teamName);
		return new ResponseEntity<>(standingService.getStandings(countryName, leagueName, teamName), HttpStatus.OK);
	}

	private void validate(String countryName, String leagueName, String teamName) {
		if (StringUtils.isEmpty(countryName)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country Name required .");
		}

		if (StringUtils.isEmpty(leagueName)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "League Name required .");
		}

		if (StringUtils.isEmpty(teamName)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team Name required");
		}
	}

}
