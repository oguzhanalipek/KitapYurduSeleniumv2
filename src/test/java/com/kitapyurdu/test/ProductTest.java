package com.kitapyurdu.test;

import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.pages.HomePage;
import com.kitapyurdu.pages.LoginPage;
import com.kitapyurdu.pages.ProductPage;
import org.junit.Before;
import org.junit.Test;

public class ProductTest extends BaseTest {
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
    public void productTest() {
        loginPage.login();
        productPage.searchProduct();
        productPage.productFav();
        homePage.home();
        productPage.productCatalog();
        productPage.selectRandomProduct();
        productPage.deleteThirdProductInFavorites();
        productPage.shoppingCart();
    }

    @Test
    public void searchProductTest() {
        productPage.searchProduct();
    }

    @Test
    public void favProductTest() {
        loginPage.login();
        productPage.searchProduct();
        productPage.productFav();
    }

    @Test
    public void deleteFavInSearchListTest() {
        loginPage.login();
        productPage.searchProduct();
        productPage.deleteFavoritesInProductSearchList(3);
    }

    @Test
    public void puanKataloguTest() {
        productPage.productCatalog();
    }

    @Test
    public void selectRandomProductTest() {

        loginPage.login();
        productPage.selectRandomProduct();
    }

    @Test
    public void deleteThirdProductInFavListTest() {
        loginPage.login();
        productPage.deleteThirdProductInFavorites();

    }

    @Test
    public void shoppingCartTest() {
        loginPage.login();
        productPage.shoppingCart();
    }


}
