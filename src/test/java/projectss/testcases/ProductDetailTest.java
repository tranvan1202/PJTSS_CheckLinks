package projectss.testcases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.ProductDetailPage;
import projectss.pages.SignInPage;

import static org.testng.Assert.assertTrue;

public class ProductDetailTest extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public ProductDetailPage productDetailPage;
    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }
    @Test(priority = 1)
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);

        SignInPage.loginThroughSession();
        assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Xài driver, cookie và jsoup
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyBadgeText(String param) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        Document doc = Jsoup.parse(ProductDetailPage.getElementBadgeText(driver));
        String badgeText = doc.body().text();
        Assert.assertEquals(badgeText,"เก่าแลกใหม่ 1000");
    }
    //Xài cookie và jsoup, max 50 links
    @Test(priority = 3, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyBadgeText2(String param) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        System.out.println("URL: " + param);
        Document doc = Jsoup.connect(param)
                .cookie("AWSALB", BaseSetup.prop.getProperty("quickLoginID"))
                .cookie("login-token",BaseSetup.prop.getProperty("quickLoginToken"))
                .get();
        Elements eBadgeText = doc.select("strong > span.badge-icon");
        String badgeText = eBadgeText.text();
        Assert.assertEquals(badgeText,"Thu cũ đổi mới");
    }

    @Test(priority = 4, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyXemCatalogueButton(String param) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        Document doc = Jsoup.parse(ProductDetailPage.getXemCatalogueButton(driver));
        String catalogueButton = doc.body().text();
        //Assert.assertEquals(badgeText,"Xem catalogue");
        Assert.assertTrue(catalogueButton.isEmpty());
    }
}
