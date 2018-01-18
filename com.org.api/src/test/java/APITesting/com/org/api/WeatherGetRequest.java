package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class WeatherGetRequest {

	@Test
	public void Test_01() {

		Response resp = when().get(
				"http://api.openweathermap.org/data/2.5/weather?id=2172797&appid=673c5650a20311041c26d61291b186ae");
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}

	@Test

	public void Test_03() {

		Response resp = given().

				param("q", "London").param("appid", "673c5650a20311041c26d61291b186ae").

				when().

				get("http://api.openweathermap.org/data/2.5/weather");

		System.out.println(resp.getStatusCode());

		// Assert.assertEquals(resp.getStatusCode(), 200);

		if (resp.getStatusCode() == 200) {

			System.out.println("API is working");

		} else {

			System.out.println("API is not working");

		}

	}
	// Assert our test cases in rest assured

	@Test
	public void Test_04() {

		given().param("q", "London").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather").then().assertThat().statusCode(200);
	}

	@Test
	public void Test_06() {

		Response resp = given().param("q", "London").
				param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());
	}
	@Test
		public void Test_07(){
			//Float
			String weatherReport=given().
				      parameter("id","2172797").
				      parameter("appid","673c5650a20311041c26d61291b186ae").
				      when().
				      get("http://api.openweathermap.org/data/2.5/weather").
				      then().
				      contentType(ContentType.JSON).
				      extract().
				      path("weather[0].description");
				      //path("wind.speed");

					  System.out.println("WeatherReport: "+weatherReport);
		}
	@Test
		public void Test_08(){
			
			Response resp=given().
				      parameter("id","2172797").
				      parameter("appid","673c5650a20311041c26d61291b186ae").
				      when().
				      get("http://api.openweathermap.org/data/2.5/weather");
			
		    String actualWeatherReport=resp.
		    					then().
		    					contentType(ContentType.JSON).
		    					extract().
		    					path("weather[0].description");
		    
		    String expectedWeatherReport=null;
		    
		    if(actualWeatherReport.equalsIgnoreCase(expectedWeatherReport)){
		    	System.out.println("Test Case Passes");
		    }else{
		    	System.out.println("Test Case Failed");
		    }
			
		}
	@Test
		public void Test_09(){
			
			Response resp=given().
					param("id","2172797").
					param("appid","673c5650a20311041c26d61291b186ae").
					when().
					get("http://api.openweathermap.org/data/2.5/weather");
			
			String reportbyID=resp.
							  then().
							  contentType(ContentType.JSON).
							  extract().
							  path("weather[0].description");
			
			System.out.println("weather description by ID:"+reportbyID);
			
			String lon=String.valueOf(resp.
					  then().
					  contentType(ContentType.JSON).
					  extract().
					  path("coord.lon"));
			
			System.out.println("longitude is"+lon);
			
			String lat=String.valueOf(resp.
					  then().
					  contentType(ContentType.JSON).
					  extract().
					  path("coord.lat"));
			
			System.out.println("latidue is"+lat);
			
			String reportbyCoordinates=given().
									  parameter("lat",lat).
									  parameter("lon",lon).
									  parameter("appid","673c5650a20311041c26d61291b186ae").
									  when().
									  get("http://api.openweathermap.org/data/2.5/weather").
									  then().
									  contentType(ContentType.JSON).
									  extract().
									  path("weather[0].description");
			
			System.out.println("Report by coordinates:"+reportbyCoordinates);
			
			Assert.assertEquals(reportbyID, reportbyCoordinates);
		}
		
	}



