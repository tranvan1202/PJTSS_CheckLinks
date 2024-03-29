package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class htmlUnitTest {
    public static void main(String[] args) {
        // Creating a new instance of the HTML unit driver

        WebDriver driver = new HtmlUnitDriver();

        // Navigate to Google
        driver.get("http://www.samsung.com/sg");

        // This code will print the page title
        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}