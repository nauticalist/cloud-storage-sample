package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

//	@LocalServerPort
//	private int port;
//	private static WebDriver driver;
//
//	final String HOME_PAGE = "http://localhost:" + this.port + "/";
//	final String LOGIN_PAGE = "http://localhost:" + this.port + "/login";
//	final String SIGNUP_PAGE = "http://localhost:" + this.port + "/signup";
//
//	@BeforeAll
//	public static void beforeAll() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//	}
//
//	@AfterEach
//	public void afterEach() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
//
//	@Test
//	public void testUnauthorizedAccessRedirectsToLoginPage() {
//		driver.get(HOME_PAGE);
//		Assertions.assertEquals("Login", driver.getTitle());
//
//	}
//
//	@Test
//	public void testGetSignUpPage() {
//		driver.get(LOGIN_PAGE);
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		WebElement signupLink = driver.findElement(By.id("signup-link"));
//		signupLink.click();
//		Assertions.assertEquals("Sign Up", driver.getTitle());
//	}

}
