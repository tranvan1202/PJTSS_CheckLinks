package org.example;


import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestVerifyCard {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.samsung.com/ph/offer/online/2024/hot-summer-deals/");
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        WebElement productCards = driver.findElement(By.xpath("//div[@class='feature-column-carousel__carousel']"));


    }

}
