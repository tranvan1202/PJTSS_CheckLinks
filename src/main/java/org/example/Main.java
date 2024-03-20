package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String QAlink = "https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/";
        System.setProperty("webdriver.chrome.driver", "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        VerifyLinks verifyLinks = new VerifyLinks();
        verifyLinks.verifyLinks(driver,QAlink);
    }
}