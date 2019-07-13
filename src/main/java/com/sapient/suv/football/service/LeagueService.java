package com.sapient.suv.football.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapient.suv.football.FootballConfiguration;
import com.sapient.suv.football.domain.League;

@Service
public class LeagueService {

	@Autowired
	private RestTemplate restClient;

	@Autowired
	private FootballConfiguration configuration;

	public String getLeagueId(String countryId, String leagueName) {
		ResponseEntity<List<League>> response = restClient.exchange(
				configuration.getDataApi() + "?action=get_leagues&country_id=" + countryId + "&APIkey="
						+ configuration.getApiKey(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<League>>() {
				});
		Map<String, String> leagueMap = response.getBody().stream()
				.collect(Collectors.toMap(League::getLeagueName, League::getLeagueId));
		return leagueMap.get(leagueName);
	}

}
