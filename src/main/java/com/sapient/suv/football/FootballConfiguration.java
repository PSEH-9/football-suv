package com.sapient.suv.football;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballConfiguration {
	
	@Value("${data.api}")
	private String dataApi;
	
	@Value("${api.key}")
	private String  apiKey;

	public String getDataApi() {
		return dataApi;
	}

	public void setDataApi(String dataApi) {
		this.dataApi = dataApi;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	

}
