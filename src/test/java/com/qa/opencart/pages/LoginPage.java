package com.qa.opencart.pages;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends WebDriverUtils{
   private Logger log =LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h2[normalize-space()='New Customer']")
    private WebElement newCustomerHeader;
    @FindBy(css="a[class*='btn-primary']")
    private WebElement newCustomerContinueBtn;

    @FindBy(xpath="//h2[normalize-space()='Returning Customer']")
    private WebElement returningCustomerHeader;

    @FindBy(id="input-email")
    private WebElement emailAddressEditbox;
    @FindBy(name="password")
    private WebElement passwordEditbox;

    @FindBy(xpath="//a[normalize-space()='Forgotten Password']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath="//input[@value='Login']")
    private WebElement loginBtn;

    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;

    @FindBy(xpath="//ul[@class='breadcrumb']//a[normalize-space()='Login']")
    private WebElement loginBreadCrumb;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emptyCredsErrorMsg;

    public String getLoginPageTitle(){
        return getTitle();
    }

    public String getLoginPageUrl(){
        return waitForUrlContains(Constants.LOGIN_PAGE_FRACTION_URL);
    }

    public boolean isLoginBreadCrumbExist(){
       return isDisplayed(loginBreadCrumb);
    }

    public boolean isNewCustomerHeaderExist(){
        return isDisplayed(newCustomerHeader);
    }

    public boolean isReturningCustomerHeaderExist(){
        return isDisplayed(returningCustomerHeader);
    }


    public void clickNewCustomerContinueBtn() throws InterruptedException{
        log.info("click on NewCustomerContinueBtn");
        click(newCustomerContinueBtn);
    }

    public void navigateTForgotPasswordrPage() throws InterruptedException{
        log.info("click on forgot password link");
               click(forgotPasswordLink);
    }

    public void doLogin(String uname,String pwd) throws InterruptedException{
        log.info("type username in username field");
        type(emailAddressEditbox,uname);
        log.info("type password in password field");
       type(passwordEditbox,pwd);
        log.info("click on login button");
        click(loginBtn);
    }
    public void clickBreadCrumbHomeIcon() throws InterruptedException{
        log.info("click on home icon in breadcrumb");
        click(homeIcon);
    }

    public String getEmptyCredsErrorMsg() throws InterruptedException{
        log.info("fetching empty credentials error message");
        return getText(emptyCredsErrorMsg);
    }
}
