package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
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
        //List<WebElement> links = driver.findElements(By.tagName("a"));
        List<WebElement> links = driver.findElements(By.xpath("//*[class='feature-column-carousel__button']//a"));

        // Iterating each link and checking the response status
        for (WebElement link : links) {
            String url = link.getAttribute("href");
//            String urlClass = link.getAttribute("class");
//            int ind = links.indexOf(link);
//            System.out.println(ind + "_ " + urlClass + "_ " + url);
            if (url != null) {
                String urlClass = link.getAttribute("class");
                int ind = links.indexOf(link);
                String replacedUrl = url.replace("p6-qa","www");
                System.out.println(ind + "_ " + urlClass + "_ " + replacedUrl);
                //brokenLinks.checkBrokenLinks(url);
            }
        }
        driver.quit();
    }
}
