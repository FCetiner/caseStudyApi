package runtest.account;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.account.LoginClass;


public class LoginRun extends LoginClass {

    @BeforeMethod
    public void setUp() {
        extentTest = extentReports.createTest("Login Test - Starting");
    }

    @Test(description = "TC01 - Incorrect email", groups = "smoke")
    public void loginTC01() {
        extentTest.info("Starting test: Incorrect email");

        String email = "testMail22@gmail.com";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Incorrect email or password. Please check your information and try again");
            extentTest.pass("Test passed successfully");
        } catch (AssertionError e) {
            extentTest.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC02 - Incorrect password", groups = "smoke")
    public void loginTC02() {
        extentTest.info("Starting test: Incorrect password");

        String email = "test22@gmail.com";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Password verification failed");
            extentTest.pass("Test passed successfully");
        } catch (AssertionError e) {
            extentTest.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC03 - Empty email", groups = "smoke")
    public void loginTC03() {
        extentTest.info("Starting test: Empty email");

        String email = "";
        String password = "123456";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Incorrect email or password. Please check your information and try again");
            extentTest.pass("Test passed successfully");
        } catch (AssertionError e) {
            extentTest.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC04 - Empty password", groups = "smoke")
    public void loginTC04() {
        extentTest.info("Starting test: Empty password");

        String email = "test@gmail.com";
        String password = "";
        Response response = login(email, password);

        try {
            makeValidationsOfIncorrectCase(response, 500, "Password verification failed");
            extentTest.pass("Test passed successfully");
        } catch (AssertionError e) {
            extentTest.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC05 - Positive login", groups = "smoke")
    public void loginTC05() {
        extentTest.info("Starting test: Positive login");

        String email = "fcetinerr@gmail.com";
        String password = "Test123++";
        Response response = login(email, password);

        try {
            makeValidations(response);
            extentTest.pass("Test passed successfully");
        } catch (AssertionError e) {
            extentTest.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        extentReports.flush();
    }
}
