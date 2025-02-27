package utils;

import basesetup.BaseSetup;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RestUtils extends BaseSetup {

    public static String getReferenceNo(){
        String generatedString = RandomStringUtils.randomNumeric(10);
        return (generatedString);
    }
    public static Reader readFile(String fileName) {

            try {
                Reader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/" +fileName);
                return reader;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    public static Response getPostRequestResponse(String token, String pp1, String pp2, String pp3){
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .spec(spec)
                .header("X-Forwarded-For","127.0.0.1")
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getPostRequestResponse(Map<String,Object> body, String pp1, String pp2, String pp3){
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(body)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getPostRequestResponse(Map<String,Object> body, String pp1, String pp2){
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2)
                .body(body)
                .when()
                .post("/{pp1}/{pp2}");
        return response;
    }





    public static Response getPostRequestResponse(String token,Map<String,Object>testData, String pp1, String pp2, String pp3){
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(testData)
                .header("X-Forwarded-For","127.0.0.1")
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getPostRequestResponseIPAdress(String token,Map<String,Object>testData, String pp1, String pp2, String pp3){
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }

        public static Response getPostRequestResponseIPAdress(String token,Map<String,Object>testData, String pp1, String pp2, String pp3,String ipAdress){
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For",ipAdress)
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }



    public static Response getPostRequestResponse(String token,Map<String,Object>testData, String pp1, String pp2, String pp3, String pp4){

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .spec(spec)
                .header("X-Forwarded-For","127.0.0.1")
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3,"pp4", pp4)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }

    public static Response getPostRequestResponse(String token,Map<String,Object>testData, String pp1, String pp2, String pp3, String pp4, String pp5){

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3,"pp4", pp4,"pp5", pp5)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}/{pp4}/{pp5}");
        return response;
    }
    public static Response getPostRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String query1, Object qp1, String query2, Object qp2){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query1,qp1,query2,qp2)
                .contentType(JSON)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getPostRequestWithQueryResponseBasicAuth(String username,String password, String pp1, String pp2, String pp3,Map<String,Object> testData){
        Response response = given()
                .log().all()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType(JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getPostRequestWithQueryResponseBasicAuth(String username,String password, String pp1, String pp2, String pp3,String pp4,Map<String,Object> testData){
        Response response = given()
                .log().all()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType(JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3,"pp4",pp4)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }
    public static Response getPostRequestWithQueryResponseBasicAuth(String username,String password, String pp1, String pp2, String pp3,String pp4,String pp5,String pp6,Map<String,Object> testData){
        Response response = given()
                .log().all()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType(JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3,"pp4",pp4,"pp5",pp5,"pp6",pp6)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}/{pp4}/{pp5}/{pp6}");
        return response;
    }

    public static Response getDeleteRequestWithQueryResponseBasicAuth(String username,String password, String pp1, String pp2, String pp3,String pp4,Map<String,Object> testData){
        Response response = given()
                .log().all()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType(JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3,"pp4",pp4)
                .body(testData)
                .when()
                .delete("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }

    public static Response getGetRequestWithQueryResponseBasicAuth(String username,String password, String pp1, String pp2, String pp3,String query1, Object qp1){
        Response response = given()
                .log().all()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType(JSON)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .queryParams(query1,qp1)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getPostRequestResponse(String token, String testData, String pp1, String pp2, String pp3){
        Response response = given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8")))
                .contentType(ContentType.XML)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1, "pp2", pp2,"pp3", pp3)
                .body(testData)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getPostRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String query1, Object qp1){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query1,qp1)
                .contentType(JSON)
                .when()
                .post("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestResponse(String pp1, String pp2){
        Response response=given()
                .log().all()
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}");
        return response;
    }
    public static Response getGetRequestResponse(String pp1, String pp2, String pp3){
        Response response=given()
                .log().all()
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestResponse(String token,String pp1, String pp2, String pp3){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestWithQueryResponse(String pp1, String pp2, String pp3, String query, String qp1){
        Response response=given()
                .log().all()
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithQueryResponseIPAddress(String token,String pp1, String pp2, String pp3, String query, Object qp1){
        Response response=given()
                .log()
                .all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestWithQueryResponseIPAddress(String token,String pp1, String pp2, String pp3, String query1, String qp1, String query2, String qp2){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query1,qp1,query2,qp2)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithQueryResponseIPAddress(String token,String pp1, String pp2, String pp3){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }


    public static Response getGetRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String query1, String qp1, String query2, String qp2){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query1,qp1,query2,qp2)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String query1, Object qp1, String query2, Object qp2){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query1,qp1,query2,qp2)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String query, String qp1){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithQueryResponseNoToken(String pp1, String pp2, String q1, String qp1, String q2, String qp2){
        Response response=given()
                .log().all()
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2)
                .queryParams(q1,qp1,q2,qp2)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}");
        return response;
    }
    public static Response getGetRequestWithQueryResponse(String token, String pp1, String pp2, String pp3, String pp4, String query, String qp1){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .header("X-Forwarded-For","127.0.0.1")
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3,"pp4",pp4)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }
    public static Response getGetRequestWithQueryResponseIPAddress(String token,String pp1, String pp2, String pp3, String query, String qp1,String ipAddress){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For",ipAddress)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithTokenResponse(String token, String pp1, String pp2, String pp3){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestWithTokenResponseIPAddress(String token, String pp1, String pp2, String pp3){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }
    public static Response getGetRequestWithTokenResponseIPAddress(String token, String pp1, String pp2, String pp3, String pp4){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("X-Forwarded-For","127.0.0.1")
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3,"pp4",pp4)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestWithTokenAndQueryResponse(String token, String pp1, String pp2, String pp3,String query, String qp1){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        return response;
    }

    public static Response getGetRequestWithTokenAndQueryResponse(String token, String pp1, String pp2, String pp3, String pp4,String query, String qp1){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3,"pp4",pp4)
                .queryParams(query,qp1)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }

    public static Response getGetRequestWithTokenResponse(String token, String pp1, String pp2, String pp3, String pp4){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3,"pp4",pp4)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }

    public static Response getGetRequestResponse(String token,String pp1, String pp2, String pp3,String pp4){
        Response response=given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .pathParams("pp1", pp1,"pp2",pp2,"pp3",pp3,"pp4",pp4)
                .contentType(JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        return response;
    }

}
