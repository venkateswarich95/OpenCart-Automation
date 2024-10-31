package com.qa.opencart.testcases;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
    private Logger log =LogManager.getLogger(HomePageTest.class.getName());

  /*  public HomePageTest(WebDriver driver){
        super(driver);
    }*/

@BeforeClass
public void methodName(){
    homePg = new HomePage(driver);
    regPg = new RegistrationPage(driver);
    loginPg = new LoginPage(driver);
}

    @BeforeMethod
    public void setup(){
        log.info("wait for the home page load");
        homePg.waitForPageLoad(5000);
        Assert.assertTrue(homePg.issearchEditboxExist(),"search editbox doesnot exist");
    }

    @Test(description="TC01_Verify the opencart page title")
    public void TC01_verifyOpenCartPageTitleTest(){
    log.info("verify the opencart page title");
    Assert.assertEquals(homePg.getHomePageTitle(),Constants.HOME_PAGE_TITLE);
    }


    @Test(description="TC02_Verify the opencart logo Test")
    public void TC02_verifyOpenCartLogoTest(){
        log.info("verify the opencart logo");
        Assert.assertTrue(homePg.isOpenCartLogoExist());
    }

    @Test(description="TC03_Verify the opencart featured section cards count Test")
    public void TC03_verifyOpenCartFeaturedSectionCardsCountTest(){
        log.info("TC03_Verify the opencart featured section cards count Test");
        Assert.assertTrue(homePg.getFeaturedSectionCardsCount()==4);
    }

    @Test(description="TC04_Verify navigate to registration page from home page Test")
    public void TC04_verifynavigateToRegistrationPageTest() throws InterruptedException{
        log.info("TC04_Verify navigate to registration page from home page Test");
       log.info("navigate to Registration page from home page");
       homePg.navigateToRegisterPage();
       regPg.waitForPageLoad(1000);
       log.info("Verify the Registration page Title");
       Assert.assertEquals(regPg.getTitle(),Constants.REGISTRATION_PAGE_TITLE);
       log.info("navigate back to Home page");
       regPg.clickBreadCrumbHomeIcon();
    }


    @Test(description="TC05_Verify navigate to login page from home page Test")
    public void TC05_verifyNavigateToLoginPageTest() throws InterruptedException{
        log.info("TC04_Verify navigate to login page from home page Test");
        log.info("navigate to login page from home page");
        homePg.navigateToLoginPage();
        loginPg.waitForPageLoad(1000);
        log.info("Verify the login page Title");
        Assert.assertEquals(loginPg.getTitle(),Constants.LOGIN_PAGE_TITLE);
        log.info("navigate back to Home page");
        loginPg.clickBreadCrumbHomeIcon();
    }

    @AfterMethod
    public void tearDown(){
        homePg.waitForPageLoad(2000);
        Assert.assertEquals(homePg.getTitle(),Constants.HOME_PAGE_TITLE);
    }
}
