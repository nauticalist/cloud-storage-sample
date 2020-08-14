package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeControllerTest {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;
    private ResultPage resultPage;

    private final String firstName = "Test";
    private final String lastName = "User";
    private final String username = "testuser";
    private final String password = "testPassword";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void testAddNewNoteIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToNotesTab();
        // add new note
        homePage.addNote("Test Title", "Test Note Description");
        // confirm redirection to results page and note saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your note were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new note exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("Test Title", homePage.getNoteTitle());
        Assert.hasText("Test Note Description", homePage.getNoteDescription());
    }

    @Test
    @Order(2)
    public void testDeleteExistingNoteIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToNotesTab();
        // add new note
        homePage.addNote("Test Title 2", "Test Note Description");
        // confirm redirection to results page and note saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your note were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new note exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("Test Title 2", homePage.getNoteTitle());
        homePage.clickDeleteButton();
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your note were successfully deleted.", resultPage.getSuccessMessage());
        this.resultPage.clickSuccessLink();
        Assert.doesNotContain("Test Title 2", driver.getPageSource(), "Note were not removed from database. Something went wrong");
    }


    @Test
    @Order(3)
    public void testUpdateExistingNoteIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToNotesTab();
        // add new note
        homePage.addNote("Test Title 3", "Test Note Description");
        // confirm redirection to results page and note saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your note were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new note exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("Test Title 3", homePage.getNoteTitle());
        homePage.clickEditNoteButton();
        homePage.editNote("Test Title 3 Edited", "Test Note Description Edited");
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your note were successfully saved.", resultPage.getSuccessMessage());
        this.resultPage.clickSuccessLink();
        Assert.hasText("Test Title 3 Edited", homePage.getNoteTitle());
        Assert.hasText("Test Note Description Edited", homePage.getNoteDescription());
    }

    @Test
    @Order(4)
    public void testAddNewCredentialIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToCredentialsTab();
        // add new credential
        homePage.addCredential("facebook.com", "testuser", "testpassword");
        // confirm redirection to results page and credential saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your credential were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new credential exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("facebook.com", homePage.getCredentialUrl());
        Assert.hasText("testuser", homePage.getCredentialUsername());
        Assert.doesNotContain("testpassword", homePage.getCredentialPassword(), "Password is not encrypted");
    }

    @Test
    @Order(5)
    public void testDeleteExistingCredentialIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToCredentialsTab();
        // add new credential
        homePage.addCredential("twitter.com", "testuser", "testpassword");
        // confirm redirection to results page and credential saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your credential were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new credential exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("twitter.com", homePage.getCredentialUrl());
        homePage.clickDeleteCredentialButton();
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your credential were successfully deleted.", resultPage.getSuccessMessage());
        this.resultPage.clickSuccessLink();
        Assert.doesNotContain("twitter.com", driver.getPageSource(), "Credential were not removed from database. Something went wrong");
    }

    @Test
    @Order(6)
    public void testUpdateExistingCredentialIsSuccessful() {
        this.createUserAndLogin();
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.goToCredentialsTab();
        // add new note
        homePage.addCredential("linkedin.com", "testuser", "testpassword");
        // confirm redirection to results page and credential saved successfuly
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your credential were successfully saved.", resultPage.getSuccessMessage());
        // Return back to the hame page and verify new credential exists
        resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("linkedin.com", homePage.getCredentialUrl());
        homePage.clickEditCredentialButton();
        Assert.hasText("testpassword", homePage.getCredentialPasswordInputEntry());
        String oldEncryptedPassword = homePage.getCredentialPassword();
        homePage.editCredential("reddit.com", "testuser2", "testpassword2");
        this.resultPage = new ResultPage(driver);
        Assert.hasText("Your credential were successfully saved.", resultPage.getSuccessMessage());
        this.resultPage.clickSuccessLink();
        this.homePage = new HomePage(driver);
        Assert.hasText("reddit.com", homePage.getCredentialUrl());
        Assert.hasText("testuser2", homePage.getCredentialUsername());
        Assert.doesNotContain("testpassword2", homePage.getCredentialPassword(), "Password is not encrypted");
        assertNotEquals(oldEncryptedPassword, homePage.getCredentialPassword());
    }

    private void createUserAndLogin(){
        // 1. Create new user
        driver.get("http://localhost:" + port + "/signup");
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        // 2. Login with the registered user
        driver.get("http://localhost:" + port + "/login");
        this.loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

}