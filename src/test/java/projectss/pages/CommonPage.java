package projectss.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CommonPage {
    public static WebDriver driver;
    public CommonPage(WebDriver driver) {
        this.driver = driver;
    }
    public static Elements getAssetPath(String qaUrls, String siteCode) throws IOException {
        Document doc = Jsoup.connect(qaUrls).get();
        Elements assetPaths = doc.getAllElements();

        Elements withAttr = new Elements();
        for( Element element : assetPaths) {
            for( Attribute attribute : element.attributes() )
            {
                int ind = assetPaths.indexOf(element) + 1;
                boolean correctSiteCode = attribute.getValue().contains(siteCode);
                if( attribute.getValue().contains("//images.samsung.com") && !correctSiteCode)
                {
                    withAttr.add(element);
                    System.out.println("ID: " + ind + " Path: " + attribute);
                }
            }
        }
        return withAttr;
    }
}
