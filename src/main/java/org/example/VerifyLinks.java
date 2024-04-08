package org.example;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
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

    public static void verifyImagePath(WebDriver driver) throws InterruptedException {
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
        Elements imgPaths = doc.getElementsByTag("img");
        Elements imgPaths2 = doc.select("img");

        for(Element img : imgPaths) {
            int ind = imgPaths.indexOf(img) + 1;


            String dataDesktopSrc = img.attr("data-desktop-src") ;
            String dataMobileSrc = img.attr("data-mobile-src") ;
            String dataSrc = img.attr("data-src");
            String src = img.attr("src");
            String altPC =img.attr("data-desktop-alt");
            String altMO = img.attr("data-mobile-alt");
            boolean isDataDesktopSrcContainsSitecode = dataDesktopSrc.contains("ph");
            boolean isDataMobileSrcContainsSitecode = dataMobileSrc.contains("ph");
            boolean isDataSrcContainsSitecode = (dataSrc.contains("ph") && StringUtils.isEmpty(dataSrc));
            boolean isSrcContainsSitecode = (src.contains("ph") && StringUtils.isEmpty(src));

            if (!isDataDesktopSrcContainsSitecode || !isDataMobileSrcContainsSitecode) {
                System.out.println(ind + " img: " +  img);
//                System.out.println("dataSrc: " +  dataSrc);
//                System.out.println("data-desktop-src: " +  dataDesktopSrc);
//                System.out.println("data-mobile-src" +  dataMobileSrc);
//                System.out.println(" pc Alt: " +  altPC);
//                System.out.println(" mo Alt: " +  altMO);
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
