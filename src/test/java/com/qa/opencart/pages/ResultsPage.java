package com.qa.opencart.pages;

import com.qa.opencart.utilities.TimeUtils;
import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends WebDriverUtils{
   private Logger log =LogManager.getLogger(ResultsPage.class);

    public ResultsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="#content > h1")
    private WebElement searchHeader;

    @FindBy(css="body div[id='account-account'] ul[class='breadcrumb'] li:nth-child(2) a:nth-child(1)")
    private WebElement accountBreadCrumb;

    @FindBy(css="div[class*='product-layout product-grid']")
    private List<WebElement> searchproductList;

    @FindBy(xpath="//div[@id='product-search']/ul[@class='breadcrumb']/li[2]/a")
    private WebElement searchBreadCrumb;

    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;
    @FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(.,'My Account')]")
    private WebElement myAccountMenu;
    @FindBy(xpath = "(//a[contains(.,'My Account')])[2]")
    private WebElement myAccountOption;
    public String getResultsPageTitle(){
        return getTitle();
    }

    public boolean isSearchHeaderExist(){
       return isDisplayed(searchHeader);
    }
    public boolean isSearchBreadcrumbExist(){
        return isDisplayed(searchBreadCrumb);
    }

    public void navigateToMyAccountPage() throws InterruptedException{
        try{
                log.info("click on myaccountMenu link");
                click(myAccountMenu);
            log.info("click on myaccount option link");
            click(myAccountOption);
        }catch(NoSuchElementException ex){
            log.info("not able to navigate to my account page");
            ex.printStackTrace();
        }
    }

    public void selectProduct(String productName) throws InterruptedException{
        log.info("selecting the desired product :"+productName);
        click(getElement(By.linkText(productName)));
        TimeUtils.mediumWait();

    }
    public void clickBreadCrumbHomeIcon() throws InterruptedException{
        log.info("click on home icon in breadcrumb");
        click(homeIcon);
    }

public int getSearchProductListSize(){
        return searchproductList.size();
}
}
