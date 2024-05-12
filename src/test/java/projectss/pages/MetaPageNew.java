package projectss.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class MetaPageNew {
    public static WebDriver driver;
    @FindBy(xpath = "//html//head")
    public static WebElement headElement;

    public MetaPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ArrayList<String> arrayContentMetaTags() {
        //WebElement header = driver.findElement(By.xpath("//html//head"));
        String headerSourceCode = headElement.getAttribute("innerHTML");

        Document doc = Jsoup.parse(headerSourceCode);
        Elements meta = doc.getElementsByTag("meta");
        Elements pageTitle = doc.getElementsByTag("title");
        Elements eLink = doc.select("link[rel='canonical']");
        String sLinkCan = eLink.attr("href");
        String sPageTitle = pageTitle.text();

        ArrayList<String> arrayContentMetaTags = new ArrayList<>();
        arrayContentMetaTags.add(sPageTitle);
        arrayContentMetaTags.add(sLinkCan);

        //In ra
        System.out.println("<title>" + sPageTitle + "</title> ");
        System.out.println("<link rel= 'canonical' href='" + sLinkCan + "'/>");
        for (Element e : meta) {
            //Lấy để làm title
            String name = e.attr("name");
            String property = e.attr("property");
            //Lấy value để verify
            String content = e.attr("content");
            //Lấy cờ để lọc
            boolean isAttributeIsProperty = e.hasAttr("property");
            boolean isAttributeIsName = e.hasAttr("name");
            boolean isAttributeIsNameAndValueIsViewport = name.equals("viewport");
            boolean isAttributeIsNameAndValueIsTwitterCard = name.equals("twitter:card");
            if (isAttributeIsProperty) {
                System.out.println("<meta property " + "'" + property + "'" + " content='" + content + "'/>");
                arrayContentMetaTags.add(content);
            } else if (isAttributeIsName && !isAttributeIsNameAndValueIsViewport && !isAttributeIsNameAndValueIsTwitterCard) {
                System.out.println("<meta name " + "'" + name + "'" + " content='" + content + "'/>");
                arrayContentMetaTags.add(content);
            }
        }

        String[] arrMeta = new String[arrayContentMetaTags.size()];
        arrayContentMetaTags.toArray(arrMeta);

        return arrayContentMetaTags();
    }
}
