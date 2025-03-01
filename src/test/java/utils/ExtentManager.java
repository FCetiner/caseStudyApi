package utils;

import basesetup.BaseSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class ExtentManager extends BaseSetup {
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentSparkReporter extentSparkReporter;

    @BeforeSuite(alwaysRun = true)
    public void setUpTest() {
        extentReports = new ExtentReports();

        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Report_" + date + ".html";

        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Automation Engineer", "QA Engineer");

        // Raporun görünümünü ayarla
        extentSparkReporter.config().setDocumentTitle("TestNG Extent Report");
        extentSparkReporter.config().setReportName("Automation Test Results");
        extentSparkReporter.config().setTheme(Theme.DARK);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownTest() {
        extentReports.flush();
    }
}
