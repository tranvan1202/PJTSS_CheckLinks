package projectss.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import static org.testng.Assert.assertNotEquals;

public class MetaPage {
    private WebDriver driver;
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
        static String titleContent,metaTitle,linkCan,
                metaNameKeywords,metaNameDescription,metaNameDate,metaNameSiteCode,
                metaNameTwitterCard,metaNameTwitterSite,metaNameTwitterCreator,metaNameTwitterURL,metaNameTwitterTitle,metaNameTwitterDescription,metaNameTwitterImage,
                metaPropertyOgURL,metaPropertyOgImage,metaPropertyOgType,metaPropertyOgSiteName,metaPropertyOgLocale,metaPropertyOgTitle,metaPropertyOgDescription, metaPropertyOgCountryName,
                metaPropertyName,metaPropertyImage,metaPropertyUrl,metaPropertyDescription,metaPropertyKeywords;

    }
    public void getMetaInfo() {
        //Get Document object after parsing the html from given url.
        MetaPageInfo.titleContent = driver.getTitle();
        MetaPageInfo.metaTitle = eMetaTitle.getAttribute("content");
        MetaPageInfo.linkCan = eLinkCan.getAttribute("href");

        MetaPageInfo.metaNameKeywords = eMetaNameKeywords.getAttribute("content");
        MetaPageInfo.metaNameDescription = eMetaNameDescription.getAttribute("content");
        MetaPageInfo.metaNameDate = driver.findElement(By.xpath("//meta[@name='date']")).getAttribute("content");
        MetaPageInfo.metaNameSiteCode = driver.findElement(By.xpath("//meta[@name='sitecode']")).getAttribute("content");

        MetaPageInfo.metaNameTwitterCard = driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterSite = driver.findElement(By.xpath("//meta[@name='twitter:site']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterCreator = driver.findElement(By.xpath("//meta[@name='twitter:creator']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterURL = driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterTitle = driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterDescription = driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
        MetaPageInfo.metaNameTwitterImage = driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");

        MetaPageInfo.metaPropertyOgURL = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgImage = driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgType = driver.findElement(By.xpath("//meta[@property='og:type']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgSiteName = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgLocale = driver.findElement(By.xpath("//meta[@property='og:locale']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgTitle = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgDescription = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");
        MetaPageInfo.metaPropertyOgCountryName = driver.findElement(By.xpath("//meta[@property='og:country-name']")).getAttribute("content");

        MetaPageInfo.metaPropertyName = driver.findElement(By.xpath("//meta[@property='name']")).getAttribute("content");
        MetaPageInfo.metaPropertyImage = driver.findElement(By.xpath("//meta[@property='image']")).getAttribute("content");
        MetaPageInfo.metaPropertyUrl = driver.findElement(By.xpath("//meta[@property='url']")).getAttribute("content");
        MetaPageInfo.metaPropertyDescription = driver.findElement(By.xpath("//meta[@property='description']")).getAttribute("content");
        MetaPageInfo.metaPropertyKeywords = driver.findElement(By.xpath("//meta[@property='keywords']")).getAttribute("content");
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
