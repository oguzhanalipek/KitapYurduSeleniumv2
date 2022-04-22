package com.kitapyurdu.driver;

import com.kitapyurdu.util.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static Browser browser = new Browser();

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }

    @Before
    public void setUp() {

        logger.info("SetUp işlemi yapılıyor...");
        browser.setBrowser("https://www.kitapyurdu.com/");

    }

    @After
    public void shutDown() {

        if (getDriver() != null) {
            logger.info("Driver kapatılıyor...");
            getDriver().quit();
        }
    }

}
