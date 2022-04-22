package com.kitapyurdu.util;

import com.kitapyurdu.driver.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Browser {

    public void setBrowser(String url) {

        //Chrome driver'ın dizinini belirttik
        String driverName = "webdriver.chrome.driver";
        String drivePath = "src/test/resources/chromedriver.exe";
        System.setProperty(driverName, drivePath);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        ChromeOptions chromeOptions = new ChromeOptions();

        //Browser'ı test modunda çalıştırır
        chromeOptions.addArguments("test-type");
        //Popup'ları kapatır
        chromeOptions.addArguments("disable-popup-blocking");
        //Güvenlik sertifakası hatası varsa görmezden gelir
        chromeOptions.addArguments("ignore-certificate-errors");
        //Dil çeviri penceresini kapatır
        chromeOptions.addArguments("disable-translate");
        //Eklentileri devre dışı bırakır
        chromeOptions.addArguments("disable-extensions");
        //Bildirimleri devre dışı bırakır
        chromeOptions.addArguments("--disable-notifications");
        //Otomatik şifre kaydetme seçeneğini kapatır.
        chromeOptions.addArguments("disable-automatic-password-saving");
        //Bilgi ekranını kapatır
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-plugins");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setExperimentalOption("w3c", false);

        //Chrome ayarlarını Selenium ayarlarına dönüştürüyoruz
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        //Driver set işlemi...
        BaseTest.setDriver(new ChromeDriver(chromeOptions));

        BaseTest.getDriver().navigate().to(url);
        BaseTest.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
}
