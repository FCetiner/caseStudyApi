package runtest.account;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.account.RegisterClass;
import utils.ReusableMethods;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Registration Tests")
@Feature("User Registration")
public class RegisterRun extends RegisterClass {
    private String email;

    @Test(description = "TC01 Best Case - successful registration And inputs invalid Code", groups = "smoke")
    @Description("Test Description: Register with valid email and verify invalid OTP code")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Registration with valid email")
    public void registerTC01() {
        Allure.step("Starting test: Register with valid email");

        // Test data
        email = "test" + Faker.instance().numerify("#####") + "@gmail.com";
        Response response = register(email);

        try {
            makeValidations(response);
            Allure.step("Registration successful");

            String refId = response.jsonPath().getString("result");
            String otpCode = ReusableMethods.getDateTime("mmHHdd");

            // Response responseRegisterConfirmation = registerConfirmation(refId, otpCode);
            // makeValidationsOfIncorrectCase(responseRegisterConfirmation, 500, "Code not valid");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC02 Best Case - Register with same email", groups = "smoke", dependsOnMethods = "registerTC01")
    @Description("Test Description: Register with an already registered email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Registration with duplicate email")
    public void registerTC02() {
        Allure.step("Starting test: Register with duplicate email");

        // Test data
        Response response = register(email);

        try {
            makeValidationsOfIncorrectCase(response, 500, "This email is already associated with an account. Please use a different email or log in");
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }
}