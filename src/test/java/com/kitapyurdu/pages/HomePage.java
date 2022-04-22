
package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Methods {

    Logger logger = LogManager.getLogger(ProductPage.class);


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void home() {

        goHomePage();
        try {
            Assert.assertTrue(isElementVisible(By.cssSelector("div[class='content-bg content-home']")));
            logger.info("Kitap Yurdu AnaSayfası açıldı");
        } catch (Exception e) {
            logger.info("Sayfa açılamadı");
        }

    }
}
