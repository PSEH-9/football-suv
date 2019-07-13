package com.sapient.suv.football.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapient.suv.football.FootballConfiguration;
import com.sapient.suv.football.domain.Standing;
import com.sapient.suv.football.domain.TeamPosition;

@Service
public class StandingService {

	@Autowired
	private CountryCache countryCache;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private RestTemplate restClient;

	@Autowired
	private FootballConfiguration configuration;

	public Standing getStandings(String countryName, String leagueName, String teamName) {
		String countryId = countryCache.getCountryId(countryName);
		String leagueId = getLeagueId(countryId, leagueName);
		TeamPosition teamPosition = getTeamPosition(leagueId, teamName);
		return new Standing("(" + countryId + ") - " + countryName,
							"(" + leagueId + ") - " + leagueName,
							"(" + teamPosition.getTeamId() + ") - " + teamName,
							teamPosition.getPosition());
		
	}

	private TeamPosition getTeamPosition(String leagueId, String teamName) {
		ResponseEntity<List<TeamPosition>> response = restClient.exchange(
				configuration.getDataApi() + "?action=get_standings&league_id=" + leagueId + "&APIkey="
						+ configuration.getApiKey(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<TeamPosition>>() {
				});
		Map<String, TeamPosition> leagueMap = response.getBody().stream()
				.collect(Collectors.toMap(TeamPosition::getTeamName, Function.identity()));
		return leagueMap.get(teamName);
	}

	private String getLeagueId(String countryId, String leagueName) {
		return leagueService.getLeagueId(countryId, leagueName);

	}

}
