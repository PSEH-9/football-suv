package com.sapient.suv.football;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sapient.suv.football.domain.Standing;

public class StandingServiceTest {

	private static final String TEAM_NAME_ARSENAL_URL = "http://localhost:8080/standings?countryName=England&leagueName=Premier League&teamName=Arsenal";
	private static final String TEAM_NAME_LEICESTER_URL = "http://localhost:8080/standings?countryName=England&leagueName=Premier League&teamName=Leicester";
	private static final String COUNTRY_NAME_INDIA_URL = "http://localhost:8080/standings?countryName=India&leagueName=Santosh Trophy&teamName=Services";
	
	
	private RestTemplate restClient = new RestTemplate();
	
	
	@Test
	public void shouldReturnStanding() {
		Standing standing = getStanding(TEAM_NAME_LEICESTER_URL);
		assertNotNull(standing);
	}

	

	@Test
	public void shouldReturnStandingCountryNameAnId() {
		Standing standing = getStanding(TEAM_NAME_LEICESTER_URL);
		assertEquals("(41) - England", standing.getCountry());
	}
	
	@Test
	public void shouldReturnStandingCountryNameAnIdForOther() {
		Standing standing = getStanding(COUNTRY_NAME_INDIA_URL);
		assertEquals("(62) - India", standing.getCountry());
	}

	@Test
	public void shouldReturnStandingLeagueNameAnId() {

		Standing standing = getStanding(TEAM_NAME_LEICESTER_URL);
		assertEquals("(148) - Premier League", standing.getLeague());
	}

	@Test
	public void shouldReturnStandingTeamNameAnId() {

		Standing standing = getStanding(TEAM_NAME_LEICESTER_URL);
		assertEquals("(2611) - Leicester", standing.getTeam());
	}

	@Test
	public void shouldReturnStandingPosition() {

		Standing standing = getStanding(TEAM_NAME_LEICESTER_URL);
		assertEquals("13", standing.getOverallPosition());
	}
	
	@Test
	public void shouldReturnStandingPositionForOther() {

		Standing standing = getStanding(TEAM_NAME_ARSENAL_URL);
		assertEquals("1", standing.getOverallPosition());
	}
	
	private Standing getStanding(String url) {
		ResponseEntity<Standing> response = restClient.exchange(
				url,
				HttpMethod.GET, null, new ParameterizedTypeReference<Standing>() {
				});
		Standing standing = response.getBody();
		return standing;
	}

}
