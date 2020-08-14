package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriverWait webDriverWait;

    @FindBy(css="#logout")
    private WebElement logoutForm;


    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTabLink;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabLink;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTabLink;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(className = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(className = "delete-note-link")
    private WebElement deleteNoteLink;

    @FindBy(className = "note-title-text")
    private WebElement noteTitle;

    @FindBy(className = "note-description-text")
    private WebElement noteDescription;

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;

    @FindBy(id = "save-note-changes")
    private WebElement saveNoteChangesButton;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(className = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(className = "delete-credential-link")
    private WebElement deleteCredentialLink;

    @FindBy(className = "credential-url-text")
    private WebElement credentialUrl;

    @FindBy(className = "credential-username-text")
    private WebElement credentialUsername;

    @FindBy(className = "credential-password-text")
    private WebElement credentialPassword;

    @FindBy(id = "credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id = "save-credential-changes")
    private WebElement saveCredentialButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.webDriverWait = new WebDriverWait(driver, 10);
    }

    public void logout() {
        webDriverWait.until(ExpectedConditions.visibilityOf(logoutForm));
        logoutForm.submit();
    }

    public void goToNotesTab() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(notesTabLink));
        notesTabLink.click();
    }

    public void goToCredentialsTab() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialsTabLink));
        credentialsTabLink.click();
    }

    public void addNote(String noteTitle, String noteDescription) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        addNoteButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputNoteTitle));
        this.inputNoteTitle.sendKeys(noteTitle);
        this.inputNoteDescription.sendKeys(noteDescription);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveNoteChangesButton));
        this.saveNoteChangesButton.click();
    }

    public void editNote(String noteTitle, String noteDescription) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputNoteTitle));
        this.inputNoteTitle.clear();
        this.inputNoteTitle.sendKeys(noteTitle);
        this.inputNoteDescription.clear();
        this.inputNoteDescription.sendKeys(noteDescription);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveNoteChangesButton));
        this.saveNoteChangesButton.click();
    }

    public String getNoteTitle() {
        webDriverWait.until(ExpectedConditions.visibilityOf(noteTitle));
        return this.noteTitle.getText();
    }


    public String getNoteDescription() {
        webDriverWait.until(ExpectedConditions.visibilityOf(noteDescription));
        return this.noteDescription.getText();
    }

    public void clickDeleteButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteNoteLink));
        this.deleteNoteLink.click();
    }

    public void clickEditNoteButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        this.editNoteButton.click();
    }

    public void addCredential(String url, String username, String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        addCredentialButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputCredentialUrl));
        inputCredentialUrl.sendKeys(url);
        inputCredentialUsername.sendKeys(username);
        inputCredentialPassword.sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));
        saveCredentialButton.click();
    }

    public String getCredentialUrl() {
        webDriverWait.until(ExpectedConditions.visibilityOf(credentialUrl));
        return credentialUrl.getText();
    }

    public String getCredentialUsername() {
        webDriverWait.until(ExpectedConditions.visibilityOf(credentialUsername));
        return credentialUsername.getText();
    }

    public String getCredentialPassword() {
        webDriverWait.until(ExpectedConditions.visibilityOf(credentialPassword));
        return credentialPassword.getText();
    }
    public void clickDeleteCredentialButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteCredentialLink));
        this.deleteCredentialLink.click();
    }

    public void clickEditCredentialButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        this.editCredentialButton.click();
    }

    public String getCredentialPasswordInputEntry() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputCredentialPassword));
        return inputCredentialPassword.getText();
    }

    public void editCredential(String url, String username, String password){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputCredentialUrl));
        this.inputCredentialUrl.clear();
        this.inputCredentialUrl.sendKeys(url);
        this.inputCredentialUsername.clear();
        this.inputCredentialUsername.sendKeys(username);
        this.inputCredentialPassword.clear();
        this.inputCredentialPassword.sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));
        this.saveCredentialButton.click();
    }
}
