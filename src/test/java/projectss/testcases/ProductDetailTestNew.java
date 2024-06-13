package projectss.testcases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectss.base.BaseSetup;
import projectss.base.SheetsQuickstart;
import projectss.pages.CommonPage;
import projectss.pages.ProductDetailPage;
import projectss.pages.SignInPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductDetailTestNew extends projectss.base.BaseSetup{
    private WebDriver driver;
    public SignInPage signInPage;
    public ProductDetailPage productDetailPage;
    public static List<String> listSKUCodes = new ArrayList<String>();

    public static List<String> listBadgeTexts = new ArrayList<String>();
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
    @Test(priority = 2)
    public void getDataFromURLs(ITestContext context) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        WebDriverWait wait60s = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebDriverWait wait10s = new WebDriverWait(driver, Duration.ofSeconds(10));
        String parameterGGSpreadSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
        String parameterGGSpreadSheetRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
        List<List<Object>> receivedValues = (List<List<Object>>) SheetsQuickstart.getQALinks(parameterGGSpreadSheetID ,parameterGGSpreadSheetRange);

        if (receivedValues == null || receivedValues.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("QA link");
            for (List row : receivedValues) {
                System.out.printf("%s,%s\n",row.get(0), row.get(5));
                driver.get((String) row.get(5));
                CommonPage.isPresent(driver,By.xpath("//span[@class='pd-info__sku-code']"));
                //wait60s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='pd-info__sku-code']")));

                //Add SKU Code text vào list
                WebElement elementSKUCode = driver.findElement(By.xpath("//span[@class='pd-info__sku-code']"));
                Document doc = Jsoup.parse(elementSKUCode.getAttribute("innerHTML"));
                String skuCode = doc.text();
                listSKUCodes.add(skuCode);

                //Add Badge text
                List<WebElement>  elementBadgeList = driver.findElements(By.xpath("//strong[contains(@class,'pd-info__badge-icon')]"));
                if(elementBadgeList.size()>0){
                    // Element is present
                    for (WebElement webElementBadgeText: elementBadgeList ) {
                        Document doc2 = Jsoup.parse(webElementBadgeText.getAttribute("innerHTML"));
                        String badgeText = doc2.text();
                        listBadgeTexts.add(badgeText);
                    }
                }  else{
                    // Element is not present
                    System.out.println(row.get(0)+ ": No badge text.");
                }
                //wait10s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(@class,'pd-info__badge-icon')]")));
                //WebElement elementBadgeText = driver.findElements(By.xpath("//strong[contains(@class,'pd-info__badge-icon')]"));
            }
        }
    }
    @Test(dependsOnMethods = "getDataFromURLs", dataProvider = "listSKUCodes",dataProviderClass = ProductDetailPage.class)
    public void verifySKUByGoogle(ITestContext context) throws Exception {
        String parameterGGSpreadSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
        String parameterGGSpreadSheetRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
        List<List<Object>> receivedValues = (List<List<Object>>) SheetsQuickstart.getQALinks(parameterGGSpreadSheetID ,parameterGGSpreadSheetRange);

        if (receivedValues == null || receivedValues.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("QA link");
            for (List row : receivedValues) {
                System.out.printf("%s,%s\n",row.get(0), row.get(5));
                for (String skuCode: listSKUCodes) {
                    Assert.assertEquals(skuCode,row.get(1));
                }
            }
        }
    }
    //Xài driver, cookie và jsoup
    @Test(priority = 3, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyBadgeText(String param) throws Exception {
        productDetailPage = new ProductDetailPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        Document doc = Jsoup.parse(ProductDetailPage.getElementBadgeText(driver));
        String badgeText = doc.body().text();
        Assert.assertEquals(badgeText,"เก่าแลกใหม่ 1000");
    }

    @Test
    @Parameters({ "key1", "key2" })
    public void m1(String key1, String key2) throws Exception {
        System.out.println(key1 + ", " + key2);
    }
}
