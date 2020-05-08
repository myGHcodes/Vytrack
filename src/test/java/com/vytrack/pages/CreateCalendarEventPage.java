package com.vytrack.pages;

import com.vytrack.pages.pageBase.PageBase;
import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CreateCalendarEventPage extends PageBase {

    BrowserUtils bu = new BrowserUtils();

    @FindBy(xpath = "(//div[@class='btn-group'])[1]")
    private WebElement calendarEventButton;

    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle']")
    private WebElement dropdown;

    @FindBy(xpath = "(//ul[@class='dropdown-menu'])[3]//li//button[@type='submit']")
    private List<WebElement> savingOptions;


    @FindBy(xpath = "//a[@title='Cancel']")
    private WebElement cancelButton;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(xpath = "//div[@class='ui-timepicker-wrapper']//ul//li")
    private List<WebElement> listOfStartTimes;

    @FindBy(xpath = "//div[@class='ui-timepicker-wrapper ui-timepicker-positioned-top']//ul//li")
    private List<WebElement> listOfEndTimes;

    @FindBy(xpath = "//input[@name='oro_calendar_event_form[allDay]']")
    private WebElement allDayEventCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    private WebElement repeatCheckbox;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    private WebElement endDate;

    @FindBy(xpath = "//select[@class='recurrence-repeats__select']")
    private WebElement repeats;

    /*@FindBy(xpath = "//select[@id='recurrence-repeats-view1317']")
    private WebElement listOfRepeats;*/

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    private WebElement repeatEvery;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    private WebElement neverRadioButton;

    @FindBy(xpath = "//div[@class='control-label wrap']//label[text() ='Ends']")
    private WebElement endsElement;

    @FindBy(xpath = "//div[@class='control-group recurrence-summary alert-info']//div//label")
    private WebElement summaryElement;

    @FindBy(xpath = "//div[@class='control-group recurrence-summary alert-info']//div//span")
    private WebElement dailyEvery1DayElement;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    private WebElement afterRadioButton;

    @FindBy(xpath = "(//input[@class='recurrence-subview-control__number'])[7]")
    private WebElement afterInput;

    @FindBy(xpath = "//div[@class='control-group recurrence-summary alert-info']//div//span[2]")
    private WebElement endAfter10Occurrences;

    @FindBy(xpath = "(//input[@type='radio'])[5]")
    private WebElement byRadioButton;

    @FindBy(id = "dp1586895038184")
    private WebElement chooseDate;

    @FindBy(className = "recurrence-repeats__select")
    public WebElement repeatsWeekly;

    @FindBy(xpath = "//input[@value='monday']")
    public WebElement monday;

    @FindBy(xpath = "//input[@value='friday']")
    public WebElement friday;

    @FindBy(xpath = "//div[@data-name='recurrence-summary']//div//span")
    public WebElement summaryTextElement;


    public String summaryText3() {
        String text = summaryElement.getText() + " " + summaryTextElement.getText();
        return text;
    }



    public void clickOnMondayAndFriday(){
        monday.click();
        friday.click();
    }

    public void selectWeekly() {
        bu.selectFromDropdownByText(repeatsWeekly, "Weekly");

    }

    public void byClickDateMethod() {
        byRadioButton.click();

    }

    public String summaryText2() {
        String text = summaryElement.getText() + " " + dailyEvery1DayElement.getText() + endAfter10Occurrences.getText();
        return text;
    }

    public void clickAfterRadioButtonAndAddTen() {
        afterRadioButton.click();
        afterInput.sendKeys("10");
        afterInput.click();
    }


    public String summaryText() {
        String text = summaryElement.getText() + " " + dailyEvery1DayElement.getText();
        return text;
    }

    public boolean checkingNeverRadioButton() {
        if (endsElement.getText().equals("Ends")) {
            neverRadioButtonIsSelected();
            return true;
        } else {
            return false;
        }
    }

    public boolean neverRadioButtonIsSelected() {
        return neverRadioButton.isSelected();
    }


    public boolean repeatEveryIsSelected() {
        return repeatEvery.isSelected();
    }


    public String getFirstSelectedFromRepeats() {
        Select select = new Select(repeats);
        bu.waitInSeconds(2);
        return select.getFirstSelectedOption().getText();
    }

    public String getAllOptionsFromRepeats() {
        List<String> list = new ArrayList<>();
        Select select = new Select(repeats);
        List<WebElement> options = select.getOptions();
        for (WebElement each : options) {
            list.add(each.getText());
        }
        return list.toString();
    }


    public void clickRepeatCheckbox() {
        repeatCheckbox.click();
    }

    public boolean repeatCheckboxIsSelected() {
        return repeatCheckbox.isSelected();
    }


    public boolean startEndDateIsDisplayed() {
        if (startDate.isDisplayed() && endDate.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startEndTimeIsDisplayed() {
        if (startTime.isDisplayed() && endTime.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void clickAllDayEventCheckbox() {
        allDayEventCheckbox.click();
    }

    public String getTextFromEndTime() {
        return endTime.getAttribute("value");
    }

    public void clickOnCertainEndTime(String eTime) {
        for (WebElement each : listOfEndTimes) {
            if (each.getText().equalsIgnoreCase(eTime)) {
                each.click();
            }
        }
    }


    public void clickOnCertainStartTime(String sTime) {
        for (WebElement each : listOfStartTimes) {
            if (each.getText().equalsIgnoreCase(sTime)) {
                each.click();
            }
        }
    }


    public void clickStartTime() {
        startTime.click();
    }

    public String getStartTime() {
        BrowserUtils.waitInSeconds(2);
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitInSeconds(2);
        return endTime.getAttribute("value");
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }


    public List<String> getSavingOptions() {
        List<String> tempList = new ArrayList<>();
        for (WebElement each : savingOptions) {
            bu.waitInSeconds(1);
            tempList.add(each.getText().trim());
        }
        return tempList;
    }

    public void clickOnDropdown() {
        dropdown.click();
    }

    public void clickOnCreateCalendarEventButton() {
        bu.waitInSeconds(3);
        calendarEventButton.click();
        bu.waitInSeconds(2);
    }
}
