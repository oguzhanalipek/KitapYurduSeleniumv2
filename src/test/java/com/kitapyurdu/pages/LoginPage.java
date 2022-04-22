package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Methods {

    Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        //Kitapyurdu anasayfasından Login butonuna tıklar
        clickElement(By.cssSelector(".menu-top-button.login>a"));

        waitUntilPresence(By.cssSelector(".login-container"));

        //Giriş ekranında Email alanına değer gönder
        sendKeys(By.id("login-email"), "oguzhan.alipek@testinium.com");

        //Eğer popUp görünür ise kapat
        if (isElementVisible(By.cssSelector(".popupContainer.show"))) {
            clickElement(By.cssSelector(".popupContainer.show>div[class='popupCloseIcon']"));
        }

        //Giriş ekranında Password alanına değer gönder
        sendKeys(By.id("login-password"), "1q2w3e4r5t");

        //Giriş butonuna tıkla
        clickElement(By.cssSelector(".ky-form-buttons>button"));

        //Login işlemini giriş yaptıktan sonra gelen bir elementin gözüküp gözükmediği ile kontrol et
        Assert.assertTrue("Giriş işlemi doğrulanamadı!", isElementVisible(By.cssSelector(".common-sprite>b")));
        logger.info("Giriş işlemi başarıyla yapıldı");


    }

    public void logout() {

        hoverElement(By.cssSelector("div[class='menu top login']>ul>li>a[class='common-sprite"));

        try {
            scrollWithAction(By.xpath("//div[@class='menu top login']/ul/li/a[@class='common-sprite']"));
            clickElement(By.xpath("//div[@class='menu top login']/ul/li/div/ul/li[4]"));
            logger.info("Kullanıcı oturumu kapatıldı");
        } catch (Exception e) {
            logger.info("Kullanıcı oturumu kapatılamadı!");
        }
    }
}
