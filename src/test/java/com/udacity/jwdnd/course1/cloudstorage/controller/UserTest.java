package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;

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
    public void testUnauthorizedAccessRedirectsToLoginPage() {
        driver.get("http://localhost:" + port + "/");
        this.loginPage = new LoginPage(driver);
        assertEquals("Login", loginPage.getTitle());
    }

    @Test
    @Order(2)
    public void testLoginPageRedirectsToSignupPage() {
        driver.get("http://localhost:" + port + "/");
        this.loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        assertEquals("Sign Up", loginPage.getTitle());
    }

    @Test
    @Order(3)
    public void testErrorOccursWithInvalidLoginCredentials() {
        driver.get("http://localhost:" + port + "/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        this.loginPage = new LoginPage(driver);
        loginPage.login("invaliduser", "pwd");
        Assert.hasText("Invalid username or password", loginPage.getErrorMessage());
    }

    @Test
    @Order(4)
    public void testNewUserSignUpIsSuccessful() {
        driver.get("http://localhost:" + port + "/signup");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        Assert.hasText("You successfully signed up! Please continue to the login page.", signupPage.getSuccessMessage());
    }

    @Test
    @Order(5)
    public void testErrorOccursDuringSignUpIfUserExists() {
        driver.get("http://localhost:" + port + "/signup");
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        // Register with same user details
        driver.get("http://localhost:" + port + "/signup");
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        Assert.hasText("The username already exists.", signupPage.getErrorMessage());
    }

    @Test
    @Order(6)
    public void testLoginWithValidCredentialsIsSuccessfull() {
        // 1. register user
        driver.get("http://localhost:" + port + "/signup");
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        // 2. Login with the registered user
        driver.get("http://localhost:" + port + "/login");
        this.loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        // 3. Check is home page is accessible
        driver.get("http://localhost:" + port + "/");
        Assert.hasText("Home", driver.getTitle());
    }

    @Test
    @Order(7)
    public void testRedirectToLoginPageAfterLogoutIsSuccessfull() throws InterruptedException{
        // 1. register user
        driver.get("http://localhost:" + port + "/signup");
        this.signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);

        // 2. Login with the registered user
        driver.get("http://localhost:" + port + "/login");
        this.loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // 3. Check is logout is successful
        driver.get("http://localhost:" + port + "/");
        this.homePage = new HomePage(driver);
        homePage.logout();
        Assert.hasText("Login", driver.getTitle());
    }

}