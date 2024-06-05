package org.example.elements;

import com.sun.jdi.request.DuplicateRequestException;
import org.example.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// This is a Java class representing a login page. It contains methods to interact with the login elements such as entering username and password, and clicking the login button.

public class LoginPage {
    WebDriver driver;

    /*@FindBy(how = How.ID, using = "UserName")
    private WebElement loginFieldElement;*/

    @FindBy(id = "UserName")
    private WebElement loginFieldElement;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordFieldElement;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), \"Submit\")]")
    private WebElement loginButtonElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1000));
            wait.withTimeout(Duration.ofSeconds(1000));
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
        }
    }


// This is a Java code for logging in with a username and password by entering them in the respective fields and clicking the login button.

    public void login(String username, String pass) {
        loginFieldElement.sendKeys(username);
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "User name has been entered.");
        passwordFieldElement.sendKeys(pass);
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Password has been entered.");
        loginButtonElement.click();
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Login button has been clicked.");
    }

    public void enterUsername(String username) {
        loginFieldElement.sendKeys(username);
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "User name has been entered.");
    }

    public void enterPassword(String pass) {
        passwordFieldElement.sendKeys(pass);
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + "Password has been entered.");
    }
}

