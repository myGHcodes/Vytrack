package com.vytrack.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    public static WebDriverWait wait;
    public static Actions actions;
    public static BrowserUtils bu;

    // actual reporter
    protected static ExtentReports report;

    //html report
    protected ExtentHtmlReporter htmlReporter;

    // it is the object which takes notes and it writes to report
    protected ExtentTest extentLogger;

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        report = new ExtentReports();

        //path where report will be stored
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";

        htmlReporter = new ExtentHtmlReporter(filePath);
        report.attachReporter(htmlReporter);

        report.setSystemInfo("ENV", "staging");
        report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));

        htmlReporter.config().setReportName("VyTrack Automated Test Reports");
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        //erasing old report
        report.flush();

        //close browser after all tests are done in the same browser
       // Driver.closeDriver();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        actions = new Actions(Driver.getDriver());

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        String URL = ConfigurationReader.getProperty("qa1");
        Driver.getDriver().get(URL);
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        // checking if test is failed
        if (result.getStatus() == ITestResult.FAILURE) {

            //get screenshot using the utility method and then save the location of the screenshot
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());

            // takes a screenshot the name of test method
            extentLogger.fail(result.getName());

            // add the screenshot to the report
            try {
                extentLogger.addScreenCaptureFromPath(screenshotLocation);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // capture the exception thrown(gets the error message)
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test case skipped is " + result.getName());
        }

        //closes browser after each @Test is done
        Driver.closeDriver();
    }




}
