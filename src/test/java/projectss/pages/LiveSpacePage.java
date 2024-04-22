package projectss.pages;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public class LiveSpacePage {
    public static WebDriver driver;
    public static String xpathLoadMoreButton = "//button[contains(@class, 'rhq-btn rhq-btn--black rhq-btn--arrow')]";
    @FindBy(xpath = "//div[contains(@class, 'rhq-time-slot__row')]")
    public static WebElement timeSlotCards;
    @FindBy(xpath = "//h2[contains(@class, 'rhq-title rhq-title--5')]")
    public static WebElement timeSlotTitles;
    @FindBy(xpath = "//h3[contains(@class, 'rhq-time-slot__location')]")
    public static WebElement timeSlotLocations;
    @FindBy(xpath = "//p[contains(@class, 'rhq-time-slot__daytime'')]")
    public static WebElement timeSlotDayTimes;
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

    public LiveSpacePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void getTimeSlotInformation(String url) {
        WebElement loadMoreButton = driver.findElement(By.xpath(xpathLoadMoreButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLoadMoreButton)));
        if(!driver.findElements(By.xpath(xpathLoadMoreButton)).isEmpty()) {
            loadMoreButton.click();
        }

    }
}
