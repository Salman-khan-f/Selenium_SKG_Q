package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Test_1 {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String gridurl = "http://localhost:4444";
        String pageURL = "https://demoqa.com/automation-practice-form";
        WebDriver driver = new RemoteWebDriver(new URL(gridurl), new ChromeOptions());
        // maximize
        driver.manage().window().maximize();
        // getting the url
        driver.get(pageURL);
        // page load time
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        // implicit wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // enter the first name
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("mano");

        // enter the last name
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("A");

        // enter the last name
        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("mano@gmail.com");

        // // enter the gender name
        WebElement maleCheckbox = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        maleCheckbox.click();
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("9898989898");

        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();

        // select month
        WebElement monthSelect = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        Select selectMonth = new Select(monthSelect);
        selectMonth.selectByValue("6");

        // select year

        WebElement yearSelect = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select selectYear = new Select(yearSelect);
        selectYear.selectByValue("2025");
        // selecting the date
        WebElement dateClick = driver
                .findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--023']"));

        dateClick.click();
        Thread.sleep(3000);
        // closing or quitting the driver
        if (driver != null) {
            driver.quit();
        }
    }

}
