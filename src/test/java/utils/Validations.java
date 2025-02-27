package utils;

import basesetup.BaseSetup;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public abstract class Validations extends BaseSetup {
    protected   void makeValidations(Response response){
        response.then().statusCode(200);
        Assert.assertTrue(response.jsonPath().getBoolean("succeeded"));
    }

    protected  void makeValidationsOfIncorrectCase(Response response, int statusCode, String errors) {
        response.then().statusCode(statusCode);
        Assert.assertFalse(response.jsonPath().getBoolean("Succeeded"),"sucseeded is false!!!");
        Assert.assertNull(response.jsonPath().getString("Result"), "Result is different");
        Assert.assertEquals(response.jsonPath().getString("Errors[0]"),errors,"Errors are different");

    }
}
