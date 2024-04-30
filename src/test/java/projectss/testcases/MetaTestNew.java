package projectss.testcases;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class MetaTestNew extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public MetaPage metaPage;

    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }

    //SignIn mặc định
//    @BeforeMethod
//    public void signIn() throws Exception {
//        System.out.println(driver);
//        signInPage = new SignInPage(driver);
//
//        SignInPage.loginThroughSession();
//    }
    //Phần xử lý get Meta
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyExistOfMetaContent(String param) throws Exception {
        metaPage = new MetaPage(driver);
        System.out.println("URL: " + param);
        Connection connection = Jsoup.connect(param);
        Document doc = connection.get();
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
        for (String s : arrMeta) {
            //System.out.println("The list is: " + s.toString() + ", ");
            assertTrue(StringUtils.isNotBlank(s),"Fail URL: " + param);
        }


    }
}
