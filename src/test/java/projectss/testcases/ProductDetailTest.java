package projectss.testcases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectss.base.BaseSetup;
import projectss.base.SheetsQuickstart;
import projectss.pages.ProductDetailPage;
import projectss.pages.SignInPage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ProductDetailTest extends projectss.base.BaseSetup {
    private WebDriver driver;
    private SignInPage signInPage;
    private BaseSetup baseSetup = this;
    private ProductDetailPage productDetailPage;
    private static String resultSheetName = "";
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }
    @BeforeClass(dependsOnMethods = { "setUp" })
    public static void testSetupWriteResultIntoSheets(ITestContext context) throws GeneralSecurityException, IOException {
        LocalDateTime myDateTime = LocalDateTime.now();
        String existingSpreadSheetID = paramsInputExistingSheetId(context);

        SheetsQuickstart.getCredentials();
        SheetsQuickstart.getSpreadsheetInstance();

        DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        String formatDate = myDateTime.format(myDateTimeFormat);

        resultSheetName = "TestResult_" + formatDate;
        System.out.println("SheetName-" + formatDate);
        SheetsQuickstart.createNewSheet(existingSpreadSheetID, resultSheetName);
        SheetsQuickstart.writeDataGoogleSheets(resultSheetName,
                new ArrayList<Object>(Arrays.asList("Test Case ID","Test Case Name", "QA No", "QA URL","Xpath", "Expected","Actual","Result")),
                existingSpreadSheetID);
    }
    @Test(priority = 1)
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);
        productDetailPage = signInPage.loginAndGoToPDP();
    }

    //Xài driver, cookie và jsoup
//    @Test(priority = 2, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
//    public void verifyBadgeText(String param) throws Exception {
//        productDetailPage = new ProductDetailPage(driver);
//        System.out.println("URL: " + param);
//        driver.get(param);
//        Document doc = Jsoup.parse(ProductDetailPage.getElementBadgeText(driver));
//        String badgeText = doc.body().text();
//        Assert.assertEquals(badgeText,"เก่าแลกใหม่ 1000");
//    }
//    //Xài cookie và jsoup, max 50 links
//    @Test(priority = 1, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
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

//    @Test(priority = 4, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
//    public void verifyXemCatalogueButton(String param) throws Exception {
//        productDetailPage = new ProductDetailPage(driver);
//        System.out.println("URL: " + param);
//        driver.get(param);
//        Document doc = Jsoup.parse(ProductDetailPage.getXemCatalogueButtonText(driver));
//        String catalogueButton = doc.body().text();
//        //Assert.assertEquals(badgeText,"Xem catalogue");
//        Assert.assertTrue(catalogueButton.isEmpty());
//    }

    @Test(priority = 2, dataProvider = "urlSheetLink", dataProviderClass = BaseSetup.class)
    public void verifyTextByXpath2(int testCaseID,String testCaseName,int qaNo,String qaURL,String xpath,String expectedResult) throws Exception {
        //SoftAssert softAssert = new SoftAssert();
        // Biến load params
        org.testng.ITestContext context = org.testng.Reporter.getCurrentTestResult().getTestContext();

        baseSetup.getUrlLink2(context);
        System.out.println(qaURL);
        //For qua các row data trong Google Sheet

        //softAssert.assertAll();
    }

//    @Test(priority = 2)
//    public void verifyTextByXpath() throws Exception {
//        SoftAssert softAssert = new SoftAssert();
//        // Biến load params
//        org.testng.ITestContext context = org.testng.Reporter.getCurrentTestResult().getTestContext();
//        String existingSpreadSheetID = paramsInputExistingSheetId(context);
//        String ggSpreadSheetRange = BaseSetup.paramsInputTestDataSheetRange(context);
//        String inputQALinkColumn = BaseSetup.paramsInputQALinkColumn(context) ;
//        String inputXpathColumn = BaseSetup.paramsInputXpath(context);
//        String inputExpectedResultColumn = BaseSetup.paramsInputExpectedResultColumn(context);
//
//        baseSetup.getUrlLink1(existingSpreadSheetID,ggSpreadSheetRange);
//        System.out.println("QA link");
//        //For qua các row data trong Google Sheet
//        for (List row : BaseSetup.ggDataList) {
//            System.out.printf("%s,%s\n", row.get(0), row.get(Integer.parseInt(inputQALinkColumn)));
//            driver.get((String) row.get(Integer.parseInt(inputQALinkColumn)));
//            //Đợi Element text
//            Thread.sleep(2000);
//            //Đợi 5s ko tìm thấy Element thì move tiếp
//            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//            //Lưu Elements List khi ng dùng input Xpath
//            List<WebElement> inputedElement = driver.findElements(By.xpath((String) row.get(Integer.parseInt(inputXpathColumn))));
//            //boolean isInputedElementEmpty = inputedElement.size() > 0;
//            if (inputedElement.size() > 0) {
//                // Element is present
//                for (WebElement webInputedElementText : inputedElement) {
//                    Document doc = Jsoup.parse(webInputedElementText.getAttribute("innerHTML"));
//                    String actualElementText = doc.text();
//                    System.out.println("TestCase_ID " + row.get(0) + " text: " + actualElementText );
//                    SheetsQuickstart.writeDataGoogleSheets(resultSheetName, new ArrayList<Object>(Arrays.asList(
//                            row.get(0),row.get(1), row.get(2),row.get(3),row.get(4),row.get(5),
//                            actualElementText)), existingSpreadSheetID);
//
//                    softAssert.assertEquals(actualElementText,row.get(Integer.parseInt(inputExpectedResultColumn)),"TestCase_ID: "
//                            + row.get(0) + " lỗi sai text cho Element, chi tiết: ");
//                }
//            } else {
//                // Element is not present
//                //softAssert.assertTrue(isInputedElementEmpty,"TestCase_ID: " + row.get(0) + " lỗi ko có text cho element này ");
//                SheetsQuickstart.writeDataGoogleSheets(resultSheetName, new ArrayList<Object>(Arrays.asList(
//                        row.get(0),row.get(1), row.get(2),row.get(3),row.get(4),row.get(5),
//                        "Not found")),existingSpreadSheetID);
//            }
//        }
//        softAssert.assertAll();
//    }
}
