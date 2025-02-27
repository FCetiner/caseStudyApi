package tests.account;

import io.restassured.response.Response;
import testdata.account.RegisterTestData;
import utils.ReusableMethods;

import java.util.HashMap;
import java.util.Map;

import static utils.RestUtils.getPostRequestResponse;

public class RegisterClass extends RegisterConfirmationClass {
    public static Response register(String email) {
        //Test data
        Map<String,Object> body= ReusableMethods.readJsonTextFile("src/test/resources/account/register.json");
        body.put("email", email);

        //request response
        Response response = getPostRequestResponse(body,"account","Register");
        response.prettyPrint();
        return response;
    }
}
