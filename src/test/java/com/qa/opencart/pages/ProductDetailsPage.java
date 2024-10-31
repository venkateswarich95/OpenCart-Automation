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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailsPage extends WebDriverUtils{
    private Logger log=LogManager.getLogger(ProductDetailsPage.class);

    public ProductDetailsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@id='content']/div[1]/div[2]//h1")
    private WebElement productHeader;

    @FindBy(css="a.thumbnail")
    private List<WebElement> productImageList;

    @FindBy(xpath="(//div[@id='content']/div/div[2]//ul[@class='list-unstyled'])[position()=1]/li")
    private List<WebElement> productMetaDataList;
    @FindBy(xpath="(//div[@id='content']/div/div[2]//ul[@class='list-unstyled'])[position()=2]/li")
    private List<WebElement> productPriceList;
    @FindBy(id="input-quantity")
    private WebElement quantityEditbox;
    @FindBy(id="button-cart")
    private WebElement addToCartButton;
    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;

    public String getProductDetailsPageTitle(){
        return getTitle();
    }

    public String getProductName() throws InterruptedException{
        return getText(productHeader);
    }

    public int getProductImageCount(){
        return productImageList.size();
    }

    private Map<String,String> productMap;

    public void getProductMetaData(){
        log.info("Product mete data count is:"+productMetaDataList.size());
        //iterate the product metadata list
        for(WebElement pmd:productMetaDataList){
            String metaText = pmd.getText();//Brand: Apple
            log.info("split the metatext based on :");
           String[] metaDataArray =  metaText.split(":");
           log.info("fetch meta key and value");
           String metKey = metaDataArray[0].trim();
           String metaValue = metaDataArray[1].trim();
           log.info("Store meta key and value in the map");
            productMap.put(metKey,metaValue);
        }
    }

    public void getProductPriceData(){
        log.info("Product meta price count is:"+productPriceList.size());
        String price = productPriceList.get(0).getText().trim();
        String extTaxPrice = productPriceList.get(1).getText().trim();
        log.info("Store the price values in the map");
        productMap.put("actualPrice",price);
        productMap.put("actualTaxPrice",extTaxPrice);
    }

    public Map<String,String> getProductInformation(){
        productMap = new HashMap<String,String>();
        getProductMetaData();
        getProductPriceData();
        return productMap;
    }

    public void clickAddToCartButton() throws InterruptedException{
        click(addToCartButton);
    }
    public void setQuantity(String quantity) throws InterruptedException{
        log.info("entering the quantity in editbox");
        type(quantityEditbox,quantity);
    }
    public void clickBreadCrumbHomeIcon() throws InterruptedException{
        log.info("click on home icon in breadcrumb");
        click(homeIcon);
    }

}