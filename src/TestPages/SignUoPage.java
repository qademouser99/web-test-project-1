package TestPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.Reporter;

public class SignUoPage {
	private WebDriver driver;
	private Reporter reporter;

	public SignUoPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Element
	public WebElement getFirstName() {
		return getWebElement(By.name("firstname"));
	}

	public WebElement getLastName() {
		return getWebElement(By.name("lastname"));
	}

	public WebElement getEmail() {
		return getWebElement(By.name("reg_email__"));
	}

	public WebElement getEmailConfirm() {
		return getWebElement(By.name("reg_email_confirmation__"));
	}

	public WebElement getEmailPass() {
		return getWebElement(By.name("reg_passwd__"));
	}

	public Select getBirthdayMomth() {
		return new Select(getWebElement(By.id("month")));
	}

	public Select getBirthdayDay() {
		return new Select(getWebElement(By.id("day")));
	}

	public Select getBirthdayYear() {
		return new Select(getWebElement(By.id("year")));
	}

	public WebElement getSexRadioMale() {
		return getWebElement(By.id("u_0_o")); // Male
	}

	public WebElement getSexRadioFemale() {
		return getWebElement(By.id("u_0_n")); // Female
	}

	public WebElement getButtonSignUp() {
		return getWebElement(By.name("websubmit"));
	}

	public WebElement getpopUpMassage() {
		return getWebElement(By
				.xpath("//*[contains(text(), 'Enter a combination of at')]"));
	}

	// Action

	public void SelectBirthDay(String month, String day, String year) {
		this.getBirthdayMomth().selectByVisibleText(month);
		this.getBirthdayDay().selectByVisibleText(day);
		this.getBirthdayYear().selectByVisibleText(year);
		this.reporter.info("Put birthday detail " + month +"-" + day +"-" + year );
	}

	public void SignUpMethod(String firstName, String lastName, String email,
			String emailConfirm, String pass, String birthdayMomth,
			String birthdayDay, String birthdayYear, boolean isMale) {

		getFirstName().sendKeys(firstName);
		this.reporter.info("Put firstName " + firstName);
		getLastName().sendKeys(lastName);
		this.reporter.info("Put lastName " + lastName);
		getEmail().sendKeys(email);
		this.reporter.info("Put email " + email);
		getEmailConfirm().sendKeys(emailConfirm);
		this.reporter.info("Confirm email " + emailConfirm);
		getEmailPass().sendKeys(pass);
		this.reporter.info("The password is set");
		SelectBirthDay(birthdayMomth, birthdayDay, birthdayYear);
		if (isMale) {
			getSexRadioMale().click();
			this.reporter.info("The sex is male ");
		} else {
			getSexRadioFemale().click();
			this.reporter.info("The sex is female");
		}
		getButtonSignUp().click();
		this.reporter.info("The signup button is clicked");
	}

	public void VerifyPopUpExist() {
		try {
			if(this.getpopUpMassage().isDisplayed()){			
			this.reporter.pass("Test is pass, warnyng PopUp exist ");
			Assert.assertTrue(this.getpopUpMassage().isDisplayed());
			}else{
				this.reporter.error("Test is fail, warnyng PopUp not exist ");
				Assert.assertTrue(this.getpopUpMassage().isDisplayed());
			}			
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profile name element not found");
		} catch (Exception ex) {
			this.reporter.error("Cant verify logged in username");
		}

	}

	private WebElement getWebElement(By selector) {
		WebElement webelement = null;
		if (this.driver != null) {
			webelement = this.driver.findElement(selector);
		} else {
			System.out.println("Driver is not initialized!");
		}
		return webelement;
	}

}
