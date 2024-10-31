package com.qa.opencart.pages;

import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends WebDriverUtils{
   private Logger log =LogManager.getLogger(LogoutPage.class);

    public LogoutPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div[id='content'] h1")
    private WebElement accountLogoutHeader;
    @FindBy(css="#content > p")
    private WebElement accountLoggedOffMsg;

    @FindBy(css="a.btn.btn-primary")
    private WebElement continueBtn;

    public String getLogoutPageTitle(){
        return getTitle();
    }

    public boolean isAccountLogoutHeaderExist(){
       return isDisplayed(accountLogoutHeader);
    }

    public boolean isAccountLoggedOffMsgExist(){
        return isDisplayed(accountLoggedOffMsg);
    }


    public void clickOnContinueBtn() throws InterruptedException{
        log.info("click on NewCustomerContinueBtn");
        click(continueBtn);
    }
}
