package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.pages.LoginPagePages;
import com.example.utils.ExcelReader;
import com.example.utils.ExtentReportsHelper;
import com.example.utils.Screenshot;
import com.example.utils.WebDriverHelper;

public class Hybrid {

    public WebDriver driver = null;

    static ExtentReports report = null;

    LoginPagePages pages = null;
    WebDriverHelper helper = null;
    Screenshot shot = null;

    @BeforeClass
    public static void initConfig() {

        report = ExtentReportsHelper.extentreports();
    }

    @BeforeMethod
    public void launch_browser() throws MalformedURLException, InterruptedException {
        String gridurl = "http://localhost:4444";
        driver = new RemoteWebDriver(new URL(gridurl), new ChromeOptions());
        // maximize
        driver.manage().window().maximize();
        // getting the url
        driver.get("https://demoqa.com/frames/");
        String currentURL = driver.getCurrentUrl();
        pages = new LoginPagePages(driver);
        helper = new WebDriverHelper(driver);
        shot = new Screenshot(driver);
        System.out.println(currentURL);
        Thread.sleep(4000);
    }

    @AfterMethod
    public void tear_down() {
        if (driver != null) {
            driver.quit();
        }

    }

    @AfterClass
    public static void flush() {
        if (report != null)
            report.flush();
    }

    @Test(enabled = false)
    public void interact_with_frame() {
        String h1xpath = "//h1[@id='sampleHeading']";
        String iframe1Id = "frame1";
        String outsideFrame1 = "//div[@id='framesWrapper']//div[1]";
        // Switch to iframe
        driver.switchTo().frame(iframe1Id);
        WebElement h1Iframe1 = driver.findElement(By.xpath(h1xpath));
        System.out.println("Text inside the iframe 1 :" + h1Iframe1.getText());
        driver.switchTo().parentFrame();
        WebElement outsideFrame1Page = driver.findElement(By.xpath(outsideFrame1));
        System.out.println(outsideFrame1Page.getText());
        Assert.assertEquals("mano", "mano");
    }

    @Test
    public void login_demoPage() throws IOException, InterruptedException {
        driver.get("https://demoqa.com/login");
        String excelPath = "/home/coder/project/workspace/demo/testdata/login.xlsx";
        String excelSheetName = "Sheet1";
        String username = ExcelReader.readExcel(excelPath, excelSheetName, 1, 0);
        String password = ExcelReader.readExcel(excelPath, excelSheetName, 1, 1);
        pages.enter_username(username);
        // report.createTest("TC_001").log(Status.PASS, "Extent report with tc 001");
        shot.screenshot("username_screenshot");
        pages.enter_password("pass");
        // shot.screenshot("password_screenshot");

        pages.login_button();

        String logoutXpath = "(//button[@id=\"submit\" and@type='button'])[1]";
        Thread.sleep(3000);
        boolean isDisplayed = false;

        ExtentTest test = report.createTest("Test case login");

        try {
            WebElement logoutButton = driver.findElement(By.xpath(logoutXpath));
            isDisplayed = logoutButton.isDisplayed();

        } catch (Exception e) {
            test.log(Status.WARNING, "Exception Occured" + e.getMessage());
        }

        if (isDisplayed) {
            // report.createTest("Testcasepassed").log(Status.PASS, "Login In done");
            test.log(Status.PASS, "Login Success");
        } else {
            Screenshot shrScreenshot = new Screenshot(driver);
            String path = shrScreenshot.screenshot("loginFailedScreenshot");

            test.fail( "Login Failed ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }

    }

}