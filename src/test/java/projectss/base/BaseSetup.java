package projectss.base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.ITestContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private WebDriver driver;
    public static Properties prop;
    public static List<List<Object>> ggDataList;
    static String driverPath = "resources\\drivers\\";

    public BaseSetup() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/projectss"
                    + "/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    //Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
    private void setDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
    }

    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case
    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        return driver;
    }

    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
    @Parameters({ "browserType"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserType) {
        try {
            // Khởi tạo driver và browser
            setDriver(browserType);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @DataProvider(name="qaURLs")
    public Object[][] splitQALinks(ITestContext context) {
        String parameter = context.getCurrentXmlTest().getParameter("qaURLs");
        String[] names = parameter.split(",");
        Object[][] returnValues = new Object[names.length][1];
        int index = 0;
        for (Object[] each : returnValues) {
            each[0] = names[index++].trim();
        }
        return returnValues;
    }
    @DataProvider(name="hrefLinks")
    public Object[][] splitHrefLinks(ITestContext context) {
        String parameter = context.getCurrentXmlTest().getParameter("hrefLinks");
        String[] names = parameter.split(",");
        Object[][] returnValues = new Object[names.length][1];
        int index = 0;
        for (Object[] each : returnValues) {
            each[0] = names[index++].trim();
        }
        return returnValues;
    }
//    @DataProvider(name="paramsVerifyTextByInputXpath")
//    public static Object[][] paramsVerifyTextByInputXpath(ITestContext context) {
//        String ggTestDataSheetRange = context.getCurrentXmlTest().getParameter("paramTestDataSheetRange");
//        String inputQALinkColumn = context.getCurrentXmlTest().getParameter("paramQALinkColumn");
//        String inputXpath = context.getCurrentXmlTest().getParameter("paramInputXpathColumn");
//        String inputExpectedResultColumn = context.getCurrentXmlTest().getParameter("paramExpectedResultColumn");
//
//        Object[][] returnParams = {{ggTestDataSheetRange,inputQALinkColumn,inputXpath,inputExpectedResultColumn}};
//        return returnParams;
//    }
    public static String paramsInputTestDataSheetRange(ITestContext context) {
        return context.getCurrentXmlTest().getParameter("paramTestDataSheetRange");
    }
    public static String paramsInputQALinkColumn(ITestContext context) {
        return context.getCurrentXmlTest().getParameter("paramQALinkColumn");
    }
    public static String paramsInputXpath(ITestContext context) {
        return context.getCurrentXmlTest().getParameter("paramInputXpathColumn");
    }
    public static String paramsInputExpectedResultColumn(ITestContext context) {
        return context.getCurrentXmlTest().getParameter("paramExpectedResultColumn");
    }
    @DataProvider(name="ggDataTestList")
    public List<List<Object>> getQALinkList(String sheetId, String sheetRange) throws Exception{
        System.out.println(driver);
        ggDataList = (List<List<Object>>) SheetsQuickstart.getQALinks(sheetId ,sheetRange);
        if (ggDataList == null || ggDataList.isEmpty()) {
            System.out.println("No data found.");
        } else{
            return ggDataList;
        }
        return ggDataList;
    }
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
