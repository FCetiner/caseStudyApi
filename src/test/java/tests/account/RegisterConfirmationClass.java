package tests.account;

import io.restassured.response.Response;
import testdata.account.RegisterTestData;
import utils.ReusableMethods;

import java.util.Map;

import static utils.RestUtils.*;

public class RegisterConfirmationClass extends RegisterTestData {
    public static Response registerConfirmation(String refId,String code) {
        //request response
        Response response = getGetRequestWithQueryResponseNoToken("account","RegisterConfirmation","refId",refId,"code",code);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        response.prettyPrint();
        return response;
    }
}
