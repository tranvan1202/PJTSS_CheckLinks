package projectss.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StaticPage {
    public static WebDriver driver;
//    @FindBy(xpath = "//div[contains(concat('', @class, ''), 'feature-column-carousel__content')]")
//    public static WebElement elementProduct;
    public StaticPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }
    public static void verifyLinks(WebDriver driver) throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(concat('', @class, ''), 'feature-column-carousel__content')]"));

        // Iterating each link and checking the response status
        for (WebElement element : elements) {
            String titleContent = element.findElement(By.xpath(".//div[contains(concat('',@class, ''), 'feature-column-carousel__sub-title')]//h3")).getText();
            String url =element.findElement(By.xpath(".//div[contains(concat('',@class, ''), 'feature-column-carousel__button')]//a")).getAttribute("href");
            WebElement indicatorButton = element.findElement(By.xpath("//button[contains(concat('', @class, ''), 'indicator__item swiper-pagination-bullet indicator__item--not-hover')]"));
            if (url != null) {
                //String urlClass = element.getAttribute("class");
                int ind = elements.indexOf(element);
                String replacedUrl = url.replace("p6-qa","www");
                System.out.println(ind + " _ " + titleContent + " _ "  + replacedUrl);
                //checkBrokenLinks(url);
            }
        }
        driver.quit();
    }
    public static void clickIndicatorButton() {

    }
}
