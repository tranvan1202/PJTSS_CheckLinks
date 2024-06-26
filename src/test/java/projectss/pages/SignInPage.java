package projectss.pages;

import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import projectss.base.BaseSetup;

import java.util.Properties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import static java.lang.Thread.sleep;

public class SignInPage extends projectss.pages.CommonPage {
    //Khai báo driver cục bộ
    private WebDriver driver;
    public static Properties prop;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div[1]/div/dl[4]/dd[1]")
    private static WebElement submitQALink;
//    @FindBy(xpath = "//meta[@name='mswebdialog-title']")
//    private static WebElement elementHeaderPageText;
//    @FindBy(xpath = "//*[@id=\"loginContent\"]/div/div[1]/fieldset[1]/div/ul/table/tbody/tr/td[2]/a")
//    private static WebElement loginButton;
//    @FindBy(id = "userNameInput")
//    private static WebElement userNameInput;
//    @FindBy(id = "otpCode")
//    private static WebElement otpCode;
//    private static String cookiesCurrent;
    //private static By loginButton = By.xpath("//*[@id=\"loginContent\"]/div/div[1]/fieldset[1]/div/ul/table/tbody/tr/td[2]/a");
    //private static By emailInput = By.id("userNameInput");
    //private static By otpCode = By.id("otpCode");
    //private static By submitQALink = By.xpath("/html/body/div/div[2]/div/div[1]/div[1]/div/dl[4]/dd[1]");

    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong
    // class này đọc
    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong
    // Và khởi tạo initElements
    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getSignInPageTitle() {
        String pageTitle = driver.getTitle();
        return pageTitle;
    }
    public boolean verifySignInPageTitle() {
        String expectedTitle = "Home Electronics | Home Appliances | Mobile | Computing |";
        return getSignInPageTitle().equals(expectedTitle);
    }
    public ProductDetailPage loginAndGoToPDP() throws InterruptedException{
        loginThroughSession();
        return new ProductDetailPage(driver);
    }
    public void loginThroughSession() throws InterruptedException {
        //String winHandleBefore = driver.getWindowHandle();
        // Add the cookie into current browser context (cookiesCurrent)
        //System.out.println("Get Current Cookies: " + BaseSetup.prop.getProperty("cook"));
        driver.navigate().to("https://wds.samsung.com/wds/sso/login/forwardLogin.do");
        driver.manage().addCookie(new Cookie("JSESSIONID", BaseSetup.prop.getProperty("session")));
        sleep(3);
        driver.navigate().to("https://wds.samsung.com/wds/sso/login/ssoLoginSuccess.do");

        //Click nút QA
        clickSubmitQALink();
        sleep(2000);
        ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
    }

    public void clickSubmitQALink() {
        WebElement submitQA = submitQALink;
        if (submitQA.isDisplayed()) {
            submitQA.click();
        }
    }
//    public static void signInManually() throws InterruptedException {
//        String winHandleBefore = driver.getWindowHandle();
//
//        //Find the Login button
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", loginButton);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//
//        Set<String> windowIds = driver.getWindowHandles();
//        for (String windowId : windowIds) {
//            driver.switchTo().window(windowId);
//            if (driver.getTitle().equals("Sign In")) {
//                //Nhập username
//                enterEmail(BaseSetup.prop.getProperty("username"));
//                wait.until((ExpectedConditions.invisibilityOf(userNameInput)));
//                driver.switchTo().window(winHandleBefore);
//            }
//        }
//        wait.until(ExpectedConditions.visibilityOf(otpCode));
//        if (otpCode.isDisplayed()) {
//            try {
//                otpCode.sendKeys("");
//                wait.until(ExpectedConditions.visibilityOf(submitQALink));
//            } catch (UnhandledAlertException f) {
//                wait.until(ExpectedConditions.alertIsPresent());
//                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
//                System.out.println("Alert data: " + alertText);
//                alert.accept();
//            }
//        }
//        //Click nút QA
//        clickSubmitQALink();
//        sleep(2000);
//        ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
//        driver.switchTo().window(browserTabs.get(1));
//    }
//
//    public static void enterEmail(String email) {
//        WebElement emailTxtBox = userNameInput;
//        if (emailTxtBox.isDisplayed()) {
//            emailTxtBox.sendKeys(email);
//            emailTxtBox.sendKeys(Keys.TAB);
//        }
//    }
//
}
