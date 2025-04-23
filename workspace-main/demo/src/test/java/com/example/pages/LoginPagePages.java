package com.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.uistore.BookStoreLoginUI;
import com.example.utils.WebDriverHelper;

public class LoginPagePages {

    BookStoreLoginUI loginUiLocators = new BookStoreLoginUI();

    private WebDriver driver = null;
    Logger logger = LogManager.getLogger(LoginPagePages.class);

    public LoginPagePages(WebDriver driver) {

        this.driver = driver;

    }

    public void enter_username(String username) {
        WebElement usernameBox = WebDriverHelper.getElementByXpath(loginUiLocators.usernameXpath);
        logger.warn("Username box is identified");
        usernameBox.sendKeys(username);
        logger.info("Username  is entered.");

    }

    public void enter_password(String password) {
        WebElement passwordBox = driver.findElement(loginUiLocators.passwordXpath);
        logger.info("Password box is identified");
        passwordBox.sendKeys(password);
        logger.info("Password  is entered.");
    }

    public void login_button() {
        WebElement loginButton = driver.findElement(loginUiLocators.loginButtonXpath);
        logger.info("Login button is identified");
        loginButton.click();
        logger.info("Login button is clicked.");
    }

}
