package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.base.BaseSetup;
import projectss.pages.MetaPage;
import projectss.pages.SignInPage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MetaTest extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public MetaPage metaPage;

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

        SignInPage.signin("van.binhtran@samsung.com");
        Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }
    //Phần xử lý get Meta
    @Test(priority = 2)
    public void getMeta() throws Exception {
        metaPage = new MetaPage(driver);
        System.out.println("Title Page: " + MetaPage.MetaPageInfo.getTitleContent());
        System.out.println("metaTitle: " + MetaPage.MetaPageInfo.getMetaTitle());
        System.out.println("metaNameTwitterDescription: " + MetaPage.MetaPageInfo.getMetaNameTwitterDescription());
        System.out.println("metaNameTwitterCard: " + MetaPage.MetaPageInfo.getMetaNameTwitterCard());

        assertNotNull(MetaPage.MetaPageInfo.getTitleContent());
        assertNotNull(MetaPage.MetaPageInfo.getMetaTitle());
        assertNotNull(MetaPage.MetaPageInfo.getMetaNameTwitterDescription());
        assertNotNull(MetaPage.MetaPageInfo.getMetaNameTwitterCard());

    }

}
