package com.kitapyurdu.test;

import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.pages.HomePage;
import com.kitapyurdu.pages.LoginPage;
import com.kitapyurdu.pages.ProductPage;
import org.junit.Before;
import org.junit.Test;

public class MainTest extends BaseTest {

    ProductPage productPage;
    LoginPage loginPage;
    HomePage homePage;

    @Before
    public void before() {
        productPage = new ProductPage(getDriver());
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }


    @Test
    public void mainTest() {
        loginPage.login();
        productPage.searchProduct();
        productPage.productFav();
        homePage.home();
        productPage.productCatalog();
        productPage.selectRandomProduct();
        productPage.deleteThirdProductInFavorites();
        productPage.shoppingCart();
        homePage.home();
        loginPage.logout();

    }
}
