package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import com.google.common.collect.Lists;

public class VerifyLinks {
    public static void verifyLinks(WebDriver driver) throws InterruptedException {
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
        Elements productCards = doc.select("div.feature-column-carousel__content");
        for(Element e : productCards) {
            int ind = productCards.indexOf(e) + 1;
            String h3ProductName = e.select("h3").first().text();
            String hrefButton = e.select("a").first().attr("href");
            if (!hrefButton.contains("www")) {
                hrefButton.replaceFirst("/","www.samsung.com");
                System.out.println(ind + " " + h3ProductName + " Buy now: " + hrefButton + "\n");
            } else {
                hrefButton.replaceFirst("//","www");
                System.out.println(ind + " " + h3ProductName + " Buy now: " + hrefButton + "\n");
            }
        }
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
