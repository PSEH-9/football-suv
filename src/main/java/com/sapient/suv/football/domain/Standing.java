package com.sapient.suv.football.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Standing {

	@JsonProperty("Country ID & Name")
	private String country;

	@JsonProperty("League ID & Name")
	private String league;

	@JsonProperty("Team ID & Name")
	private String team;

	@JsonProperty("Overall League Position")
	private String overallPosition;

	public Standing(String country, String league, String team, String overallPosition) {

		this.country = country;
		this.league = league;
		this.team = team;
		this.overallPosition = overallPosition;
	}
	
	public Standing() {
		
	}

	public String getCountry() {
		return country;
	}

	public String getLeague() {
		return league;
	}

	public String getTeam() {
		return team;
	}

	public String getOverallPosition() {
		return overallPosition;
	}

}
