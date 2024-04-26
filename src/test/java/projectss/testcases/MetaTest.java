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

        SignInPage.loginThroughSession();
        assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Phần xử lý get Meta
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyExistOfMetaContent(String param) throws Exception {
        metaPage = new MetaPage(driver);
        //System.out.println("URL: " + param);
        driver.get(param);

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

//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("pageTitle: " + spageTitle);
//        System.out.println("metaTitle: " + smetaTitle);
//        System.out.println("linkCan: " + slinkCan);
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaNameKeywords: " + smetaNameKeywords);
//        System.out.println("metaNameDescription: " + smetaNameDescription);
//        System.out.println("metaNameDate: " + smetaNameDate);
//        System.out.println("metaNameSiteCode : " + smetaNameSiteCode);
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaNameTwitterCard: " + smetaNameTwitterCard);
//        System.out.println("metaNameTwitterSite: " + smetaNameTwitterSite);
//        System.out.println("metaNameTwitterCreator: " + smetaNameTwitterCreator);
//        System.out.println("metaNameTwitterURL: " + smetaNameTwitterURL);
//        System.out.println("metaNameTwitterTitle: " + smetaNameTwitterTitle);
//        System.out.println("metaNameTwitterDescription: " + smetaNameTwitterDescription);
//        System.out.println("metaNameTwitterImage: " + smetaNameTwitterImage);
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaPropertyOgURL: " + smetaPropertyOgURL);
//        System.out.println("metaPropertyOgImage: " + smetaPropertyOgImage);
//        System.out.println("metaPropertyOgType: " + smetaPropertyOgType);
//        System.out.println("metaPropertyOgSiteName: " + smetaPropertyOgSiteName);
//        System.out.println("metaPropertyOgLocale: " + smetaPropertyOgLocale);
//        System.out.println("metaPropertyOgTitle: " + smetaPropertyOgTitle);
//        System.out.println("metaPropertyOgDescription: " + smetaPropertyOgDescription);
//        System.out.println("metaPropertyOgCountryName: " + smetaPropertyOgCountryName);
//        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
//        System.out.println(" ");
//        System.out.println("metaPropertyName: " + smetaPropertyName);
//        System.out.println("metaPropertyImage: " + smetaPropertyImage);
//        System.out.println("metaPropertyUrl: " + smetaPropertyUrl);
//        System.out.println("metaPropertyDescription: " + smetaPropertyDescription);
//        System.out.println("metaPropertyKeywords: " + smetaPropertyKeywords);

        //Bỏ smetaNameTwitterCard
        String arrMeta[] = new String[] { spageTitle , smetaTitle , slinkCan , smetaNameKeywords , smetaNameDescription , smetaNameDate ,
                smetaNameSiteCode , smetaNameTwitterSite , smetaNameTwitterCreator , smetaNameTwitterURL , smetaNameTwitterTitle ,
                smetaNameTwitterDescription , smetaNameTwitterImage , smetaPropertyOgURL , smetaPropertyOgImage, smetaPropertyOgType , smetaPropertyOgSiteName ,
                smetaPropertyOgLocale , smetaPropertyOgTitle , smetaPropertyOgDescription , smetaPropertyOgCountryName , smetaPropertyName , smetaPropertyImage ,
                smetaPropertyUrl , smetaPropertyDescription , smetaPropertyKeywords
        };

        ArrayList<String> arrMissingMetaURLs = new ArrayList<>();
        List<String> listMeta = Arrays.asList(arrMeta);
        for (String s : listMeta) {
            //System.out.println("The list is: " + s.toString() + ", ");
            assertTrue(StringUtils.isNotBlank(s),"Fail URL: " + param);
            arrMissingMetaURLs.add(param);
        }
        System.out.println(arrMissingMetaURLs);
    }
}
