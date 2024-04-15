package org.example;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        //arrayURLs.addAll(Arrays.asList("https://images.samsung.com/is/image/samsung/assets/sg/offer/c-audit-august23/computing-MO1.png?$LazyLoad_Home_PNG$"));
        //MetaInformation metaInformation = new MetaInformation();

        //loginQA.login(driver);
        //JavascriptExecutor js1 = (JavascriptExecutor) driver;
        //js1.executeScript("window.open()");
        //ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        //driver.switchTo().window(browserTabs.get(1));


//        for (int i = 0; i < arrayURLs.size(); i++) {
//            System.out.println("---------------------------------------------------------");
//            System.out.println((i+1) + ". " + "URL: " + arrayURLs.get(i));
//            driver.navigate().to(arrayURLs.get(i));
//            //metaInformation.getMetaInformation(driver);
//            //VerifyLinks.verifyLinks(driver);
//            VerifyLinks.verifyImagePath(driver);
//        }

        VerifyLinks.checkBrokenLinks("https://images.samsung.com/is/image/samsung/assets/sg/offer/c-audit-august23/computing-MO1.png?$LazyLoad_Home_PNG$");
        driver.quit();
    }
}