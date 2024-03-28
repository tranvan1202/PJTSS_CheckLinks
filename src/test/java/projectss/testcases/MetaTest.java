package projectss.testcases;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        SignInPage.signin("van.binhtran@samsung.com");
        assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Phần xử lý get Meta
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyMetaTitle(String param) throws Exception {
        metaPage = new MetaPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);

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

        for (String s : Arrays.asList(MetaPage.driver.getTitle(),
                MetaPage.metaTitle.getAttribute("content"), MetaPage.linkCan.getAttribute("href"),
                MetaPage.metaNameKeywords.getAttribute("content"), MetaPage.metaNameDescription.getAttribute("content"),
                MetaPage.metaNameDate.getAttribute("content"), MetaPage.metaNameSiteCode.getAttribute("content"),
                MetaPage.metaNameTwitterCard.getAttribute("content"), MetaPage.metaNameTwitterSite.getAttribute("content"),
                MetaPage.metaNameTwitterCreator.getAttribute("content"), MetaPage.metaNameTwitterURL.getAttribute("content"),
                MetaPage.metaNameTwitterTitle.getAttribute("content"), MetaPage.metaNameTwitterDescription.getAttribute("content"),
                MetaPage.metaNameTwitterImage.getAttribute("content"), MetaPage.metaPropertyOgURL.getAttribute("content"),
                MetaPage.metaPropertyOgImage.getAttribute("content"), MetaPage.metaPropertyOgType.getAttribute("content"),
                MetaPage.metaPropertyOgSiteName.getAttribute("content"), MetaPage.metaPropertyOgLocale.getAttribute("content"),
                MetaPage.metaPropertyOgTitle.getAttribute("content"), MetaPage.metaPropertyOgDescription.getAttribute("content"),
                MetaPage.metaPropertyOgCountryName.getAttribute("content"), MetaPage.metaPropertyName.getAttribute("content"),
                MetaPage.metaPropertyImage.getAttribute("content"), MetaPage.metaPropertyUrl.getAttribute("content"),
                MetaPage.metaPropertyDescription.getAttribute("content"), MetaPage.metaPropertyKeywords.getAttribute("content"))) {
            assertTrue(StringUtils.isNotBlank(s));
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
