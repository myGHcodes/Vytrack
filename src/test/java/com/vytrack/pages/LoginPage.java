package com.vytrack.pages;

import com.vytrack.pages.pageBase.PageBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends PageBase {


    @FindBy(id = "prependedInput")
    private WebElement username;

    @FindBy(id = "prependedInput2")
    private WebElement password;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    private WebElement errorMsg;

    public String getInvalidUsernameMsg(){
        return errorMsg.getText();
    }

    public void loginAsStoreManager85(){
        BrowserUtils.waitInSeconds(2);
        username.sendKeys(ConfigurationReader.getProperty("store_manager_username"));
        password.sendKeys(ConfigurationReader.getProperty("store_manager_password"), Keys.ENTER);
        BrowserUtils.waitInSeconds(3);
    }

    public void manualLogin(String name, String password){
        this.username.sendKeys(name);
        this.password.sendKeys(password, Keys.ENTER);
        BrowserUtils.waitInSeconds(3);
    }


}
