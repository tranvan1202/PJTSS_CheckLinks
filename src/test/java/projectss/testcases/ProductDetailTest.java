package projectss.testcases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectss.base.BaseSetup;
import projectss.base.SheetsQuickstart;
import projectss.base.SheetsQuickstart_original;
import projectss.pages.ProductDetailPage;
import projectss.pages.SignInPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProductDetailTest extends projectss.base.BaseSetup {
    private WebDriver driver;
    private SignInPage signInPage;
    private ProductDetailPage productDetailPage;
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }
    @Test(priority = 1)
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);
        productDetailPage = signInPage.loginAndGoToPDP();
    }

    //Xài driver, cookie và jsoup
//    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
//    public void verifyBadgeText(String param) throws Exception {
//        productDetailPage = new ProductDetailPage(driver);
//        System.out.println("URL: " + param);
//        driver.get(param);
//        Document doc = Jsoup.parse(ProductDetailPage.getElementBadgeText(driver));
//        String badgeText = doc.body().text();
//        Assert.assertEquals(badgeText,"เก่าแลกใหม่ 1000");
//    }
//    //Xài cookie và jsoup, max 50 links
//    @Test(priority = 1, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
//    public void verifyBadgeText2(String param) throws Exception {
//        productDetailPage = new ProductDetailPage(driver);
//        System.out.println("URL: " + param);
//        Document doc = Jsoup.connect(param)
//                .cookie("AWSALB", BaseSetup.prop.getProperty("quickLoginID"))
//                .cookie("login-token",BaseSetup.prop.getProperty("quickLoginToken"))
//                .get();
//        Elements eBadgeText = doc.select("strong > span.badge-icon");
//        String badgeText = eBadgeText.text();
//        Assert.assertEquals(badgeText,"Thu cũ đổi mới");
//    }

//    @Test(priority = 4, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
//    public void verifyXemCatalogueButton(String param) throws Exception {
//        productDetailPage = new ProductDetailPage(driver);
//        System.out.println("URL: " + param);
//        driver.get(param);
//        Document doc = Jsoup.parse(ProductDetailPage.getXemCatalogueButtonText(driver));
//        String catalogueButton = doc.body().text();
//        //Assert.assertEquals(badgeText,"Xem catalogue");
//        Assert.assertTrue(catalogueButton.isEmpty());
//    }

    @Test(priority = 2, dataProvider = "paramsVerifyTextByInputXpath", dataProviderClass = BaseSetup.class)
    public void verifyTextByXpath(String ggSpreadSheetID, String ggSpreadSheetRange,String inputQALinkColumn,String inputXpathColumn, String inputExpectedResultColumn) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        productDetailPage.getQALinkList(ggSpreadSheetID,ggSpreadSheetRange);

        SheetsQuickstart.getCredentials();
        SheetsQuickstart.getSpreadsheetInstance();


        List<List<Object>> values = new ArrayList<>();;
        System.out.println("QA link");
        //For qua các row data trong Google Sheet
        for (List row : ProductDetailPage.ggDataList) {
            By byInputXpath = By.xpath((String) row.get(Integer.parseInt(inputXpathColumn)));
            System.out.printf("%s,%s\n", row.get(0), row.get(Integer.parseInt(inputQALinkColumn)));
            driver.get((String) row.get(Integer.parseInt(inputQALinkColumn)));
            //Đợi Element text
            Thread.sleep(2000);
            //Lưu Elements List khi ng dùng input Xpath
            List<WebElement> inputedElement = driver.findElements(byInputXpath);
            boolean isInputedElementEmpty = inputedElement.size() > 0;
            if (isInputedElementEmpty) {
                // Element is present
                for (WebElement webInputedElementText : inputedElement) {
                    Document doc = Jsoup.parse(webInputedElementText.getAttribute("innerHTML"));
                    String actualElementText = doc.text();
                    System.out.println("TestCase_ID " + row.get(0) + " text: " + actualElementText );
                    softAssert.assertEquals(actualElementText,row.get(Integer.parseInt(inputExpectedResultColumn)),"TestCase_ID: " + row.get(0) + " lỗi sai text cho Element, chi tiết: ");
                    values.add(Collections.singletonList(actualElementText));
                    SheetsQuickstart_original.updateValues(ggSpreadSheetID,"QuickSample!F2:F100","RAW",values);

                }
            } else {
                // Element is not present
                softAssert.assertTrue(isInputedElementEmpty,"TestCase_ID: " + row.get(0) + " lỗi ko có text cho element này ");
            }
        }
        softAssert.assertAll();
    }
}
