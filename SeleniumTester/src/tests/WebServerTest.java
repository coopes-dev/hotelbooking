package tests;

import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WebServerTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");	
		System.out.println("Trying to load up chrome driver...");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
		System.out.println("Loaded driver");
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	private String getRandomUser() {
		String returnValue = "u";
		Random rng = new Random(System.currentTimeMillis());
		for (int idx = 0; idx < 5; idx++) {
			returnValue += rng.nextLong();
		}
		return (returnValue);
	}

	/**
	 * Passqword is too short test
	 */
	@Test
	public void test1() {
		System.out.println("Running tests!");
		driver.get("http://localhost/WebView/register.html");
		driver.manage().window().setSize(new Dimension(821, 575));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys(getRandomUser());
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("abcdefg");
		driver.findElement(By.id("registerButton")).click();
		WebElement TextElement = driver.findElement(By.id("response"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		assertThat(driver.findElement(By.id("response")).getText(),
				is("{{TEXT_PASSWORD_TOO_SHORT}8{TEXT_CHARACTERS}}"));

	}

	/**
	 * Password is missing digit on registration test
	 */
	@Test
	public void test2() {
		System.out.println("Running tests!");
		driver.get("http://localhost/WebView/register.html");
		driver.manage().window().setSize(new Dimension(821, 575));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys(getRandomUser());
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("abcdefgABCDEF");
		driver.findElement(By.id("registerButton")).click();
		WebElement TextElement = driver.findElement(By.id("response"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		assertThat(driver.findElement(By.id("response")).getText(), is("{ResponseCode:PASSWORD_MISSING_DIGIT}"));
	}

	@Test
	public void t3() {
		driver.get("http://localhost/WebView/register.html");
		driver.manage().window().setSize(new Dimension(1536, 824));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("coopes");
		driver.findElement(By.id("password")).sendKeys("ABCDabcd1234");
		driver.findElement(By.id("registerButton")).click();
		WebElement TextElement = driver.findElement(By.id("response"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		driver.get("http://localhost/WebView/loginr.html");
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("coopes");
		driver.findElement(By.id("password")).sendKeys("ABCDabcd1234");
		driver.findElement(By.id("loginButton")).click();
		TextElement = driver.findElement(By.id("response"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		assertThat(driver.findElement(By.id("response")).getText(), is("ResponseCode:OK"));
	}

}
