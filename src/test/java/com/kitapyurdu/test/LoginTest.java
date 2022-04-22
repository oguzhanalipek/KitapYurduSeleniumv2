package com.kitapyurdu.test;

import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Before
    public void before() {
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginTest() {
        loginPage.login();
    }

    @Test
    public void logoutTest() {
        loginPage.login();
        loginPage.logout();
    }
}
