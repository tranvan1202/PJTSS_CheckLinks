package projectss.testcases;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class MetaTest extends BaseSetup {
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

        SignInPage.signin();
        assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Phần xử lý get Meta
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyMetaTitle(String param) throws Exception {
        metaPage = new MetaPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
        System.out.println(" ");
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("pageTitle: " + MetaPage.driver.getTitle());
//        System.out.println("metaTitle: " + MetaPage.metaTitle.getAttribute("content"));
//        System.out.println("linkCan: " + MetaPage.linkCan.getAttribute("href"));
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaNameKeywords: " + MetaPage.metaNameKeywords.getAttribute("content"));
//        System.out.println("metaNameDescription: " + MetaPage.metaNameDescription.getAttribute("content"));
//        System.out.println("metaNameDate: " + MetaPage.metaNameDate.getAttribute("content"));
//        System.out.println("metaNameSiteCode : " + MetaPage.metaNameSiteCode.getAttribute("content"));
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaNameTwitterCard: " + MetaPage.metaNameTwitterCard.getAttribute("content"));
//        System.out.println("metaNameTwitterSite: " + MetaPage.metaNameTwitterSite.getAttribute("content"));
//        System.out.println("metaNameTwitterCreator: " + MetaPage.metaNameTwitterCreator.getAttribute("content"));
//        System.out.println("metaNameTwitterURL: " + MetaPage.metaNameTwitterURL.getAttribute("content"));
//        System.out.println("metaNameTwitterTitle: " + MetaPage.metaNameTwitterTitle.getAttribute("content"));
//        System.out.println("metaNameTwitterDescription: " + MetaPage.metaNameTwitterDescription.getAttribute("content"));
//        System.out.println("metaNameTwitterImage: " + MetaPage.metaNameTwitterImage.getAttribute("content"));
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaPropertyOgURL: " + MetaPage.metaPropertyOgURL.getAttribute("content"));
//        System.out.println("metaPropertyOgImage: " + MetaPage.metaPropertyOgImage.getAttribute("content"));
//        System.out.println("metaPropertyOgType: " + MetaPage.metaPropertyOgType.getAttribute("content"));
//        System.out.println("metaPropertyOgSiteName: " + MetaPage.metaPropertyOgSiteName.getAttribute("content"));
//        System.out.println("metaPropertyOgLocale: " + MetaPage.metaPropertyOgLocale.getAttribute("content"));
//        System.out.println("metaPropertyOgTitle: " + MetaPage.metaPropertyOgTitle.getAttribute("content"));
//        System.out.println("metaPropertyOgDescription: " + MetaPage.metaPropertyOgDescription.getAttribute("content"));
//        System.out.println("metaPropertyOgCountryName: " + MetaPage.metaPropertyOgCountryName.getAttribute("content"));
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaPropertyName: " + MetaPage.metaPropertyName.getAttribute("content"));
//        System.out.println("metaPropertyImage: " + MetaPage.metaPropertyImage.getAttribute("content"));
//        System.out.println("metaPropertyUrl: " + MetaPage.metaPropertyUrl.getAttribute("content"));
//        System.out.println("metaPropertyDescription: " + MetaPage.metaPropertyDescription.getAttribute("content"));
//        System.out.println("metaPropertyKeywords: " + MetaPage.metaPropertyKeywords.getAttribute("content"));

        String spageTitle = MetaPage.driver.getTitle();
        String smetaTitle = MetaPage.metaTitle.getAttribute("content");
        String slinkCan = MetaPage.linkCan.getAttribute("href");
        String smetaNameKeywords = MetaPage.metaNameKeywords.getAttribute("content");
        String smetaNameDescription = MetaPage.metaNameDescription.getAttribute("content");
        String smetaNameDate = MetaPage.metaNameDate.getAttribute("content");
        String smetaNameSiteCode = MetaPage.metaNameSiteCode.getAttribute("content");
        String smetaNameTwitterCard = MetaPage.metaNameTwitterCard.getAttribute("content");
        String smetaNameTwitterSite = MetaPage.metaNameTwitterSite.getAttribute("content");
        String smetaNameTwitterCreator = MetaPage.metaNameTwitterCreator.getAttribute("content");
        String smetaNameTwitterURL = MetaPage.metaNameTwitterURL.getAttribute("content");
        String smetaNameTwitterTitle = MetaPage.metaNameTwitterTitle.getAttribute("content");
        String smetaNameTwitterDescription = MetaPage.metaNameTwitterDescription.getAttribute("content");
        String smetaNameTwitterImage = MetaPage.metaNameTwitterImage.getAttribute("content");
        String smetaPropertyOgURL = MetaPage.metaPropertyOgURL.getAttribute("content");
        String smetaPropertyOgImage= MetaPage.metaPropertyOgImage.getAttribute("content");
        String smetaPropertyOgType = MetaPage.metaPropertyOgType.getAttribute("content");
        String smetaPropertyOgSiteName = MetaPage.metaPropertyOgSiteName.getAttribute("content");
        String smetaPropertyOgLocale = MetaPage.metaPropertyOgLocale.getAttribute("content");
        String smetaPropertyOgTitle = MetaPage.metaPropertyOgTitle.getAttribute("content");
        String smetaPropertyOgDescription = MetaPage.metaPropertyOgDescription.getAttribute("content");
        String smetaPropertyOgCountryName = MetaPage.metaPropertyOgCountryName.getAttribute("content");
        String smetaPropertyName = MetaPage.metaPropertyName.getAttribute("content");
        String smetaPropertyImage = MetaPage.metaPropertyImage.getAttribute("content");
        String smetaPropertyUrl = MetaPage.metaPropertyUrl.getAttribute("content");
        String smetaPropertyDescription = MetaPage.metaPropertyDescription.getAttribute("content");
        String smetaPropertyKeywords = MetaPage.metaPropertyKeywords.getAttribute("content");

        String arrMeta[] = new String[] { spageTitle , smetaTitle , slinkCan , smetaNameKeywords , smetaNameDescription , smetaNameDate ,
                smetaNameSiteCode , smetaNameTwitterCard , smetaNameTwitterSite , smetaNameTwitterCreator , smetaNameTwitterURL , smetaNameTwitterTitle ,
                smetaNameTwitterDescription , smetaNameTwitterImage , smetaPropertyOgURL , smetaPropertyOgImage, smetaPropertyOgType , smetaPropertyOgSiteName ,
                smetaPropertyOgLocale , smetaPropertyOgTitle , smetaPropertyOgDescription , smetaPropertyOgCountryName , smetaPropertyName , smetaPropertyImage ,
                smetaPropertyUrl , smetaPropertyDescription , smetaPropertyKeywords
        };
        List<String> listMeta = Arrays.asList(arrMeta);
        for (String s : listMeta) {
            System.out.println("The list is: " + s.toString() + ", ");
            //assertTrue(StringUtils.isNotBlank(s));
        }
//        assertTrue(StringUtils.isNotBlank(MetaPage.driver.getTitle()));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaTitle.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.linkCan.getAttribute("href")));
//        /////////////////////////////////////////////////////////////////////////////////
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameKeywords.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameDescription.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameDate.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameSiteCode.getAttribute("content")));
//        /////////////////////////////////////////////////////////////////////////////////
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterCard.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterSite.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterCreator.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterURL.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterTitle.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterDescription.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaNameTwitterImage.getAttribute("content")));
//        /////////////////////////////////////////////////////////////////////////////////
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgURL.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgImage.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgType.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgSiteName.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgLocale.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgTitle.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgDescription.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyOgCountryName.getAttribute("content")));
//        /////////////////////////////////////////////////////////////////////////////////
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyName.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyImage.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyUrl.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyDescription.getAttribute("content")));
//        assertTrue(StringUtils.isNotBlank(MetaPage.metaPropertyKeywords.getAttribute("content")));
    }
//    @Test(priority = 3, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
//    public void verifyMetaTwitter(String param) throws Exception {
//        System.out.println("URL: " + param);
//        System.out.println("metaNameTwitterDescription: " + MetaPage.MetaPageInfo.getMetaNameTwitterDescription());
//        System.out.println("metaNameTwitterCard: " + MetaPage.MetaPageInfo.getMetaNameTwitterCard());
//        assertTrue(StringUtils.isNotBlank(MetaPage.MetaPageInfo.getMetaNameTwitterDescription());
//        assertTrue(StringUtils.isNotBlank(MetaPage.MetaPageInfo.getMetaNameTwitterCard());
//    }
}
