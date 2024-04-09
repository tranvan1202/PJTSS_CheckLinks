package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
    public static void main(String[] args) {
        final String input = "<person src=\"Samsung\" src-pc=\"Samsung\"/>"
                + "<person name=\"Mary\"/>"
                + "<animal src-mobile=\"Samsung\" thing=\"swims\"/>";


        Document doc = Jsoup.parse(input);
        Elements withAttr = new Elements();


        for( Element element : doc.getAllElements() ) {
            for( Attribute attribute : element.attributes() )
            {
                if( attribute.getValue().equalsIgnoreCase("Samsung") )
                {
                    withAttr.add(element);
                    System.out.println(" Ne:" + attribute + "________" );
                }
            }
        }
    }

}
