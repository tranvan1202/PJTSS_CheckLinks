package projectss.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import projectss.base.SheetsQuickstart;
import projectss.testcases.ProductDetailTestNew;

import java.time.Duration;
import java.util.List;

public class ProductDetailPage extends projectss.pages.CommonPage {
    public WebDriver driver;
    public static List<List<Object>> ggDataList;
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private static By bySKUCode= By.xpath("//span[@class='pd-info__sku-code");
    public static String getElementBadgeText(WebDriver driver){
        return driver.findElement(bySKUCode).getText();
    }
    private static By byXemCatalogueButton = By.xpath("//a[contains(text(),'Xem catalogue')]");
    public static String getXemCatalogueButtonText(WebDriver driver){
        return driver.findElement(byXemCatalogueButton).getText();
    }
    @DataProvider(name="listSKUCodes")
    public List<String> getSKUCodes(ITestContext context) {
        return ProductDetailTestNew.listSKUCodes;
    }

    public List getQALinkList(String sheetId, String sheetRange) throws Exception{
        System.out.println(driver);
        //String paramSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
       // String paramSheetDataRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");
        //int paramExpectedResultColumn = Integer.parseInt(context.getCurrentXmlTest().getParameter("inputExpectedResultColumn"));
        ggDataList = (List<List<Object>>) SheetsQuickstart.getQALinks(sheetId ,sheetRange);
        if (ggDataList == null || ggDataList.isEmpty()) {
            System.out.println("No data found.");
        } else{
            return ggDataList;
        }
        return ggDataList;
     }

}
