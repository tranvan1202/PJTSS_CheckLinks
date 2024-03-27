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
    private static WebDriver driver;
    @FindBy(xpath = "//meta[@name='title']")
    private static WebElement eMetaTitle;
    @FindBy(xpath = "//link[@rel='canonical']")
    private static WebElement eLinkCan;
    @FindBy(xpath = "//meta[@name='keywords']")
    private static WebElement eMetaNameKeywords;
    @FindBy(xpath = "//meta[@name='description']")
    private static WebElement eMetaNameDescription;

    public MetaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static class MetaPageInfo {
        static String titleContent = driver.getTitle();
        static String metaTitle = eMetaTitle.getAttribute("content");
        static String linkCan = eLinkCan.getAttribute("href");

        static String metaNameKeywords = eMetaNameKeywords.getAttribute("content");
        static String metaNameDescription = eMetaNameDescription.getAttribute("content");
        static String metaNameDate = driver.findElement(By.xpath("//meta[@name='date']")).getAttribute("content");
        static String metaNameSiteCode = driver.findElement(By.xpath("//meta[@name='sitecode']")).getAttribute("content");

        static String metaNameTwitterCard = driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
        static String metaNameTwitterSite = driver.findElement(By.xpath("//meta[@name='twitter:site']")).getAttribute("content");
        static String metaNameTwitterCreator = driver.findElement(By.xpath("//meta[@name='twitter:creator']")).getAttribute("content");
        static String metaNameTwitterURL = driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
        static String metaNameTwitterTitle = driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
        static String metaNameTwitterDescription = driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
        static String metaNameTwitterImage = driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");

        static String metaPropertyOgURL = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
        static String metaPropertyOgImage = driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content");
        static String metaPropertyOgType = driver.findElement(By.xpath("//meta[@property='og:type']")).getAttribute("content");
        static String metaPropertyOgSiteName = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
        static String metaPropertyOgLocale = driver.findElement(By.xpath("//meta[@property='og:locale']")).getAttribute("content");
        static String metaPropertyOgTitle = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
        static String metaPropertyOgDescription = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");
        static String metaPropertyOgCountryName = driver.findElement(By.xpath("//meta[@property='og:country-name']")).getAttribute("content");

        static String metaPropertyName = driver.findElement(By.xpath("//meta[@property='name']")).getAttribute("content");
        static String metaPropertyImage = driver.findElement(By.xpath("//meta[@property='image']")).getAttribute("content");
        static String metaPropertyUrl = driver.findElement(By.xpath("//meta[@property='url']")).getAttribute("content");
        static String metaPropertyDescription = driver.findElement(By.xpath("//meta[@property='description']")).getAttribute("content");
        static String metaPropertyKeywords = driver.findElement(By.xpath("//meta[@property='keywords']")).getAttribute("content");

        public static String getTitleContent() {
            return titleContent;
        }

        public static String getMetaTitle() {
            return metaTitle;
        }

        public static String getLinkCan() {
            return linkCan;
        }

        public static String getMetaNameKeywords() {
            return metaNameKeywords;
        }

        public static String getMetaNameDescription() {
            return metaNameDescription;
        }

        public static String getMetaNameDate() {
            return metaNameDate;
        }

        public static String getMetaNameSiteCode() {
            return metaNameSiteCode;
        }

        public static String getMetaNameTwitterCard() {
            return metaNameTwitterCard;
        }

        public static String getMetaNameTwitterSite() {
            return metaNameTwitterSite;
        }

        public static String getMetaNameTwitterCreator() {
            return metaNameTwitterCreator;
        }

        public static String getMetaNameTwitterURL() {
            return metaNameTwitterURL;
        }

        public static String getMetaNameTwitterTitle() {
            return metaNameTwitterTitle;
        }

        public static String getMetaNameTwitterDescription() {
            return metaNameTwitterDescription;
        }

        public static String getMetaNameTwitterImage() {
            return metaNameTwitterImage;
        }

        public static String getMetaPropertyOgURL() {
            return metaPropertyOgURL;
        }

        public static String getMetaPropertyOgImage() {
            return metaPropertyOgImage;
        }

        public static String getMetaPropertyOgType() {
            return metaPropertyOgType;
        }

        public static String getMetaPropertyOgSiteName() {
            return metaPropertyOgSiteName;
        }

        public static String getMetaPropertyOgLocale() {
            return metaPropertyOgLocale;
        }

        public static String getMetaPropertyOgTitle() {
            return metaPropertyOgTitle;
        }

        public static String getMetaPropertyOgDescription() {
            return metaPropertyOgDescription;
        }

        public static String getMetaPropertyOgCountryName() {
            return metaPropertyOgCountryName;
        }

        public static String getMetaPropertyName() {
            return metaPropertyName;
        }

        public static String getMetaPropertyImage() {
            return metaPropertyImage;
        }

        public static String getMetaPropertyUrl() {
            return metaPropertyUrl;
        }

        public static String getMetaPropertyDescription() {
            return metaPropertyDescription;
        }

        public static String getMetaPropertyKeywords() {
            return metaPropertyKeywords;
        }
    }
    //Hàm đợi trang load xong rồi thao tác
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
