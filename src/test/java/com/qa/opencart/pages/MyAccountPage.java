package com.qa.opencart.pages;

import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MyAccountPage extends WebDriverUtils{
   private Logger log =LogManager.getLogger(MyAccountPage.class);

    public MyAccountPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[normalize-space()='My Account']")
    private WebElement myAccountMenu;
    @FindBy(css="input[name='search']")
    private WebElement searchEditbox;

    @FindBy(css="button[class='btn btn-default btn-lg']")
    private WebElement searchTorchIcon;

    @FindBy(linkText="Logout")
    private WebElement logoutLink;
    @FindBy(css="body div[id='account-account'] ul[class='breadcrumb'] li:nth-child(2) a:nth-child(1)")
    private WebElement paccountBreadCrumb;

    @FindBy(xpath="//div[@id='top-links']/ul/li[2]/ul/li")
    private List<WebElement> myAccountMenuOptionList;

    @FindBy(xpath="//div[@id='content']/h2")
    private List<WebElement> myAccountHeadersList;

    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;

    @FindBy(xpath="//div[@id='content']/ul[1]/li")
    private List<WebElement> myAccountHeaderElementsList;
    @FindBy(xpath="//div[@id='content']/ul[2]/li")
    private List<WebElement> myOrdersHeaderElementsList;
    @FindBy(xpath="//div[@id='content']/ul[3]/li")
    private List<WebElement> myAffiliateAccountHeaderElementsList;

    @FindBy(xpath="//div[@id='content']/ul[4]/li")
    private List<WebElement> newsletterHeaderElementsList;


    public String getMyAccountPageTitle(){
        return getTitle();
    }

    public String getMyAccountPageUrl(){
        return getURL();
    }
    public boolean issearchEditboxExist(){
       return isDisplayed(searchEditbox);
    }

    public void clickMyAccountMenuLink() throws InterruptedException{
        log.info("click on my account menu link");
        click(myAccountMenu);
    }

    public boolean isLogoutExists() throws InterruptedException{
        clickMyAccountMenuLink();
        return isDisplayed(logoutLink);
    }
    public void clickOnLogoutLink() throws InterruptedException{
        try{
            if(isLogoutExists()){
                log.info("click on Logout link");
                click(logoutLink);
            }
        }catch(NoSuchElementException ex){
            log.info("logout link is not visible");
            ex.printStackTrace();
        }
    }

    public List<String> getMyAccountMenuOptionList(){
        List<String>myAccountMenuOptionsTextList = new ArrayList<>();
        try{
            clickMyAccountMenuLink();
            for (WebElement e : myAccountMenuOptionList) {
                String text = e.getText();
                myAccountMenuOptionsTextList.add(text);
            }

        }catch(InterruptedException e){
           log.info("options list is not able to fetch:");
           e.printStackTrace();
        }
        return myAccountMenuOptionsTextList;
    }

    public List<String> getMyAccountHeaderOptionsList(){
        List<String>myAccountHeaderTextList = new ArrayList<>();
        try{
            clickMyAccountMenuLink();
            for (WebElement e : myAccountHeaderElementsList) {
                String text = e.getText();
                myAccountHeaderTextList.add(text);
            }

        }catch(InterruptedException e){
            log.info("My account header element list is not able to fetch:");
            e.printStackTrace();
        }
        return myAccountHeaderTextList;
    }

    public List<String> getOrdersHeaderList(){
        List<String>ordersHeaderTextList = new ArrayList<>();
        try{
            clickMyAccountMenuLink();
            for (WebElement e : myOrdersHeaderElementsList) {
                String text = e.getText();
                ordersHeaderTextList.add(text);
            }

        }catch(InterruptedException e){
            log.info("My Orders header element list is not able to fetch:");
            e.printStackTrace();
        }
        return ordersHeaderTextList;
    }

    public List<String> getMyAffiliateAccountHeaderList(){
        List<String>myAffiliateAccountHeaderTextList = new ArrayList<>();
        try{
            clickMyAccountMenuLink();
            for (WebElement e : myOrdersHeaderElementsList) {
                String text = e.getText();
                myAffiliateAccountHeaderTextList.add(text);
            }

        }catch(InterruptedException e){
            log.info("My AffiliateAccount header element list is not able to fetch:");
            e.printStackTrace();
        }
        return myAffiliateAccountHeaderTextList;
    }

    public List<String> getNewsletterHeaderList(){
        List<String>newsletterHeaderTextList = new ArrayList<>();
        try{
            clickMyAccountMenuLink();
            for (WebElement e : myOrdersHeaderElementsList) {
                String text = e.getText();
                newsletterHeaderTextList.add(text);
            }

        }catch(InterruptedException e){
            log.info("newsletter header element list is not able to fetch:");
            e.printStackTrace();
        }
        return newsletterHeaderTextList;
    }

    public ResultsPage doProductSearch(String productName) throws InterruptedException{
        log.info("searching for the product :"+productName);
       pressEscapeKey();
        if(issearchEditboxExist()){
            log.info("type product in search field");
            type(searchEditbox,productName);
            log.info("click on search torch icon");
            click(searchTorchIcon);
        }
return new ResultsPage(driver);
    }

    public void clickBreadCrumbHomeIcon() throws InterruptedException{
        log.info("click on home icon in breadcrumb");
        click(homeIcon);
    }
    public void pressEscapeKey(){
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ESCAPE).perform();
    }

}
