package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    static BrokenLinks brokenLinks = new BrokenLinks();
    public static void main(String[] args) throws InterruptedException {
        String QAlink = "https://p6-qa.samsung.com/sg/offer/mobile-deals/";
        System.setProperty("webdriver.chrome.driver", "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        VerifyLinks verifyLinks = new VerifyLinks();
        verifyLinks.verifyLinks(driver,QAlink);
    }
}