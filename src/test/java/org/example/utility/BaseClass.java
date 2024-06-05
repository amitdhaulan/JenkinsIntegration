package org.example.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;

// This is a Java file named BaseClass that contains a class with various methods for handling WebDriver, logging, reading properties and Excel files, and taking screenshots.

// Fixing performance issues in the code

public class BaseClass {
    public static final String timeStamp = new SimpleDateFormat("MMddyyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
    public static final Logger LOGGER = Logger.getLogger(BaseClass.class);
    SimpleLayout layout = new SimpleLayout();
    FileAppender appender = null;
    static WebDriver driver;
    XSSFWorkbook work_book;
    XSSFSheet sheet;

    // Java code to create a BaseClass with a constructor that initializes a FileAppender for logging

    public BaseClass() {
        try {
            appender = new FileAppender(layout, "utilities/logFile/logs.log", true);
            LOGGER.addAppender(appender);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // Java code to get a WebDriver based on the specified DriverType

    public WebDriver getDriver(DriverType type, EdgeOptions options) {
        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                applyImplicitWait();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                applyImplicitWait();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                applyImplicitWait();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(options);
                applyImplicitWait();
                break;
        }
        return driver;
    }

    public void applyImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    // This Java code defines a method to read properties from a file and load them into a Properties object.
    // It handles exceptions for file not found and IO errors.

    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    // This Java code defines a method to read an Excel file using Apache POI library.

    public void readExcelFile(String excelFilePath) {
        try {
            File file = new File(excelFilePath);
            FileInputStream stream = new FileInputStream(file);
            work_book = new XSSFWorkbook(stream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // This Java code defines a method that retrieves data from a specific cell in a given sheet of a workbook.

    public String getData(int sheetNumber, int row, int column) {
        sheet = work_book.getSheetAt(sheetNumber);
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    // Java code to get the total number of rows in a specific sheet of a workbook

    public int getRowCount(int sheetIndex) {
        int row = work_book.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }

    // This Java code defines a method to take a screenshot using the Selenium WebDriver. It takes the WebDriver object and the file path as input parameters, captures a screenshot, and saves it to the specified file path.

    public static void takeScreenShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, new File(fileWithPath));
    }

    public static void waitForVisibilityOfElement(WebDriver driver, WebElement element, Duration timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void jsClick(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static void actionClick(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.click(webElement).perform();
    }

    public static void clickAndHold(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(webElement).release().perform();
    }

    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().perform();
    }

    public static void actionSendKeys(WebElement webElement, String text) {
        Actions actions = new Actions(driver);
        actions.sendKeys(webElement, Keys.RETURN).perform();
        webElement.sendKeys(text);
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}