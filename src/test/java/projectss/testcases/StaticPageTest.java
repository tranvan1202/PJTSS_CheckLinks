package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.SignInPage;
import projectss.pages.StaticPage;

import static org.testng.Assert.assertTrue;

public class StaticPageTest extends projectss.base.BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }
//    @Test(priority = 1)
//    public void signIn() throws Exception {
//        System.out.println(driver);
//        signInPage = new SignInPage(driver);
//
//        SignInPage.loginThroughSession();
//        assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
//    }
    @Test(priority = 2, dataProvider = "urlList", dataProviderClass = BaseSetup.class)
    public void verifyCTALink(String param) throws Exception {
        System.out.println("URL: " + param);
        driver.get(param);
        StaticPage.verifyLinks(driver);
    }
}
