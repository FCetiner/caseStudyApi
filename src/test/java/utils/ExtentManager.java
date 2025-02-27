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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ExtentManager extends BaseSetup {
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentSparkReporter extentSparkReporter;

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        extentReports = new ExtentReports();

        // Raporun dosya yolunu belirle
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Report_" + date + ".html";

        // Yeni ExtentSparkReporter kullanılıyor
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);

        // Test ortam bilgilerini ekle
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser")); // Eğer ConfigReader yoksa kaldır
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

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        extentReports.flush();
    }
}
