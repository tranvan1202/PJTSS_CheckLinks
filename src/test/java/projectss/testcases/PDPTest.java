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
import projectss.pages.CommonPage;
import projectss.pages.SignInPage;
import org.testng.ITestContext;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class PDPTest extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public CommonPage commonPage;
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
    public void verifySKUnBadgeByGoogle(ITestContext context) throws Exception {
        commonPage = new CommonPage(driver);
        String parameterGGSpreadSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
        String parameterGGSpreadSheetRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
        SoftAssert softAssert = new SoftAssert();
        List<List<Object>> receivedValues = (List<List<Object>>) SheetsQuickstart.getQALinks(parameterGGSpreadSheetID ,parameterGGSpreadSheetRange);
        if (receivedValues == null || receivedValues.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("QA link");
            for (List row : receivedValues) {
                System.out.printf("%s,%s\n",row.get(0), row.get(5));
                driver.get((String) row.get(5));

                //Verify SKU Code text
                WebElement elementSKUCode = driver.findElement(By.xpath("//span[@class='pd-info__sku-code']"));
                Document doc = Jsoup.parse(elementSKUCode.getAttribute("innerHTML"));
                String skuCode = doc.text();
                softAssert.assertEquals(skuCode,row.get(1),"No lỗi: " + row.get(0) + "_");

                //Verify Badge text
                WebElement elementBadgeText = driver.findElement(By.xpath("//strong[contains(@class,'pd-info__badge-icon')]"));
                Document doc2 = Jsoup.parse(elementBadgeText.getAttribute("innerHTML"));
                String badgeText = doc2.text();
                softAssert.assertEquals(badgeText,row.get(2),"No lỗi: " + row.get(0) + "_");
            }
            softAssert.assertAll();
        }
    }
}
