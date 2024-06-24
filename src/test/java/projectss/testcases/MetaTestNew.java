package projectss.testcases;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;

import java.util.ArrayList;


import static org.testng.Assert.assertTrue;

public class MetaTestNew extends projectss.base.BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public MetaPage metaPage;

    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }

    //SignIn mặc định
    @Test(priority = 1)
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);

        signInPage.loginThroughSession();
    }
    //Phần xử lý get Meta (version 2.0| dùng driver Browser để get| pros: không giới hạn QA links, cons: cần gọi sign in, chậm)
    @Test(priority = 3, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
    public void verifyExistOfMetaContent2(String param) throws Exception {
        System.out.println("URL: " + param);
        driver.get(param);
        WebElement header = driver.findElement(By.xpath("//html//head"));
        String headerSourceCode = header.getAttribute("innerHTML");
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
        for (String s : arrMeta) {
            //System.out.println("The list is: " + s.toString() + ", ");
            assertTrue(StringUtils.isNotBlank(s),"Fail URL: " + param);
        }
    }

    //Phần xử lý get Meta (version 3.0| Jsoup| pros: nhanh, ko cần gọi sign in, cons: 50-70 links/ lần chạy do cookies không tồn tại lâu)
    @Test(priority = 4, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
    public void verifyExistOfMetaContent3(String param) throws Exception {
        Document doc = Jsoup.connect(param)
                .cookie("AWSALB", BaseSetup.prop.getProperty("quickLoginID"))
                .cookie("login-token",BaseSetup.prop.getProperty("quickLoginToken"))
                .get();
        Element eMeta = doc.select("html > head").first();
        Elements meta = eMeta.getElementsByTag("meta");
        Elements pageTitle = eMeta.getElementsByTag("title");
        Elements eLink = eMeta.select("link[rel='canonical']");
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
            assertTrue(StringUtils.isNotBlank(s),"Fail URL: " + param);
        }
    }
}
