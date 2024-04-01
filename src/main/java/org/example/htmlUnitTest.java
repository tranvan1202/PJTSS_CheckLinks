package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class htmlUnitTest {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://samsung.com/ph/offer/online/2024/galaxy-week/").get();
            Elements paragraphs = doc.select("div.feature-column-carousel__sub-title");
            for(Element p : paragraphs) {
                System.out.println(p.text());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}