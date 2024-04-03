package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class htmlUnitTest {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://samsung.com/ph/offer/online/2024/galaxy-week/").get();
//            Elements productCards = doc.select("div.feature-column-carousel__content");
            Elements meta = doc.getElementsByTag("meta");

            for(Element e : meta) {
                int ind = meta.indexOf(e) + 1;
//                String h3ProductName = e.select("h3").first().text();
//                String hrefButton = e.select("a").first().attr("href");
//                System.out.println(ind + " " + h3ProductName + " " + hrefButton);
                String name = e.attr("name");
                String property = e.attr("property");
                String content = e.attr("content");

                boolean isNameEmpty = StringUtils.isEmpty(name);
                if (isNameEmpty) {
                    System.out.println(ind + " meta: " +  property +"|| content:  " + content);
                } else {
                    System.out.println(ind + " meta: " +  name +"|| content:  " + content);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}