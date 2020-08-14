package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    private final WebDriverWait webDriverWait;

    @FindBy(className = "display-5")
    private WebElement title;

    @FindBy(id = "success-msg")
    private WebElement successMessage;

    @FindBy(id = "error-msg")
    private WebElement errorMessage;

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver driver) {
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.inputFirstName));
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.submitButton));
        this.submitButton.submit();
    }


    public String getTitle() {
        webDriverWait.until(ExpectedConditions.visibilityOf(this.title));
        return title.getText();
    }

    public String getSuccessMessage() {

        webDriverWait.until(ExpectedConditions.visibilityOf(this.successMessage));
        return successMessage.getText();
    }

    public String getErrorMessage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(this.errorMessage));
        return errorMessage.getText();
    }


}
