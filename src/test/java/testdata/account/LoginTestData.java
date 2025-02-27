package testdata.account;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTestData extends DBUtils {
    protected   void makeValidations(Response response){
        response.then().statusCode(200);
        Assert.assertNotNull(response.jsonPath().getString("result.token"));
        Assert.assertNotNull(response.jsonPath().getString("result.user.firstname"),"Ferhat");
        Assert.assertNotNull(response.jsonPath().getString("result.user.lastname"),"Çetiner");
        Assert.assertNotNull(response.jsonPath().getString("result.user.email"),"fcetinerr@gmail.com");
        Assert.assertNotNull(response.jsonPath().getString("result.user.username"),"fcetinerr@gmail.com");
    }


}
