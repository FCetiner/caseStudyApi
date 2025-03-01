package runtest.account;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.account.LoginClass;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Login Tests")
@Feature("Login Functionality")
public class LoginRun extends LoginClass {

    @Test(description = "TC01 - Incorrect email", groups = "smoke")
    @Description("Test Description: Login with incorrect email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with incorrect credentials")
    public void loginTC01() {
        Allure.step("Starting test: Incorrect email");
        String email = "testMail22@gmail.com";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Incorrect email or password. Please check your information and try again");
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC02 - Incorrect password", groups = "smoke")
    @Description("Test Description: Login with incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with incorrect credentials")
    public void loginTC02() {
        Allure.step("Starting test: Incorrect password");

        String email = "test22@gmail.com";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Password verification failed");
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC03 - Empty email", groups = "smoke")
    @Description("Test Description: Login with empty email")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login with invalid inputs")
    public void loginTC03() {
        Allure.step("Starting test: Empty email");

        String email = "";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Incorrect email or password. Please check your information and try again");
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC04 - Empty password", groups = "smoke")
    @Description("Test Description: Login with empty password")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login with invalid inputs")
    public void loginTC04() {
        Allure.step("Starting test: Empty password");

        String email = "test@gmail.com";
        String password = "";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Password verification failed");
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC05 - Positive login", groups = "smoke")
    @Description("Test Description: Login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Login with valid credentials")
    public void loginTC05() {
        Allure.step("Starting test: Positive login");

        String email = "fcetinerr@gmail.com";
        String password = "Test123++";
        Response response = login(email, password);

        try {
            makeValidations(response);
            Allure.step("Test passed successfully");
        } catch (AssertionError e) {
            Allure.step("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Attachment(value = "Ekran Görüntüsü", type = "image/png")
    public byte[] attachScreenshot() {
        return new byte[0];
    }
}