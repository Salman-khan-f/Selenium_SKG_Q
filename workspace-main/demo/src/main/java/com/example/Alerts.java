package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface Alerts {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String gridurl = "http://localhost:4444";
        String pageURL = "https://demoqa.com/alerts";
        WebDriver driver = new RemoteWebDriver(new URL(gridurl), new ChromeOptions());
        // maximize
        driver.manage().window().maximize();
        // getting the url
        driver.get(pageURL);
        // page load time
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        // implicit wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // simple alert with -ok
        WebElement simpleAlert = driver.findElement(By.id("alertButton"));
        simpleAlert.click();
        Thread.sleep(3000);

        // method for handling alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        if (driver != null) {
            driver.quit();
        }
    }
}
