package com.TestService.Testit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.*;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.putdatasets.Datasets;

import static com.jayway.restassured.RestAssured.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
  
{
	ResponseFactory robj ;
	static Logger logit ;
	Datasets dobj ;
	
	
	@Before
	public void setup(){
		
//Before starting the test make sure that the node js server is up an running and update the URI accordingly
		// The json is hosted on nodejs at the localhost 3000
		//This code only shows how to GET POST PUT PATCH and DELETE requests
		
		
		
	PropertyConfigurator.configure("./Resources/Logger.properties");
	robj =new ResponseFactory();
    logit = Logger.getLogger(this.getClass());
    
    try{   	
    	
    	//Hitting the server with GET request
    	
    logit.info("Hitting with GET request");	
	robj.setResp(get("http://localhost:3000/db"));
	logit.info("GET request successful");
	
    }catch(Exception e){
    	
    logit.error("Server unavailable -Response Code:"+get().statusCode());
   
    	
    }
	
	
		
	}
	
		
	@Test
	public void TC_001(){
		
		//getting and asserting the response code as 200 OK
		
		logit.info("Generating the Response Code");			
		Integer status =robj.getResp().getStatusCode();			
		logit.info("Status code Received"+status);
		Assert.assertTrue("Status received successfully", status.equals(200));
		
	
	}
	
	@Test
	public void TC_002(){
		
		
		//Printing the JSON String
		logit.info("Generating the JSON STRING");
		System.out.println("The obtained json is"+robj.getResp().asString());
		
	}
	
	@Test
	public void TC_003() {			
		dobj = new Datasets();		
		
		
		dobj.setId(3);
		dobj.setTitle("json-server2");
		dobj.setAuthor("Warmachine");
		
		logit.info("updating the json file with new dataset with "+"id:"+dobj.getId()+": "+"Author: "+dobj.getAuthor()+":"+"Title"+dobj.getTitle());
		try{
		Response res ;				
		res= given().when().
				contentType(ContentType.JSON).body(dobj).post("http://localhost:3000/posts");
		logit.info("Dataset Update successful");
		logit.info("Posts fetched from Server"+res.asString());		
		
		}catch(Exception e){
			
			logit.info("Unable to update data");
			
		}
		
		
		
	}
	
	
	
	@Test
	public void TC_004(){
		
		logit.info("Patching the information on id :3");
		
	Response res;	
     res = given().body("{\"title\":\"Title updated by put request\"}").when().contentType(ContentType.JSON).patch("http://localhost:3000/posts/3");
	
     logit.info("Patch Successful with info"+res.asString());
     
	 
	}
	
	@Test
	public void TC_005(){
		
		
		try{
		logit.info("Deleting the post with id: 3");
		Response res;	
	     res=given().when().delete("http://localhost:3000/posts/3");	    
	     logit.info("Delete Successful"+res.asString());
	     
		}catch(Exception e){
			
			logit.info("Unable to delete");
			
			
		}
	     
	}

	
}
