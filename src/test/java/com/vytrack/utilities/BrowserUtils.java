package com.vytrack.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtils {


    private WebDriver driver = Driver.getDriver();
    WebDriverWait wait = TestBase.wait;
    Actions actions = TestBase.actions;


    //select
    public void selectFromDropdownByText(WebElement dropdownElement, String text) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }


    // JavaScript scroll
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    //actions
    public void moveMouseTo(WebElement element) {
        actions.moveToElement(element).perform();
    }

    // wait
    public static void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // this method basically takes a name of a test and returns a path to screenshot
    public static String getScreenshot(String name) {
        // name the screenshot with current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMhhmmss").format(new Date());
        // TakesScreenshot --> interface from selenium which takes screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

        File finalDestination = new File(target);

        // save the screenshot to the path given
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    // time difference method
    public static long startAndEndTime(String start, String end) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        Date time1 = simpleDateFormat.parse(start);
        Date time2 = simpleDateFormat.parse(end);
        long difference = time2.getTime() - time1.getTime();
        long differenceInHours = difference / (60 * 60 * 1000);
        return differenceInHours;
    }


}
