package runtest.account;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.account.RegisterClass;
import utils.ReusableMethods;

public class RegisterRun extends RegisterClass {
    private String email;

    @Test(description = "TC01 Best Case - succesful registration And inputs invalid Code",groups = "smoke")
    public  void registerTC01 (){
        //Test data
        email= "test"+Faker.instance().numerify("#####")+"@gmail.com";
        Response response=register(email);
        makeValidations(response);

        //
        String refId=response.jsonPath().getString("result");
        String otpCode=ReusableMethods.getDateTime("mmHHdd");
     //   Response responseRegisterConfirmation=registerConfirmation(refId,otpCode);
     //   makeValidationsOfIncorrectCase(responseRegisterConfirmation,500,"Code not valid");
    }


    @Test(description = "TC02 Best Case - Register with same email",groups = "smoke",dependsOnMethods = "registerTC01")
    public  void registerTC02 (){
        //Test data
        Response response=register(email);
        makeValidationsOfIncorrectCase(response,500,"This email is already associated with an account. Please use a different email or log in");
    }


}
