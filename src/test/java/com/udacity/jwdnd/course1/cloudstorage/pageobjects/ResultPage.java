package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    private final WebDriverWait webDriverWait;

    @FindBy(id = "success-message")
    private WebElement successMessage;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    @FindBy(id = "success-link")
    private WebElement successLink;

    @FindBy(id = "error-link")
    private WebElement errorLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.webDriverWait = new WebDriverWait(driver, 20);
    }

    public String getSuccessMessage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(this.successMessage));
        return this.successMessage.getText();
    }

    public String getErrorMessage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(this.errorMessage));
        return this.errorMessage.getText();
    }

    public void clickSuccessLink() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.successLink));
        this.successLink.click();
    }

    public void clickErrorLink() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.errorLink));
        this.errorLink.click();
    }
}
