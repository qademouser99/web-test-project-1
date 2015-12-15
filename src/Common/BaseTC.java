package Common;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import TestPages.HomePage;
import TestPages.InvalidLoginPage;
import TestPages.LogInPage;
import TestPages.SignUoPage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTC {
	protected WebDriver driver;
	protected Reporter reporter;
	protected ExtentReports extent;
	private static long timestamp = new Date().getTime();
	
	
	// Test Page
	protected LogInPage loginPage;
	protected HomePage homePage;
	protected InvalidLoginPage invalidLoginPage;
	protected SignUoPage signUpPage;

	private void initTestPages() {
		this.loginPage = new LogInPage(this.driver , this.reporter);
		this.homePage = new HomePage(this.driver, this.reporter);
		this.invalidLoginPage = new InvalidLoginPage(this.driver, this.reporter);
		this.signUpPage = new SignUoPage(this.driver, this.reporter);
	}
	
	private String getReortPath(){		
		String currentFolder = Paths.get("").toAbsolutePath().toString();
		String reportPath = currentFolder + "\\report-" + timestamp + ".html";
		File reportFile = new File(reportPath);
		return reportPath;
	}
	
	@BeforeClass
	public void beforeClass(){
		String reportPath = getReortPath();
		this.reporter = new Reporter();
		this.extent = new ExtentReports(reportPath, false);
	}

	@BeforeMethod
	public void beforeMethod(Method testCase) {
		//this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		initTestPages();
		ExtentTest currentTest = this.extent.startTest(testCase.getName());
		this.reporter.setTest(currentTest);
	}

	@AfterMethod
	public void afterMethod() {
		if (this.driver != null) {
			this.driver.quit();
		} else {
			System.out.println("Driver is not initialized");
		}
		ExtentTest currentTest = this.reporter.getTest();
		 // end test
        extent.endTest(currentTest);        
        // calling flush writes everything to the log file
        extent.flush();
	}

	protected void open(String url) {
		if (this.driver != null) {
			this.driver.get(url);
		} else {
			System.out.println("Driver is not initialized");
		}

	}

}
