package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.CreateCalendarEventPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.BrowserUtils;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Tasks extends TestBase {

    LoginPage loginPage;
    CalendarEventsPage calendarEventsPage;
    CreateCalendarEventPage createCalendarEventPage;
    BrowserUtils bu;

    @Test
    public void testCase1() {
        extentLogger = report.createTest("Login and verify <View> <Edit> <Delete> options test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        calendarEventsPage.hoverToDetailsTesterMeetings();

        /*Assert.assertEquals(Driver.getDriver().findElement
                (By.xpath("//ul[@class = \"dropdown-menu dropdown-menu__action-cell launchers-dropdown-menu detach dropdown-menu__floating\"]")).isDisplayed(), true);*/
        assertEquals(calendarEventsPage.detailsOptionsTestersMeetingVisible(), true);

        extentLogger.pass("Login and verify <View> <Edit> <Delete> options test passed.");
    }


    @Test
    public void testCase2() {
        extentLogger = report.createTest("Grid options test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        calendarEventsPage.clickOnGridSettings();
        calendarEventsPage.clickCheckboxOptions();
        assertEquals(calendarEventsPage.titleIsDisplayed(), true);

        extentLogger.pass("Grid options test passed.");
    }

    @Test
    public void testCase3() {
        extentLogger = report.createTest("Verify <Save and Close>, <Save and New>, <Save> options test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnDropdown();
        List<String> expectedList = Arrays.asList("Save And Close", "Save And New", "Save");
        assertEquals(createCalendarEventPage.getSavingOptions().toString(), expectedList.toString());

        extentLogger.pass("Verify <Save and Close>, <Save and New>, <Save> options test passed.");
    }

    @Test
    public void testCase4() {
        extentLogger = report.createTest("Verify that “All Calendar Events” page subtitle is displayed test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCancelButton();
        bu.waitInSeconds(3);

        assertEquals(calendarEventsPage.allCalendarEventsTextIsDisplayed(), true);

        extentLogger.pass("Verify that “All Calendar Events” page subtitle is displayed test passed.");
    }


    @Test
    public void testCase5() throws ParseException {
        extentLogger = report.createTest("Verify that difference between end and start time is exactly 1 hour test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(3);

        assertEquals(BrowserUtils.startAndEndTime(createCalendarEventPage.getStartTime(), createCalendarEventPage.getEndTime()), 1);

        extentLogger.pass("Verify that difference between end and start time is exactly 1 hour test passed.");
    }


    @Test
    public void testCase6() throws ParseException {
        extentLogger = report.createTest("Verify that end time is equals to “10:00 PM” test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(3);
        createCalendarEventPage.clickStartTime();
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCertainStartTime("9:00 PM");
        assertEquals(createCalendarEventPage.getTextFromEndTime(), "10:00 PM");

        extentLogger.pass("Verify that end time is equals to “10:00 PM” test passed.");
    }


    @Test
    public void testCase7() {
        extentLogger = report.createTest("Verify that start and end time input boxes are not displayed test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(3);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(3);
        createCalendarEventPage.clickAllDayEventCheckbox();
        bu.waitInSeconds(3);
        assertEquals(createCalendarEventPage.startEndTimeIsDisplayed(), false);
        assertEquals(createCalendarEventPage.startEndDateIsDisplayed(), true);
        extentLogger.pass("Verify that start and end time input boxes are not displayed test passed.");
    }

    @Test
    public void testCase8() {
        extentLogger = report.createTest("Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down: test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(4);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(4);
        createCalendarEventPage.clickRepeatCheckbox();
        assertEquals(createCalendarEventPage.repeatCheckboxIsSelected(), true);
        assertEquals(createCalendarEventPage.getFirstSelectedFromRepeats(), "Daily");
        createCalendarEventPage.getAllOptionsFromRepeats();
        List<String> expectedList = Arrays.asList("Daily", "Weekly", "Monthly", "Yearly");
        assertEquals(createCalendarEventPage.getAllOptionsFromRepeats(), expectedList.toString());

        extentLogger.pass("Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down: test passed.");
    }

    @Test
    public void testCase9() {
        extentLogger = report.createTest("Verify that following message as a summary is displayed: “Summary: Daily every 1 day” test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(4);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(4);
        createCalendarEventPage.clickRepeatCheckbox();
        assertTrue(createCalendarEventPage.repeatCheckboxIsSelected());
        assertTrue(createCalendarEventPage.repeatEveryIsSelected());
        assertTrue(createCalendarEventPage.checkingNeverRadioButton());
        assertEquals(createCalendarEventPage.summaryText(), "Summary: " + "Daily every 1 day");

        extentLogger.pass("Verify that following message as a summary is displayed: “Summary: Daily every 1 day” test passed.");
    }


    @Test
    public void testCase10() {
        extentLogger = report.createTest("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(4);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(4);
        createCalendarEventPage.clickRepeatCheckbox();
        createCalendarEventPage.clickAfterRadioButtonAndAddTen();
        assertEquals(createCalendarEventPage.summaryText2(), "Summary: Daily every 1 day, end after 10 occurrences");

        extentLogger.pass("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences test passed.");
    }


    @Test
    public void testCase11() {
        extentLogger = report.createTest("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(4);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(4);
        createCalendarEventPage.clickRepeatCheckbox();
        createCalendarEventPage.clickAfterRadioButtonAndAddTen();
        assertEquals(createCalendarEventPage.summaryText2(), "Summary: Daily every 1 day, end after 10 occurrences");

        extentLogger.pass("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences test passed.");
    }

    @Test
    public void testCase12(){
        extentLogger = report.createTest("Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday test");

        loginPage = new LoginPage();
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventPage = new CreateCalendarEventPage();
        bu = new BrowserUtils();

        loginPage.loginAsStoreManager85();
        loginPage.navigateTo("Activities", "Calendar Events");
        bu.waitInSeconds(4);
        createCalendarEventPage.clickOnCreateCalendarEventButton();
        bu.waitInSeconds(4);
        createCalendarEventPage.clickRepeatCheckbox();
        createCalendarEventPage.selectWeekly();
        createCalendarEventPage.clickOnMondayAndFriday();
        assertTrue(createCalendarEventPage.summaryTextElement.isDisplayed());
        assertEquals(createCalendarEventPage.summaryText3(), "Summary: Weekly every 1 week on Monday, Friday");

        bu.waitInSeconds(9);
        extentLogger.pass("Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday test passed.");

    }




}
