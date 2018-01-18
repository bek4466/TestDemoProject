package APITesting.com.org.api;


import static com.jayway.restassured.RestAssured.given;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.api.classes.Info;
import APITesting.com.org.api.classes.Posts;
import APITesting.com.org.classes.advancedExamples._Info;
import APITesting.com.org.classes.advancedExamples._Posts;

public class JsonServerRequest {

	// GET
	//@Test
	public void test_01() {
		Response resp = given().when().get("http://localhost:3000/posts");

		System.out.println("Get Response is:" + resp.asString());
	}
	
	//DELETE
		//@Test
		public void test_07(){
			
			Response resp=given().
			when().
			delete("http://localhost:3000/posts/4");
			
			System.out.println("Patch request"+resp.asString());
		}

		//POST
		//@Test
		public void test_02(){
			Response resp=given().
			body("  { \"id\":\"3\",\"title\":\"HELLOWORLD!\",\"author\":\"OYBEK\" } ").
			when().
			contentType(ContentType.JSON).
			post("http://localhost:3000/posts");
			
			System.out.println(resp.asString());
		}
		//@Test
		public void test_03(){
			
			Posts posts=new Posts();
			posts.setId("4");
			posts.setTitle("posts request by object");
			posts.setAuthor("canlilar");
			
			Response resp=given().
					body(posts).
					when().
					contentType(ContentType.JSON).
					post("http://localhost:3000/posts");
					
					System.out.println(resp.asString());
		}

		//PATCH
		//@Test
		public void test_06(){
			
			Response resp=given().
			body("  { \"title\":\"updated by put request\" } ").
			when().
			contentType(ContentType.JSON).
			patch("http://localhost:3000/posts/4");
			
			System.out.println("Patch request"+resp.asString());
			
		}
		//Complex POST
		//@Test
		public void test_08(){
			
			Info info=new Info();
			info.setEmail("test@test.com");
			info.setPhone("111-111-1111");
			info.setAddress("washington DC");
			
			Posts posts=new Posts();
			posts.setAuthor("author");
			posts.setId("10");
			posts.setTitle("title");
			posts.setInfo(info);
			
			Response resp=given().
					body(posts).
					when().
					contentType(ContentType.JSON).
					post("http://localhost:3000/posts");
					
					System.out.println(resp.asString());
					
					
		}
		//POSTS W ARRAY
		//@Test
		public void test_09(){
			
			_Info info1=new _Info();
			info1.setEmail("test1@test.com");
			info1.setPhone("999-99-9999");
			info1.setAddress("arlington,va");
			
			_Info info2=new _Info();
			info2.setEmail("test2@test.com");
			info2.setPhone("555-55-5555");
			info2.setAddress("alexandria,va");
			
			_Posts posts=new _Posts();
			posts.setId("100");
			posts.setTitle("title");
			posts.setAuthor("author");
			posts.setInfo(new _Info[]{info1,info2});
			
			Response resp=given().
					body(posts).
					when().
					contentType(ContentType.JSON).
					post("http://localhost:3000/posts");
					
					System.out.println(resp.asString());
			
		}

		//calculate response time
		//@Test
		public void test_10(){
			Response resp=given().
			when().
			get("http://localhost:3000/posts");
			
			Long time=resp.then().extract().time();
			
			System.out.println("Response time is:"+time);
			
			given().when().get("http://localhost:3000/posts").then().and().time(lessThan(10L));
			given().when().get("http://localhost:3000/posts").then().and().time(lessThan(800L));
			
		}

		private Matcher<Long> lessThan(long l) {
			// TODO Auto-generated method stub
			return null;
		}

	}


