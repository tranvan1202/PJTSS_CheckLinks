package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class htmlUnitTest {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://p6-qa.samsung.com/ph/offer/online/2024/galaxy-week/").get();
            Elements productCards = doc.select("div.feature-column-carousel__content");

            for(Element e : productCards) {
                int ind = productCards.indexOf(e) + 1;
                String h3ProductName = e.select("h3").first().text();
                String hrefButton = e.select("a").first().attr("href");
                System.out.println(ind + " " + h3ProductName + " " + hrefButton);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}