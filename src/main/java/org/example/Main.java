package org.example;
import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        LoginQA loginQA = new LoginQA();
        ArrayList<String> arrayURLs = new ArrayList<>();
        arrayURLs.addAll(Arrays.asList("https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/", "https://p6-qa.samsung.com/sg/offer/","https://p6-qa.samsung.com/vn/offer/","https://p6-qa.samsung.com/sg/tv-accessories/65-inch-disney-bezel-blue-z-65mickeyblue"));
        MetaInformation metaInformation = new MetaInformation();

        loginQA.login(driver);
        //JavascriptExecutor js1 = (JavascriptExecutor) driver;
        //js1.executeScript("window.open()");
        ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        for (int i = 0; i < arrayURLs.size(); i++) {
            System.out.println("---------------------------------------------------------");
            System.out.println((i+1) + ". " + "URL: " + arrayURLs.get(i));
            driver.navigate().to(arrayURLs.get(i));
            metaInformation.getMetaInformation(driver);
        }
        driver.quit();
    }
}