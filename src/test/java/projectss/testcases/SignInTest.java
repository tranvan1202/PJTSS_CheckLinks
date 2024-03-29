package projectss.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projectss.pages.SignInPage;

public class SignInTest extends projectss.base.BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test()
    public void signIn() throws Exception {
        System.out.println(driver);
        //signInPage = new SignInPage(driver);
        signInPage = new SignInPage(driver);
        signInPage.signin();
        Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
    }

}
