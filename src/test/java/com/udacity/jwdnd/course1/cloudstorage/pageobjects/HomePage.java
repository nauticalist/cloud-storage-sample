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

}
