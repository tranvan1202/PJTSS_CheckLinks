package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

public class htmlUnitTest {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.samsung.com/uk/offer/").get();
//            Elements productCards = doc.select("div.feature-column-carousel__content");
            Elements meta = doc.getElementsByTag("meta");
            Elements pageTitle = doc.getElementsByTag("title");
            Elements eLink = doc.select("link[rel='canonical']");
            Elements imgPath = doc.getElementsByTag("img");
            String sLinkCan = eLink.attr("href");
            String sPageTitle = pageTitle.text();
            System.out.println("<title>" + sPageTitle + "</title> ");
            System.out.println("<link rel= 'canonical' href='" + sLinkCan + "'/>");
            for(Element e : meta) {
                int ind = meta.indexOf(e) + 1;
//                String h3ProductName = e.select("h3").first().text();
//                String hrefButton = e.select("a").first().attr("href");
//                System.out.println(ind + " " + h3ProductName + " " + hrefButton);
                String name = e.attr("name");
                String property = e.attr("property");
                String content = e.attr("content");
                boolean isAttributeIsProperty = e.hasAttr("property");
                boolean isAttributeIsName = e.hasAttr("name");
                boolean isAttributeIsNameAndValueIsViewport = name.equals("viewport");

                if (isAttributeIsProperty) {
                    System.out.println("<meta property " + "'" +property + "'" +" content='" + content+ "'/>");
                } else if (isAttributeIsName && !isAttributeIsNameAndValueIsViewport){
                    System.out.println("<meta name " + "'" + name +"'" +" content='" + content + "'/>");
                }
            }

//            Elements assetPaths = doc.getAllElements();

//            Elements withAttr = new Elements();
//            for( Element element : assetPaths) {
//                for( Attribute attribute : element.attributes() )
//                {
//                    int ind = assetPaths.indexOf(element) + 1;
//                    boolean correctSiteCode = attribute.getValue().contains("/uk");
//                    if( attribute.getValue().contains("//images.samsung.com") && !correctSiteCode)
//                    {
//                        withAttr.add(element);
//                        System.out.println("ID: " + ind + " Path: " + attribute);
//                    }
//                }
//            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}