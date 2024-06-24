package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.CommonPage;

import static org.testng.Assert.assertTrue;

public class CommonPageTest extends projectss.base.BaseSetup {
    private WebDriver driver;
    public CommonPage commonPage;
    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }
//    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
//    public void verifyAssetPath(String param) throws Exception {
//        commonPage = new CommonPage(driver);
//        System.out.println("URL: " + param);
//        driver.get(param);
//        commonPage.getWrongSiteCodeAssetPath(driver,"/ph");
//    }
    @Test(priority = 1, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
    public void verifyBrokenInputLinks(String param) {
        commonPage = new CommonPage(driver);
        assertTrue(commonPage.isInputLinkBroken(param));
        //assertTrue(CommonPage.isGetLinkBroken(commonPage.getHrefFromOrginalURL(param)));
    }
}
