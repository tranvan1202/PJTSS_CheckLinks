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
            Elements pageTitle = doc.getElementsByTag("title");
            Elements linkCan = doc.getElementsByTag("link");
            Elements imgPath = doc.getElementsByTag("img");

            String sPageTitle = pageTitle.text();
            String sLinkCanName = linkCan.attr("rel");
            String sLinkCan = linkCan.attr("href");

//            System.out.println(sPageTitle);
//            System.out.println(sLinkCanName + " " + sLinkCan);

//            for(Element e : meta) {
//                int ind = meta.indexOf(e) + 1;
////                String h3ProductName = e.select("h3").first().text();
////                String hrefButton = e.select("a").first().attr("href");
////                System.out.println(ind + " " + h3ProductName + " " + hrefButton);
//                String name = e.attr("name");
//                String property = e.attr("property");
//                String content = e.attr("content");
//
//
//                boolean isNameEmpty = StringUtils.isEmpty(name);
//                if (isNameEmpty) {
//                    System.out.println(ind + " meta: " +  property +"|| content:  " + content);
//                } else {
//                    System.out.println(ind + " meta: " +  name +"|| content:  " + content);
//                }
//            }

            Elements imgPaths2 = doc.select("img");

            for (Element img:imgPaths2) {
                int ind = imgPaths2.indexOf(img) + 1;

                String attr = img.attr();
//                String dataDesktopSrc = img.attr("data-desktop-src") ;
//                String dataMobileSrc = img.attr("data-mobile-src") ;
//                String dataSrc = img.attr("data-src") ;
//                String altPC =img.attr("data-desktop-alt");
//                String altMO = img.attr("data-mobile-alt");
//                boolean isPCContainsSitecode = dataDesktopSrc.contains("ph");
//                boolean isMOContainsSitecode = dataMobileSrc.contains("ph");
//                boolean isGeneralContainsSitecode = dataSrc.contains("ph");
//                if (!isPCContainsSitecode || !isMOContainsSitecode ) {
//                    System.out.println(ind + " div: " +  img);
//                    System.out.println("dataSrc: " +  dataSrc);
//                    System.out.println("data-desktop-src: " +  dataDesktopSrc);
//                    System.out.println("data-mobile-src" +  dataMobileSrc);
//                    System.out.println(" pc Alt: " +  altPC);
//                    System.out.println(" mo Alt: " +  altMO);
//                    System.out.println("------------------------");
//                }
                System.out.println(ind  + " src:" + img + "________" );

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}