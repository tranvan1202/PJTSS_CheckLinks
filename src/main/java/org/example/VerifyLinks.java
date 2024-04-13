package org.example;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;
import java.net.URL;


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
                String newHrefButton1 = hrefButton.replace("//","");
                System.out.println(ind + " " + h3ProductName + " Buy now: " + newHrefButton1 + "\n");
            } else {
                String newHrefButton2 = hrefButton.replace("//","");
                System.out.println(ind + " " + h3ProductName + " Buy now: " + newHrefButton2 + "\n");
            }
        }
    }

    public static Elements verifyImagePath(WebDriver driver) throws InterruptedException {
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
        Elements assetPaths = doc.getAllElements();

        Elements withAttr = new Elements();
        for( Element element : assetPaths) {
            for( Attribute attribute : element.attributes() )
            {
                int ind = assetPaths.indexOf(element) + 1;
                boolean correctSiteCode = attribute.getValue().contains("/ph");
                //p6-qa thì: //stg-images.samsung.com
                //live stage thì: //images.samsung.com
                if( attribute.getValue().contains("images.samsung.com") && !correctSiteCode)
                {
                    withAttr.add(element);
                    System.out.println("No: " + ind + " Path: " + attribute);
                }
            }
        }
        return withAttr;
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
