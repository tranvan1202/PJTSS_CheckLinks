package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.google.common.collect.Lists;

import static org.testng.Assert.assertNotEquals;

public class MetaInformation {
    public void getMetaInformation(WebDriver driver) {
        //Get Document object after parsing the html from given url.
        String titleContent = driver.getTitle();
        String metaTitle = driver.findElement(By.xpath("//meta[@name='title']")).getAttribute("content");

        String linkCan = driver.findElement(By.xpath("//link[@rel='canonical']")).getAttribute("href");

        String metaNameKeywords = driver.findElement(By.xpath("//meta[@name='keywords']")).getAttribute("content");
        String metaNameDescription = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
        String metaNameDate = driver.findElement(By.xpath("//meta[@name='date']")).getAttribute("content");
        String metaNameSiteCode = driver.findElement(By.xpath("//meta[@name='sitecode']")).getAttribute("content");

        String metaNameTwitterCard = driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
        String metaNameTwitterSite = driver.findElement(By.xpath("//meta[@name='twitter:site']")).getAttribute("content");
        String metaNameTwitterCreator = driver.findElement(By.xpath("//meta[@name='twitter:creator']")).getAttribute("content");
        String metaNameTwitterURL = driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
        String metaNameTwitterTitle = driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
        String metaNameTwitterDescription = driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
        String metaNameTwitterImage = driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");

        String metaPropertyOgURL = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
        String metaPropertyOgImage = driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content");
        String metaPropertyOgType = driver.findElement(By.xpath("//meta[@property='og:type']")).getAttribute("content");
        String metaPropertyOgSiteName = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
        String metaPropertyOgLocale = driver.findElement(By.xpath("//meta[@property='og:locale']")).getAttribute("content");
        String metaPropertyOgTitle = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
        String metaPropertyOgDescription = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");
        String metaPropertyOgCountryName = driver.findElement(By.xpath("//meta[@property='og:country-name']")).getAttribute("content");

        String metaPropertyName = driver.findElement(By.xpath("//meta[@property='name']")).getAttribute("content");
        String metaPropertyImage = driver.findElement(By.xpath("//meta[@property='image']")).getAttribute("content");
        String metaPropertyUrl = driver.findElement(By.xpath("//meta[@property='url']")).getAttribute("content");
        String metaPropertyDescription = driver.findElement(By.xpath("//meta[@property='description']")).getAttribute("content");
        String metaPropertyKeywords = driver.findElement(By.xpath("//meta[@property='keywords']")).getAttribute("content");

        assertNotEquals("", metaNameSiteCode);
        assertNotEquals("", metaNameTwitterDescription);
        assertNotEquals("", metaNameTwitterCard);

        //Print description.
        System.out.println("<!-- SEO --> ");
//        System.out.println("Page Title: " + titleContent);
//        System.out.println("metaNameTitle: " + metaTitle);
//        System.out.println("Link canonical: " + linkCan);
//
//        System.out.println("metaNameKeywords: " + metaNameKeywords);
//        System.out.println("metaNameDescription: " + metaNameDescription);
//        System.out.println("metaNameDate: " + metaNameDate);
//        System.out.println("metaNameSiteCode: " + metaNameSiteCode);
//
//        System.out.println("metaNameTwitterCard: " + metaNameTwitterCard);
//        System.out.println("metaNameTwitterSite: " + metaNameTwitterSite);
//        System.out.println("metaNameTwitterCreator: " + metaNameTwitterCreator);
//        System.out.println("metaNameTwitterURL: " + metaNameTwitterURL);
//        System.out.println("metaNameTwitterTitle: " + metaNameTwitterTitle);
//        System.out.println("metaNameTwitterDescription: " + metaNameTwitterDescription);
//        System.out.println("metaNameTwitterImage: " + metaNameTwitterImage);
//
//        System.out.println("metaPropertyOgURL: " + metaPropertyOgURL);
//        System.out.println("metaPropertyOgImage: " + metaPropertyOgImage);
//        System.out.println("metaPropertyOgType: " + metaPropertyOgType);
//        System.out.println("metaPropertyOgSiteName: " + metaPropertyOgSiteName);
//        System.out.println("metaPropertyOgLocale: " + metaPropertyOgLocale);
//        System.out.println("metaPropertyOgTitle: " + metaPropertyOgTitle);
//        System.out.println("metaPropertyOgDescription: " + metaPropertyOgDescription);
//        System.out.println("metaPropertyOgCountryName: " + metaPropertyOgCountryName);
//
//        System.out.println("metaPropertyName: " + metaPropertyName);
//        System.out.println("metaPropertyImage: " + metaPropertyImage);
//        System.out.println("metaPropertyUrl: " + metaPropertyUrl);
//        System.out.println("metaPropertyDescription: " + metaPropertyDescription);
//        System.out.println("metaPropertyKeywords: " + metaPropertyKeywords);
    }
}
