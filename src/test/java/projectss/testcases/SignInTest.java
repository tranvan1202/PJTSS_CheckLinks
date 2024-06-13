package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.pages.ProductDetailPage;
import projectss.pages.SignInPage;

public class SignInTest extends projectss.base.BaseSetup {
    public WebDriver driver;
    public SignInPage signInPage;
    public ProductDetailPage productDetailPage;
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test()
    public void signIn() throws Exception {
        System.out.println(driver);
        signInPage = new SignInPage(driver);
        productDetailPage = SignInPage.loginAndGoToPDP();
        //Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }

}
