package org.example.test.cases.Login;

import org.example.elements.LoginPage;
import org.example.utility.BaseClass;
import org.example.utility.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

public class LoginTestCase extends BaseClass {
    Properties properties;
    WebDriver driver;
    LoginPage loginPage;


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
    }


    // This is a test case for logging into a web application and verifying the title of the home page after login.

    @Test(priority = 0, groups = {"Smoke","Regression"})
    public void loginTestCase() {
        loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        String titleAfterLogin = "Home Page - FiscalSystems_RETLWeb";
        Assert.assertEquals(driver.getTitle(), titleAfterLogin);
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Login has been done!");
    }

    // This is a Java code snippet from the file StaffTestCase.java.
    // It contains a method to close the browser after the test execution.

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        try {
            if (driver != null) {
                driver.quit();
            } else
                BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Driver is already closed!");
        } catch (Exception e) {
            BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Exception: " + e.getLocalizedMessage());
        }
    }
}
