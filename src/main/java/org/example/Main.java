package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        //String QAlink = "https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/";
        System.setProperty("webdriver.chrome.driver", "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        LoginQA loginQA = new LoginQA();
        ArrayList<String> arrayURLs = new ArrayList<>();
        arrayURLs.addAll(Arrays.asList("https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/", "https://p6-qa.samsung.com/sg/offer/","https://p6-qa.samsung.com/vn/offer/"));

        MetaInformation metaInformation = new MetaInformation();

        loginQA.login(driver,arrayURLs);
        metaInformation.getMetaInformation(driver,arrayURLs);


//        VerifyLinks verifyLinks = new VerifyLinks();
//        verifyLinks.verifyLinks(driver);

        driver.close();
    }
}