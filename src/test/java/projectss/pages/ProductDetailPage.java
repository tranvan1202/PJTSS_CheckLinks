package projectss.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import projectss.testcases.ProductDetailTestNew;

import java.util.List;

public class ProductDetailPage {
    public static WebDriver driver;
//    @FindBy(xpath = "//strong//span[contains(concat('', @class, ''), 'badge-icon')]")
//    public static WebElement elementBadge;

//    @FindBy(xpath = "//a[contains(text(),'Xem catalogue')]")
//    public static WebElement elementCTAButton;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public static By getByXpathSKUCode(WebDriver driver){
        return By.xpath("//span[@class='pd-info__sku-code");
    }
    public static String getElementBadgeText(WebDriver driver){
        return driver.findElement(By.xpath("//strong//span[contains(concat('', @class, ''), 'badge-icon')]")).getText();
    }
    public static String getXemCatalogueButton(WebDriver driver){
        return driver.findElement(By.xpath("//a[contains(text(),'Xem catalogue')]")).getText();
    }
    @DataProvider(name="listSKUCodes")
    public List<String> getSKUCodes(ITestContext context) {
        return ProductDetailTestNew.listSKUCodes;
    }


}
