package projectss.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommonPage {
    public static WebDriver driver;
    public CommonPage(WebDriver driver) {
        this.driver = driver;
    }
    public static Elements getWrongSiteCodeAssetPath(WebDriver driver, String siteCode) {
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
        Elements assetPaths = doc.getAllElements();

        Elements withAttr = new Elements();
        for( Element element : assetPaths) {
            for( Attribute attribute : element.attributes() )
            {
                int ind = assetPaths.indexOf(element) + 1;
                boolean correctSiteCode = attribute.getValue().contains(siteCode);
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

    public static boolean isInputLinkBroken(String url) {
        url = convertURLFromP6(url);
        boolean success = true;
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println("URL: "+ url + " - " + httpURLConnection.getResponseMessage());
                success = true;
            } else {
                System.out.println("URL: "+ url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
                success = false;
            }
        } catch (Exception e) {
            System.out.println("URL: "+ url + " - " + "is a broken link");
        }
        return success;
    }

    public static boolean isGetLinkBroken(List<String> hrefURLs) {
        boolean success = true;
        for (String hrefURL:hrefURLs) {
            hrefURL = convertURLFromP6(hrefURL);
            try {
                URL link = new URL(hrefURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
                httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    System.out.println("URL: "+ hrefURL + " - " + httpURLConnection.getResponseMessage());
                    success = true;
                } else {
                    System.out.println("URL: "+ hrefURL + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
                    success = false;
                }
            } catch (Exception e) {
                System.out.println("URL: "+ hrefURL + " - " + "is a broken link");
            }
        }
        return success;
    }
    public static boolean isKeywordsExist(WebDriver driver,String url, String keyword) {
        boolean exist = false;
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);

        Element el = doc.select(":containsOwn('keyword')").first();
        Element p = doc.select("p:contains(keyword)").first();

        System.out.println(el.html());
        System.out.println(p.html());
        return exist;
    }

    public static List<String> getHrefFromOrginalURL(String inputOrgUrl) {
        driver.get(inputOrgUrl);
        List<String> hrefLinks = new ArrayList<>();
        List<WebElement> list=driver.findElements(By.xpath("//*[@href or @src]"));

        for(WebElement e : list){
            String link = e.getAttribute("href");
            if(null==link){
                link=e.getAttribute("src");
                hrefLinks.add(link);
            }
            hrefLinks.add(link);
            //System.out.println(e.getTagName() + "=" + link);
        }
        return hrefLinks;
    }
    public static String convertURLFromP6(String url) {
        if (url.contains("p6-qa")) {
            url = url.replace("p6-qa","www");
            return url;
        }
        return url;
    }
}
