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
    public static WebDriver driver;
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

    public static List getQALinkList(String sheetId, String sheetRange) throws Exception{
        System.out.println(driver);
//         String paramSheetID = context.getCurrentXmlTest().getParameter("ggSpreadSheetID");
//         String paramSheetRange = context.getCurrentXmlTest().getParameter("ggSpreadSheetRange");

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
     public static String actualTextFromInputtedElement(By inputXpath) {
         //By inputXpath = By.xpath(context.getCurrentXmlTest().getParameter("inputXpath"));
         WebDriverWait wait60s = new WebDriverWait(driver, Duration.ofSeconds(60));
         String actualElementText = "";
         System.out.println("QA link");
         //For qua các row data trong Google Sheet
         for (List row : ggDataList) {
             System.out.printf("%s,%s\n", row.get(0), row.get(5));
             driver.get((String) row.get(5));
             //Đợi Element text
             wait60s.until(ExpectedConditions.visibilityOfElementLocated(inputXpath));

             //Lưu Elements List khi ng dùng input Xpath
             List<WebElement> inputedElement = driver.findElements(inputXpath);
             boolean isInputedElementEmpty = inputedElement.size() > 0;
             if (isInputedElementEmpty) {
                 // Element is present
                 // For qua Elements List lấy được 1 String
                 for (WebElement webInputedElementText : inputedElement) {
                     Document doc = Jsoup.parse(webInputedElementText.getAttribute("innerHTML"));
                     actualElementText = doc.text();
                     return actualElementText;
                 }
             } else {
                 // Element is not present
                 System.out.println("No " + row.get(0) + " URL lỗi ko có Element này trên page ");
             }
         }
         return actualElementText;
     }

}
