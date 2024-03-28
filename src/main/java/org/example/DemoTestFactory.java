package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

public class DemoTestFactory {
    private String url="";

    @Factory(dataProvider = "urls", dataProviderClass = xxx.class)
    public DemoTestFactory(String url) {
        this.url = url;
    }

    @Test(dataProvider = "urls", dataProviderClass = DemoTestFactory.xxx.class)
    public void something(String url) {
        WebDriver driver = new ChromeDriver();
        System.out.println(url);
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.getTitle();
        driver.close();
    }

//    @Test(dataProvider = "someOtherData", dataProviderClass = DemoTestFactory.xxx.class)
//    public void somethingElse(int a, int b) {
//        System.out.println(String.format("%s, %d, %d", url, a, b));
//    }

    public static class xxx {
        @DataProvider
        public static Iterator<Object[]> urls() {
            String[] urls = {
                    "https://www.google.com/",
                    "https://inbox.google.com/",
                    "https://calendar.google.com/",
                    "https://drive.google.com/"
            };
            return Arrays.stream(urls).map(s -> new Object[]{s}).iterator();
        }

//        @DataProvider
//        public static Object[][] someData() {
//            return new Object[][]{
//                    {1, 2},
//                    {3, 4}
//            };
//        }
//
//        @DataProvider
//        public static Object[][] someOtherData() {
//            return new Object[][]{
//                    {4, 3},
//                    {2, 1}
//            };
//        }
    }
}
