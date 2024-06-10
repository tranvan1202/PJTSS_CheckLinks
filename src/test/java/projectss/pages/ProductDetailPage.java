package projectss.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    public static String getElementBadgeText(WebDriver driver){
        return driver.findElement(By.xpath("//strong//span[contains(concat('', @class, ''), 'badge-icon')]")).getText();
    }
    public static String getXemCatalogueButton(WebDriver driver){
        return driver.findElement(By.xpath("//a[contains(text(),'Xem catalogue')]")).getText();
    }

}
