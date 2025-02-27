package tests.account;

import io.restassured.response.Response;
import testdata.account.LoginTestData;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static utils.RestUtils.getPostRequestResponse;

public class LoginClass extends LoginTestData {

    public static Response login(String email, String password) {
        //Test data
        Map<String,Object> body= new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        //request response
        Response response = getPostRequestResponse(body,"account","Login");
        response.prettyPrint();
        return response;
    }
}
