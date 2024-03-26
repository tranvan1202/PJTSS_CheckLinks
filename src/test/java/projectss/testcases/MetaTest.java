package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;

import static org.testng.Assert.assertNotEquals;
import static projectss.pages.MetaPage.MetaPageInfo.metaNameTwitterDescription;

public class MetaTest extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public MetaPage metaPage;
    public MetaPage.MetaPageInfo
    @BeforeClass
    public void setUp() {
        // Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
        driver = getDriver();
    }

    //SignIn mặc định
    @Test(priority = 1)
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);

        signInPage.signin("van.binhtran@samsung.com");
        Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Phần xử lý get Meta
    @Test(priority = 2)
    public void getMeta() throws Exception {
        metaPage = new MetaPage(driver);
        metaPage.getMetaInfo();
        assertNotEquals("", metaPage.getMetaInfo());
        assertNotEquals("", metaNameTwitterDescription);
        assertNotEquals("", metaNameTwitterCard);

    }

}
