package Tests;

import org.testng.annotations.Test;

import Common.BaseTC;

public class MyTestClass extends BaseTC {

	@Test
	public void Login() {

		open("http://facebook.com");
		loginPage.getEmailTextBox().sendKeys("qademouser99@gmail.com");
		loginPage.getEmailPass().sendKeys("one+two=3");
		loginPage.logInClick().click();
		homePage.VerifyProfileName();

	}

	@Test
	public void NotLogin() {

		open("http://facebook.com");
		loginPage.getEmailTextBox().sendKeys("qademouser99@gmail.com");
		loginPage.getEmailPass().sendKeys("one+two");
		loginPage.logInClick().click();
		invalidLoginPage.VerifyForgotPassLink(); 
	}

	@Test
	public void SignUpNoPass() {

		open("http://facebook.com");
		signUpPage.SignUpMethod("Pencho", "Penchev", "Pencho@mail.com", "Pencho@mail.com", "", "Nov", "3", "1973", true);

		signUpPage.VerifyPopUpExist();
	}

}