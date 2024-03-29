package projectss.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class MetaPage {
    public static WebDriver driver;
    @FindBy(xpath = "//meta[@name='title']")
    public static WebElement metaTitle;
    @FindBy(xpath = "//link[@rel='canonical']")
    public static WebElement linkCan;
    /////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//meta[@name='keywords']")
    public static WebElement metaNameKeywords;
    @FindBy(xpath = "//meta[@name='description']")
    public static WebElement metaNameDescription;
    @FindBy(xpath = "//meta[@name='date']")
    public static WebElement metaNameDate;
    @FindBy(xpath = "//meta[@name='sitecode']")
    public static WebElement metaNameSiteCode;
    /////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//meta[@name='twitter:card']")
    public static WebElement metaNameTwitterCard;
    @FindBy(xpath = "//meta[@name='twitter:site']")
    public static WebElement metaNameTwitterSite;
    @FindBy(xpath = "//meta[@name='twitter:creator']")
    public static WebElement metaNameTwitterCreator;
    @FindBy(xpath = "//meta[@name='twitter:url']")
    public static WebElement metaNameTwitterURL;
    @FindBy(xpath = "//meta[@name='twitter:title']")
    public static WebElement metaNameTwitterTitle;
    @FindBy(xpath = "//meta[@name='twitter:description']")
    public static WebElement metaNameTwitterDescription;
    @FindBy(xpath = "//meta[@name='twitter:image']")
    public static WebElement metaNameTwitterImage;
    /////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//meta[@property='og:url']")
    public static WebElement metaPropertyOgURL;
    @FindBy(xpath = "//meta[@property='og:image']")
    public static WebElement metaPropertyOgImage;
    @FindBy(xpath = "//meta[@property='og:type']")
    public static WebElement metaPropertyOgType;
    @FindBy(xpath = "//meta[@property='og:site_name']")
    public static WebElement metaPropertyOgSiteName;
    @FindBy(xpath = "//meta[@property='og:locale']")
    public static WebElement metaPropertyOgLocale;
    @FindBy(xpath = "//meta[@property='og:title']")
    public static WebElement metaPropertyOgTitle;
    @FindBy(xpath = "//meta[@property='og:description']")
    public static WebElement metaPropertyOgDescription;
    @FindBy(xpath = "//meta[@property='og:country-name']")
    public static WebElement metaPropertyOgCountryName;
    /////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//meta[@property='name']")
    public static WebElement metaPropertyName;
    @FindBy(xpath = "//meta[@property='image']")
    public static WebElement metaPropertyImage;
    @FindBy(xpath = "//meta[@property='url']")
    public static WebElement metaPropertyUrl;
    @FindBy(xpath = "//meta[@property='description']")
    public static WebElement metaPropertyDescription;
    @FindBy(xpath = "//meta[@property='keywords']")
    public static WebElement metaPropertyKeywords;

    /////////////////////////////////////////////////////////////////////////////////
    public MetaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static class MetaInfo {
        //public static String sPageTitle = driver.getTitle();
//        public static String sMetaTitle = metaTitle.getAttribute("content");
//        public static String sLinkCan = linkCan.getAttribute("href");
//        public static String sMetaNameKeywords = metaNameKeywords.getAttribute("content");
//        public static String sMetaNameDescription = metaNameDescription.getAttribute("content");
//        public static String sMetaNameDate = driver.findElement(By.xpath("//meta[@name='date']")).getAttribute("content");
//        public static String sMetaNameSiteCode = driver.findElement(By.xpath("//meta[@name='sitecode']")).getAttribute("content");
//        public static String sMetaNameTwitterCard = driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
//        public static String sMetaNameTwitterSite = driver.findElement(By.xpath("//meta[@name='twitter:site']")).getAttribute("content");
//        public static String sMetaNameTwitterCreator = driver.findElement(By.xpath("//meta[@name='twitter:creator']")).getAttribute("content");
//        public static String sMetaNameTwitterURL = driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
//        public static String sMetaNameTwitterTitle = driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
//        public static String sMetaNameTwitterDescription = driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
//        public static String sMetaNameTwitterImage = driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");
//
//        public static String sMetaPropertyOgURL = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
//        public static String sMetaPropertyOgImage = driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content");
//        public static String sMetaPropertyOgType = driver.findElement(By.xpath("//meta[@property='og:type']")).getAttribute("content");
//        public static String sMetaPropertyOgSiteName = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
//        public static String sMetaPropertyOgLocale = driver.findElement(By.xpath("//meta[@property='og:locale']")).getAttribute("content");
//        public static String sMetaPropertyOgTitle = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
//        public static String sMetaPropertyOgDescription = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");
//        public static String sMetaPropertyOgCountryName = driver.findElement(By.xpath("//meta[@property='og:country-name']")).getAttribute("content");
//
//        public static String sMetaPropertyName = driver.findElement(By.xpath("//meta[@property='name']")).getAttribute("content");
//        public static String sMetaPropertyImage = driver.findElement(By.xpath("//meta[@property='image']")).getAttribute("content");
//        public static String sMetaPropertyUrl = driver.findElement(By.xpath("//meta[@property='url']")).getAttribute("content");
//        public static String sMetaPropertyDescription = driver.findElement(By.xpath("//meta[@property='description']")).getAttribute("content");
//        public static String sMetaPropertyKeywords = driver.findElement(By.xpath("//meta[@property='keywords']")).getAttribute("content");
    }
}
