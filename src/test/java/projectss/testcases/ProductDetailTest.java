package projectss.testcases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectss.base.BaseSetup;
import projectss.pages.ProductDetailPage;

import java.util.List;


public class ProductDetailTest extends projectss.base.BaseSetup {
    public WebDriver driver;
    public ProductDetailPage productDetailPage;
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

    @Test(priority = 4, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyXemCatalogueButton(String param) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        Document doc = Jsoup.parse(ProductDetailPage.getXemCatalogueButtonText(driver));
        String catalogueButton = doc.body().text();
        //Assert.assertEquals(badgeText,"Xem catalogue");
        Assert.assertTrue(catalogueButton.isEmpty());
    }

//    @Test(priority = 1)
//    public void verifySKUnBadgeByGoogle(ITestContext context) throws Exception {
//        System.out.println(driver);
//        productDetailPage = new ProductDetailPage(driver);
//        //productDetailPage = new ProductDetailPage(driver);
//        WebDriverWait wait60s = new WebDriverWait(driver, Duration.ofSeconds(60));
//        String parameterGGSpreadSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
//        String parameterGGSpreadSheetRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
//        SoftAssert softAssert = new SoftAssert();
//        List<List<Object>> receivedValues = (List<List<Object>>) SheetsQuickstart.getQALinks(parameterGGSpreadSheetID ,parameterGGSpreadSheetRange);
//        if (receivedValues == null || receivedValues.isEmpty()) {
//            System.out.println("No data found.");
//        } else {
//            System.out.println("QA link");
//            for (List row : receivedValues) {
//                System.out.printf("%s,%s\n",row.get(0), row.get(5));
//                driver.get((String) row.get(5));
//                wait60s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='pd-info__sku-code']")));
//                //Verify SKU Code text
//                WebElement elementSKUCode = driver.findElement(By.xpath("//span[@class='pd-info__sku-code']"));
//                Document doc = Jsoup.parse(elementSKUCode.getAttribute("innerHTML"));
//                String skuCode = doc.text();
//                softAssert.assertEquals(skuCode,row.get(1),"No " + row.get(0) + " lỗi sai mã SKU, chi tiết: ");
//
//                //Verify Badge text
//                List<WebElement>  elementBadgeList = driver.findElements(By.xpath("//strong[contains(@class,'pd-info__badge-icon')]"));
//                boolean isElementBadgeEmpty = elementBadgeList.size()>0;
//                if(isElementBadgeEmpty){
//                    // Element is present
//                    for (WebElement webElementBadgeText: elementBadgeList ) {
//                        Document doc2 = Jsoup.parse(webElementBadgeText.getAttribute("innerHTML"));
//                        String badgeText = doc2.text();
//                        softAssert.assertEquals(badgeText,row.get(2),"No " + row.get(0) + " lỗi sai text, chi tiết: ");
//                    }
//                }  else{
//                    // Element is not present
//                    softAssert.assertTrue(isElementBadgeEmpty,"No " + row.get(0) + " lỗi ko có badge, chi tiết: ");
//                }
//            }
//            softAssert.assertAll();
//        }
//    }

    @Parameters({"paramSheetID","paramSheetDataRange","paramExpectedResultColumn","paramInputXpathString" })
    @Test(priority = 1)
    public void verifyTextByXpath(String ggSpreadSheetID, String ggSpreadSheetRange, int inputExpectedResultColumn,String inputXpath) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        SoftAssert softAssert = new SoftAssert();
        By byInputXpath = By.xpath(inputXpath);
//        By inputXpath = By.xpath(context.getCurrentXmlTest().getParameter("inputXpath"));
//        String paramSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
//        String paramSheetDataRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
//        int paramExpectedResultColumn = Integer.parseInt(context.getCurrentXmlTest().getParameter("inputExpectedResultColumn"));
        productDetailPage.getQALinkList(ggSpreadSheetID,ggSpreadSheetRange);
        String actualElementText =  productDetailPage.actualTextFromInputtedElement(byInputXpath);
        //String expectedElementText = "";
        for (List row : productDetailPage.ggDataList) {
            softAssert.assertEquals(actualElementText,row.get(inputExpectedResultColumn),"No " + row.get(0) + " lỗi sai text, chi tiết: ");
        }
        softAssert.assertAll();
    }
}
