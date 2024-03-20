package org.example;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    static BrokenLinks brokenLinks = new BrokenLinks();
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navigate to BStackDemo Website
        driver.get("https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/");

        String locatorLoginForm = "login-box";
        Boolean isLoginFormAppear = driver.findElements(By.id(locatorLoginForm)).size() > 0;
        if (isLoginFormAppear) {
            //Giữ cửa sổ hiện tại
            String winHandleBefore = driver.getWindowHandle();
            driver.manage().window().maximize();

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.open()");
            ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
            driver.switchTo().window(browserTabs.get(1));
            driver.get("https://wds.samsung.com/wds/sso/login/forwardLogin.do");

            //Find the Login button
            WebElement element = driver.findElement(By.xpath("//*[@id=\"loginContent\"]/div/div[1]/fieldset[1]/div/ul/table/tbody/tr/td[2]/a"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            WebDriverWait wait = new WebDriverWait(driver, 120);
            String locatorQAAuthenLink = "/html/body/div/div[2]/div/div[1]/div[1]/div/dl[4]/dd[1]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorQAAuthenLink)));
            //Click nút QA
            driver.findElement(By.xpath(locatorQAAuthenLink)).click();
            Thread.sleep(2000);

            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("submit-button"));

            //Đóng tab hiện tại
            driver.close();
            //Chuyển sang tab đã giữ
            driver.switchTo().window(winHandleBefore);
            driver.navigate().refresh();
        }

        // Finding all the available links on webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Iterating each link and checking the response status
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            brokenLinks.verifyLink(url);
        }
        driver.quit();
    }
}