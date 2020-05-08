package com.vytrack.pages;

import com.vytrack.pages.pageBase.PageBase;
import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage extends PageBase {

    BrowserUtils bu = new BrowserUtils();

    @FindBy(xpath = "//*[. = \"Testers Meeting\"]/..//td[9]//a[@class = \"dropdown-toggle\"]")
    private WebElement detailsTesterMeetings;

    @FindBy(xpath = "//ul[@class = \"dropdown-menu dropdown-menu__action-cell launchers-dropdown-menu detach dropdown-menu__floating\"]")
    private WebElement detailsOptionsTestersMeeting;

    @FindBy(xpath = "//div[@class='column-manager dropdown']")
    private WebElement gridSettings;

    @FindBy(xpath = "//td[@class='visibility-cell']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//span[@class='grid-header-cell__label']")
    private WebElement title;

    @FindBy(xpath = "(//h1)[2]")
    private WebElement allCalendarEvents;


    public boolean allCalendarEventsTextIsDisplayed(){
        return allCalendarEvents.isDisplayed();
    }


    public boolean titleIsDisplayed() {
        return title.isDisplayed();
    }


    public void clickCheckboxOptions() {
        for (int i = 1; i <= checkboxes.size() - 1; i++) {
            bu.waitInSeconds(1);
            checkboxes.get(i).click();
        }
    }


    public void clickOnGridSettings() {
        gridSettings.click();
    }

    public void hoverToDetailsTesterMeetings() {
        bu.moveMouseTo(detailsTesterMeetings);
    }

    public boolean detailsOptionsTestersMeetingVisible() {
        return detailsOptionsTestersMeeting.isDisplayed();
    }
}
