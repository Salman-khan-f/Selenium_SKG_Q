package com.example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class App {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String gridurl = "http://localhost:4444";
        WebDriver driver = new RemoteWebDriver(new URL(gridurl), new ChromeOptions());
        // maximize
        driver.manage().window().maximize();
        // getting the url
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String currentURL = driver.getCurrentUrl();
        Thread.sleep(4000);

        // username
        WebElement username = driver.findElement(By.xpath("//input[@name='username'] "));
        // username.click();
        username.sendKeys("Admin");
        // password
        WebElement password = driver.findElement(By.xpath("//input[@name='password'] "));
        // password.click();
        password.sendKeys("admin123");
        // login click
        WebElement login = driver.findElement(By.xpath("//button[@type='submit'] "));
        login.click();
        String actualURL = driver.getCurrentUrl();

        if (actualURL.equals(currentURL)) {
            System.out.println("Login Failed");

        } else {
            System.out.println("Login Success");

        }

        System.out.println(username);
        // driver.quit();


        // Select select = new Select(driver.findElement(By.xpath("")));
        
        driver.close();

    }
}
