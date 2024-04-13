package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.SignInPage;
import projectss.pages.CommonPage;

import static org.testng.Assert.assertTrue;

public class CommonPageTest extends BaseSetup {
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
    @Test(priority = 2, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyAssetPath(String param) throws Exception {
        commonPage = new CommonPage(driver);
        System.out.println("URL: " + param);
        driver.get(param);
        CommonPage.getWrongSiteCodeAssetPath(driver,"/ph");
    }
    @Test(priority = 3, dataProvider = "qaURLs", dataProviderClass = BaseSetup.class)
    public void verifyBrokenInputLinks(String param) throws Exception {
        //commonPage = new CommonPage(driver);
        assertTrue(CommonPage.isInputLinkBroken(param));
    }
}
