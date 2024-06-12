package projectss.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.ITestContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private WebDriver driver;
    public static Properties prop;
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
    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver(appURL);
        }
    }

    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case
    private static WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
    @Parameters({ "browserType", "loginURL" })
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String loginURL) {
        try {
            // Khởi tạo driver và browser
            setDriver(browserType, loginURL);
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


    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
