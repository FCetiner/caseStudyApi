package runtest.account;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.account.LoginClass;
import utils.ConfigReader;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.RestUtils.getPostRequestResponse;

public class LoginRun extends LoginClass {


    @Test(description = "TC01 - incorrect email",groups = "smoke")
    public  void loginTC01 (){
        //Test data
        String email= "testMail22@gmail.com";
        String password= "123456";
        Response response=login(email, password);
        makeValidationsOfIncorrectCase(response,500,"Incorrect email or password. Please check your information and try again");
    }
    @Test(description = "TC02 - incorrect password",groups = "smoke")
    public  void loginTC02 (){
        //Test data
        String email= "test22@gmail.com";
        String password= "123456";
        Response response=login(email, password);
        makeValidationsOfIncorrectCase(response,500,"Password verification failed");
    }

    @Test(description = "TC03 Empty email",groups = "smoke")
    public  void loginTC03 (){
        //Test data
        String email= "";
        String password= "123456";
        Response response=login(email, password);
        makeValidationsOfIncorrectCase(response,500,"Incorrect email or password. Please check your information and try again");
    }

    @Test(description = "TC04 Empty password",groups = "smoke")
    public  void loginTC04 (){
        //Test data
        String email= "";
        String password= "123456";
        Response response=login(email, password);
        makeValidationsOfIncorrectCase(response,500,"Incorrect email or password. Please check your information and try again");
    }
    @Test(description = "TC05 Positive login",groups = "smoke")
    public  void loginTC05 (){
        //Test data
        String email= "fcetinerr@gmail.com";
        String password= "Test123++";
        Response response=login(email, password);
        makeValidations(response);
    }



}
