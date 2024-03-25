package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class LoginQA {
    public static void login(WebDriver driver) throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        driver.manage().window().maximize();
        driver.get("https://wds.samsung.com/wds/sso/login/forwardLogin.do");
        //Find the Login button
        WebElement element = driver.findElement(By.xpath("//*[@id=\"loginContent\"]/div/div[1]/fieldset[1]/div/ul/table/tbody/tr/td[2]/a"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        String locatorQAAuthenLink = "/html/body/div/div[2]/div/div[1]/div[1]/div/dl[4]/dd[1]";

        Set<String> windowIds = driver.getWindowHandles();
        for (String windowId : windowIds) {
            driver.switchTo().window(windowId);
            if (driver.getTitle().equals("Sign In")) {
                //Nhập username
                WebElement username = driver.findElement(By.id("userNameInput"));
                username.sendKeys("van.binhtran@samsung.com");
                username.sendKeys(Keys.TAB);
                wait.until((ExpectedConditions.invisibilityOf(username)));
                driver.switchTo().window(winHandleBefore);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otpCode")));
        WebElement otpInput = driver.findElement(By.id("otpCode"));
        otpInput.sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorQAAuthenLink)));

        //Click nút QA
        driver.findElement(By.xpath(locatorQAAuthenLink)).click();
        Thread.sleep(2000);

    }
}
