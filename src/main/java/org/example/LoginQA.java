package org.example;

import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


public class LoginQA {
    public static void login(WebDriver driver, ArrayList arrURL) throws InterruptedException {
        for (int x = 0; x < arrURL.size(); x++) {
            //Giữ cửa sổ hiện tại
            driver.get(arrURL.get(x).toString());
            String winHandleBefore = driver.getWindowHandle();
            driver.manage().window().maximize();
            if (driver.findElements(By.id("login-box")).size() > 0) {
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
                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                //js1.executeScript("window.open()");
                ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
                driver.switchTo().window(browserTabs.get(1));
                driver.navigate().to(arrURL.get(x).toString());
            }
        }

    }
}
