package com.sapient.suv.football.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sapient.suv.football.FootballConfiguration;
import com.sapient.suv.football.domain.Country;

@Component
public class CountryCache {

	@Autowired
	private RestTemplate restClient;

	@Autowired
	private FootballConfiguration configuration;

	private static Map<String, String> countryCache = new ConcurrentHashMap<>();

	@PostConstruct
	public void initCache() {
		ResponseEntity<List<Country>> response = restClient.exchange(
				configuration.getDataApi() + "?action=get_countries&APIkey=" + configuration.getApiKey(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
				});
		countryCache = response.getBody().stream().collect(Collectors.toMap(Country::getCountryName, Country::getCountryId));

	}

	public String getCountryId(String countryName) {
		return countryCache.get(countryName);
	}

}
