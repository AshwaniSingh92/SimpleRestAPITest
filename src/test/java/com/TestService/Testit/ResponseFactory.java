package com.TestService.Testit;

import static com.jayway.restassured.RestAssured.get;

import com.jayway.restassured.response.Response;

public class ResponseFactory {
	
		
	private Response resp;
	
	
	

	public Response getResp() {
		return resp;
	}

	public void setResp(Response resp) {
		this.resp = resp;
	}

}
