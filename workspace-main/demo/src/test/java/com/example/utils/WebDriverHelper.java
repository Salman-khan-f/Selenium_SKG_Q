package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverHelper {
     static WebDriver driver = null;

    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public static WebElement getElementByXpath(By xpath) {
        return driver.findElement(xpath);
    }

}
