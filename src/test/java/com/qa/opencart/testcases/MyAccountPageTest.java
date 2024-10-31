package com.qa.opencart.testcases;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.factory.WebDriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.io.IOException;
import static com.qa.opencart.factory.WebDriverFactory.readPropertyValue;

public class MyAccountPageTest extends TestBase{
    private Logger log =LogManager.getLogger(MyAccountPageTest.class.getName());

  /*  public HomePageTest(WebDriver driver){
        super(driver);
    }*/

@BeforeClass
public void methodName() throws InterruptedException, IOException{
    homePg = new HomePage(driver);
    regPg = new RegistrationPage(driver);
    loginPg = new LoginPage(driver);
    myaccountPg = new MyAccountPage(driver);
    logoutPg = new LogoutPage(driver);
    resultPg = new ResultsPage(driver);
    log.info("navigate to login page");
    homePg.navigateToLoginPage();
    loginPg.doLogin(readPropertyValue("username"),readPropertyValue("pwd"));
    myaccountPg.waitForPageLoad(2000);
    log.info("Verify my account page title");
    Assert.assertEquals(myaccountPg.getTitle(),Constants.MY_ACCOUNT_PAGE_TITLE);
}

     @Test(description="TC01_Verify my account page url test")
    public void TC01_verifyMyAccountPageUrlTest(){
    log.info("TC01_Verify my account page url test");
    Assert.assertTrue(myaccountPg.getMyAccountPageUrl().contains(Constants.MY_ACC_PAGE_FRACTION_URL));
    }


    @Test(description="TC02_Verify the My account page title Test")
    public void TC02_verifyMyAccountPageTitleTest(){
        log.info("TC02_Verify the My account page title Test");
        Assert.assertEquals(myaccountPg.getTitle(),Constants.MY_ACCOUNT_PAGE_TITLE);
    }

    @Test(description="TC03_Verify myAccount page elements Test")
    public void TC03_verifyMyAccountPageElementsTest() throws InterruptedException{
        log.info("TC03_Verify myAccount page elements Test");
        Assert.assertTrue(myaccountPg.issearchEditboxExist());
        Assert.assertTrue(myaccountPg.isLogoutExists());
    //after opening the menu we need to close the menu, to close it, we have to use ESCAPE char from keyboard
    myaccountPg.pressEscapeKey();
       }

    @Test(description="TC04_Verify MyAccount menu options present or not Test")
    public void TC04_verifyMyAccountMenuOptionsTest() throws InterruptedException{
        log.info("TC04_Verify MyAccount menu options present or not Test");
       log.info("Verifying the my account menu options");
       Assert.assertEquals(myaccountPg.getMyAccountMenuOptionList(),Constants.EXPECTED_MYACC_MENU_OPTS_LIST);
    }


    @Test(description="TC05_Verify my account header options list Test")
    public void TC05_verifyMyAccountHeaderOptionsListTest() throws InterruptedException{
        log.info("TC05_Verify my account header options list Test");
     Assert.assertEquals(myaccountPg.getMyAccountHeaderOptionsList(),Constants.EXPECTED_MYACC_HEADER_OPTION_LIST);

    }

    @Test(description="TC06_Verify My Orders header options list Test")
    public void TC06_verifyMyOrdersHeaderOptionsListTest() throws InterruptedException{
        log.info("TC06_Verify My Orders header options list Test");
       myaccountPg.getOrdersHeaderList().forEach(System.out::println);

    }
    @Test(description="TC07_Verify any broken links in my account page Test",dependsOnMethods="TC08_performProductSearchTest")
    public void TC07_verifyBrokenLinksInMyAccountPageTest() throws InterruptedException, IOException{
        log.info("TC07_Verify any broken links in my account page Test");
        log.info("fetch all the links from myaccount page");
     List<WebElement>myAccPageLinksList = driver.findElements(By.tagName("a"));
     for(WebElement link:myAccPageLinksList){
         verifyUrls(link.getAttribute("href"));
     }

    }

    @Test(description="TC08_Perform the product search Test",dataProvider="products")
    public void TC08_performProductSearchTest(String productName) throws InterruptedException, IOException{
        log.info("TC08_Perform the product search Test");
        resultPg =myaccountPg.doProductSearch(productName);
        resultPg.waitForPageLoad(2000);
        log.info("Verify the results page title");
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(resultPg.getTitle(),"Search - "+productName);
        sa.assertTrue(resultPg.getSearchProductListSize()>0);
        resultPg.navigateToMyAccountPage();
        myaccountPg.waitForPageLoad(2000);
        sa.assertAll();
    }

    @DataProvider(name="products")
    public Object[][] productTestData(){
    return new Object[][]{
            {"Samsung"},
            {"MacBook"},
            {"iMac"}
    };
    }


    @Test(description="TC09_Verify logout from MyAccount Test",dependsOnMethods="TC08_performProductSearchTest")
    public void TC09_verifyLogoutTest() throws InterruptedException, IOException{
        log.info("TC09_Verify logout from MyAccount Test");
       myaccountPg.clickOnLogoutLink();
        logoutPg.waitForPageLoad(2000);
        log.info("Verify the my account page title");
        Assert.assertEquals(logoutPg.getTitle(),Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
        logoutPg.clickOnContinueBtn();
        homePg.waitForPageLoad(2000);
        Assert.assertEquals(homePg.getHomePageTitle(),Constants.HOME_PAGE_TITLE);
    }

}
