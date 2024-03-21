package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import com.google.common.collect.Lists;

public class VerifyLinks {
    public void verifyLinks(WebDriver driver) throws InterruptedException {
        // Finding all the available links on webpage
        //List<WebElement> links = driver.findElements(By.tagName("a"));
        //Lấy content
        //List<WebElement> contents = driver.findElements(By.xpath("//div[contains(concat('', @class, ''), 'feature-column-carousel__text-wrap')]/div/h3"));
        //Lấy các nút Buy Now
        //List<WebElement> links = driver.findElements(By.xpath("//div[contains(concat('', @class, ''), 'feature-column-carousel__button')]/a"));
        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(concat('', @class, ''), 'feature-column-carousel__content')]"));

        // Iterating each link and checking the response status
        for (WebElement element : elements) {
            String titleContent = element.findElement(By.xpath(".//div[contains(concat('',@class, ''), 'feature-column-carousel__sub-title')]//h3")).getText();
            String url =element.findElement(By.xpath(".//div[contains(concat('',@class, ''), 'feature-column-carousel__button')]//a")).getAttribute("href");
            if (url != null) {
                //String urlClass = element.getAttribute("class");
                int ind = elements.indexOf(element);
                String replacedUrl = url.replace("p6-qa","www");
                System.out.println(ind + " _ " + titleContent + " _ "  + replacedUrl);
                //checkBrokenLinks(url);
            }
        }
        driver.quit();
    }

    public static void checkBrokenLinks(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }
}
