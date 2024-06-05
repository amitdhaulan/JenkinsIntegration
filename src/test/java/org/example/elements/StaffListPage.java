package org.example.elements;

import org.example.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StaffListPage {
    WebDriver driver;

    @FindBy(xpath = "//li[@class=\"nav-links staff-link\"]")
    private WebElement staffMenu;
    @FindBy(xpath = "//a[contains(text(), \"Staff List\")]")
    private WebElement staffListSubMenu;
    @FindBy(id = "FilterName")
    private WebElement nameFilter;
    @FindBy(id = "filterstore")
    private WebElement storeFilter;
    @FindBy(id = "filterphone")
    private WebElement phoneFilter;
    @FindBy(id = "filteremail")
    private WebElement emailFilter;
    @FindBy(id = "filterrole")
    private WebElement roleFilter;
    @FindBy(id = "txtstatus")
    private WebElement statusFilter;
    @FindBy(id = "filterstate")
    private WebElement stateFilter;
    @FindBy(id = "filtercity")
    private WebElement cityFilter;
    @FindBy(id = "userfilter")
    private WebElement applyFilterButton;
    @FindBy(id = "clearuserfilter")
    private WebElement clearFilterButton;

    @FindBy(id = "dt-search-0")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains(text(), \"Add Staff\") and @class = \"add-staff-btn\"]")
    private WebElement addStaffButton;

    //    Actions ------------------------------

    @FindBy(xpath = "//a[@href = \"AddUser/152\"]")
    private List<WebElement> viewIcon;

    @FindBy(xpath = "//a[@href = \"UpdateUser/152\"]")
    private List<WebElement> editIcon;

    @FindBy(id = "divdeluser")
    private List<WebElement> deleteStaff;


    @FindBy(xpath = "(//I[@class='fa-solid fa-trash-can delete removeFromIcon'])[2]")
    private WebElement deleteIcon;

    @FindBy(xpath = "//button[contains(text(), \"Cancel\")]")
    private WebElement inactivateStaffCancel;

    @FindBy(xpath = "//button[contains(text(), \"Ok\")]")
    private WebElement inactivateStaff;


    //___________________________________________

    public StaffListPage(WebDriver driver) {
        this.driver = driver;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
            wait.withTimeout(Duration.ofSeconds(1000));
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
        }
    }

    public void clickStaffMenu() {
        staffMenu.click();
    }

    public void clickStaffList() {
        BaseClass.waitForVisibilityOfElement(driver, staffListSubMenu, Duration.ofSeconds(2000));
        staffListSubMenu.click();
    }

    public void enterNameFiler(String enterName) {
        nameFilter.sendKeys(enterName);
    }

    public void selectStore(String storeName) {
        Select select = new Select(storeFilter);
        select.selectByVisibleText(storeName);
    }

    public void enterPhoneFiler(String enterPhone) {
        phoneFilter.sendKeys(enterPhone);
    }

    public void enterEmailFiler(String enterEmail) {
        emailFilter.sendKeys(enterEmail);
    }

    public void enterRoleFiler(String enterRole) {
        Select select = new Select(roleFilter);
        select.selectByVisibleText(enterRole);
    }

    public void enterStatusFiler(String enterStatus) {
        Select select = new Select(statusFilter);
        select.selectByVisibleText(enterStatus);
    }

    public void enterStateFiler(String enterState) {
        Select select = new Select(stateFilter);
        select.selectByVisibleText(enterState);
    }

    public void enterCityFiler(String enterCity) {
        Select select = new Select(cityFilter);
        select.selectByVisibleText(enterCity);
    }

    public void applyFilter() {
        applyFilterButton.click();
    }

    public void clearFilter() {
        clearFilterButton.click();
    }

    public void clickViewIcon() {
//        BaseClass.waitForVisibilityOfElement(driver, deleteIcon, Duration.ofSeconds(1000));
        for (int i = 0; i < viewIcon.size(); i++) {
            BaseClass.jsClick(viewIcon.get(i));
        }
    }

    public void clickEditIcon() {
//        BaseClass.waitForVisibilityOfElement(driver, deleteIcon, Duration.ofSeconds(1000));
        for (int i = 0; i < editIcon.size(); i++) {
            BaseClass.jsClick(editIcon.get(i));
        }
    }

    public void clickDeleteIcon() {
        BaseClass.waitForVisibilityOfElement(driver, deleteIcon, Duration.ofSeconds(1000));
        BaseClass.jsClick(deleteIcon);
    }

    public void deleteCancel() {
        inactivateStaffCancel.click();
    }

    public void deleteConfirm() {
        inactivateStaff.click();
    }




    public void clickAddStaffButton() {
        addStaffButton.click();
    }

}
