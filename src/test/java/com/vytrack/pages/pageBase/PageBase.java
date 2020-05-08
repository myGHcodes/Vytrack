package com.vytrack.pages.pageBase;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {

    protected WebDriver driver = Driver.getDriver();

    public PageBase() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    BrowserUtils bu = new BrowserUtils();

    public void navigateTo(String mainTab, String subTab) {
        String mainTabXpath = "//span[@class='title title-level-1' and contains(text(), '" + mainTab + "')]";
        String subTabXpath = "//span[@class='title title-level-2' and contains(text(), '" + subTab + "')]";

        WebElement mainTabElement = driver.findElement(By.xpath(mainTabXpath));
        WebElement subTabElement = driver.findElement(By.xpath(subTabXpath));

        Actions actions = new Actions(driver);
        actions.moveToElement(mainTabElement).pause(3000).click(subTabElement).pause(3000).build().perform();
        bu.waitInSeconds(2);
    }


}
