package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Lists;

public class MetaInformation {
    public void getMetaInformation(WebDriver driver, ArrayList arrURL) {
        //Get Document object after parsing the html from given url.
        for (int i = 0; i < arrURL.size(); i++) {
            String titleContent = driver.getTitle();
            String metaTitle = driver.findElement(By.xpath("//meta[@name='title']")).getAttribute("content");
            String metaOGDescription = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");

            //Print description.
            System.out.println("Page Title: " + titleContent);
            System.out.println("Meta Title: " + metaTitle);
            System.out.println("OG Meta Description: " + metaOGDescription);
        }
    }
}
