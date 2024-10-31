package com.qa.opencart.pages;

import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends WebDriverUtils{
   private Logger log =LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="logo")
    private WebElement openCartLogo;
    @FindBy(css="div[class*='product-layout ']")
    private List<WebElement> featuredProductList;

    @FindBy(xpath="//span[normalize-space()='My Account']")
    private WebElement myAccountMenu;

    @FindBy(linkText="Register")
    private WebElement registerLink;
    @FindBy(linkText="Login")
    private WebElement loginLink;

    @FindBy(css="img.img-responsive")
    private WebElement imageCatalog;
    @FindBy(name="search")
    private WebElement searchEditbox;
    public String getHomePageTitle(){
        return getTitle();
    }

    public boolean isImageCatlogExist(){
       return isDisplayed(imageCatalog);
    }
    public boolean issearchEditboxExist(){
        return isDisplayed(searchEditbox);
    }
    public boolean isOpenCartLogoExist(){
        return isDisplayed(openCartLogo);
    }

    public void clickMyAccountMenu() throws InterruptedException{
        click(myAccountMenu);
    }

    public void navigateToRegisterPage() throws InterruptedException{
        clickMyAccountMenu();
        click(registerLink);
    }

    public void navigateToLoginPage() throws InterruptedException{
        clickMyAccountMenu();
        click(loginLink);
    }

    public int getFeaturedSectionCardsCount(){
        return featuredProductList.size();
    }
}
