package org.example.test.cases.Staff;

import org.example.elements.LoginPage;
import org.example.elements.StaffListPage;
import org.example.utility.BaseClass;
import org.example.utility.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

public class StaffTestCase extends BaseClass {
    Properties properties;
    WebDriver driver;
    LoginPage loginPage;
    StaffListPage staffListPage;


    // This Java code is a setup method for a test case related to logging in.
// It reads properties from a config file, sets up Edge browser with incognito mode, maximizes the window, and navigates to a specified URL.

    @BeforeTest
    public void setup() throws IOException {
        properties = readPropertiesFile("./utilities/config/config.properties");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless");
        driver = getDriver(DriverType.EDGE, options);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("URL"));
        loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        staffListPage = new StaffListPage(driver);
    }

    @Test(priority = 0, groups = {"Smoke","Regression"}, description = "This is a test case for viewing staff list")
    public void staffViewTestCase() {
        staffListPage.clickStaffMenu();
        staffListPage.clickStaffList();
        staffListPage.clickViewIcon();

    }

    @Test(priority = 1, groups = {"Regression"}, description = "This is a test case for editing staff list")
    public void staffEditTestCase() {
        navigateBack();
        staffListPage.clickStaffMenu();
        staffListPage.clickStaffList();
        staffListPage.clickEditIcon();
    }

    @Test(priority = 2, groups = {"Regression"}, description = "This is a test case for deleting staff list")
    public void staffDeleteTestCase() {
        navigateBack();
        staffListPage.clickStaffMenu();
        staffListPage.clickStaffList();
        staffListPage.clickDeleteIcon();
        staffListPage.deleteCancel();
    }

    // This is a Java code snippet from the file StaffTestCase.java.
// It contains a method to close the browser after the test execution.

    @AfterTest
    public void closeBrowser()  {
        driver.quit();
    }
}
