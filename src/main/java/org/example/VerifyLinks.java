package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

public class VerifyLinks {
    public void verifyLinks(WebDriver driver,String QAlink) throws InterruptedException {
        LoginQA loginQA = new LoginQA();
        BrokenLinks brokenLinks = new BrokenLinks();
        //QAlink = "https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/";

        // Navigate to the Website
        driver.get(QAlink);
        String locatorLoginForm = "login-box";
        Boolean isLoginFormAppear = driver.findElements(By.id(locatorLoginForm)).size() > 0;
        if (isLoginFormAppear) {
            loginQA.login(driver);
        }
        //Giữ cửa sổ hiện tại
        String winHandleBefore = driver.getWindowHandle();
        driver.manage().window().maximize();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.open()");
        ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(2));
        driver.get(QAlink);

        // Finding all the available links on webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Iterating each link and checking the response status
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            String replacedUrl = url.replaceAll("p6-qa","www");
            if (url.contains("p6-qa")) {
                brokenLinks.checkBrokenLinks(replacedUrl);
            }
            brokenLinks.checkBrokenLinks(url);
        }
        driver.quit();
    }
}
