package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriverWait webDriverWait;

    @FindBy(className = "display-5")
    private WebElement title;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    @FindBy(id = "logout-message")
    private WebElement logoutMessage;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    public LoginPage(WebDriver driver) {
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(this.inputUsername));
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(this.submitButton));
        this.submitButton.submit();
    }

    public String getTitle() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.title));
        return title.getText();
    }

    public String getErrorMessage() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.errorMessage));
        return this.errorMessage.getText();
    }

    public String getLogoutMessage() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.logoutMessage));
        return this.logoutMessage.getText();
    }

    public void clickRegisterLink() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.signupLink));
        this.signupLink.click();
    }
}
