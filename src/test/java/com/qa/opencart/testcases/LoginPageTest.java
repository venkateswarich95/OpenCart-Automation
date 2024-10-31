package com.qa.opencart.testcases;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.factory.WebDriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends TestBase{
    private Logger log =LogManager.getLogger(LoginPageTest.class.getName());

  /*  public HomePageTest(WebDriver driver){
        super(driver);
    }*/

@BeforeClass
public void methodName() throws InterruptedException{
    homePg = new HomePage(driver);
    regPg = new RegistrationPage(driver);
    loginPg = new LoginPage(driver);
    myaccountPg = new MyAccountPage(driver);
    logoutPg = new LogoutPage(driver);
    log.info("navigate to login page");
    homePg.navigateToLoginPage();
}

    @BeforeMethod
    public void setup(){
        log.info("wait for the home page load");
        loginPg.waitForPageLoad(5000);

    }

    @Test(description="TC01_Verify the  login page url test")
    public void TC01_verifyLoginPageUrlTest(){
    log.info(" Verify the  login page url test");
    Assert.assertTrue(loginPg.getLoginPageUrl().contains(Constants.LOGIN_PAGE_FRACTION_URL));
    }


    @Test(description="TC02_Verify the login page title Test")
    public void TC02_verifyLoginPageTitleTest(){
        log.info("verifyLoginPageTitleTest");
        Assert.assertEquals(loginPg.getTitle(),Constants.LOGIN_PAGE_TITLE);
    }

    @Test(description="TC03_Verify the login page elements Test")
    public void TC03_verifyLoginPageElementsTest(){
        log.info("TC03_Verify the login page elements Test");
        Assert.assertTrue(loginPg.isLoginBreadCrumbExist());
        Assert.assertTrue(loginPg.isNewCustomerHeaderExist());
        Assert.assertTrue(loginPg.isReturningCustomerHeaderExist());
       }

    @Test(description="TC04_Verify navigate to registration page from login page Test")
    public void TC04_verifyNavigateToRegistrationPageFromLoginPageTest() throws InterruptedException{
        log.info("TC04_verifyNavigateToRegistrationPageFromLoginPageTest");
       log.info("navigate to Registration page from login page");
      loginPg.clickNewCustomerContinueBtn();
       regPg.waitForPageLoad(1000);
       log.info("Verify the Registration page Title");
       Assert.assertEquals(regPg.getTitle(),Constants.REGISTRATION_PAGE_TITLE);
       log.info("navigate back to Home page");
       regPg.clickOnLoginPageLink();
    }


    @Test(description="TC05_Verify navigate to forgot password page from login page Test")
    public void TC05_verifyNavigateToForgotPasswordFromLoginPageTest() throws InterruptedException{
        log.info("TC05_Verify navigate to forgot password page from login page Test");
        log.info("navigate to forgot password page from login page");
       loginPg.navigateTForgotPasswordrPage();
        loginPg.waitForPageLoad(1000);
        log.info("Verify the login page Title");
       driver.navigate().back();

    }

    @Test(description="TC06_Verify empty credential login page Test")
    public void TC06_verifyEmptyCredsLoginPageTest() throws InterruptedException{
        log.info("TC06_Verify empty credential login page Test");
        loginPg.doLogin(" "," ");
        loginPg.waitForPageLoad(2000);
        log.info("Verify the empty creds error message in login page");
        Assert.assertTrue(loginPg.getEmptyCredsErrorMsg().contains(Constants.LOGIN_NOMATCH_ERR_MSG));

    }

    @Test(description="TC07_Verify valid credential login page Test",dependsOnMethods="TC06_verifyEmptyCredsLoginPageTest()")
    public void TC07_verifyValidCredsLoginTest() throws InterruptedException, IOException{
        log.info("TC07_Verify valid credential login page Test");
        loginPg.doLogin(WebDriverFactory.readPropertyValue("username"),WebDriverFactory.readPropertyValue("pwd"));
        myaccountPg.waitForPageLoad(2000);
        log.info("Verify the my account page title");
        Assert.assertEquals(myaccountPg.getMyAccountPageTitle(),Constants.MY_ACCOUNT_PAGE_TITLE);

    }
    @Test(description="TC08_Verify logout from MyAccount Test",dependsOnMethods="TC07_verifyValidCredsLoginTest")
    public void TC08_verifyLogoutTest() throws InterruptedException, IOException{
        log.info("TC08_Verify logout from MyAccount Test");
       myaccountPg.clickOnLogoutLink();
        logoutPg.waitForPageLoad(2000);
        log.info("Verify the my account page title");
        Assert.assertEquals(logoutPg.getTitle(),Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
        logoutPg.clickOnContinueBtn();
        homePg.waitForPageLoad(2000);
        Assert.assertEquals(homePg.getHomePageTitle(),Constants.HOME_PAGE_TITLE);
    }

}
