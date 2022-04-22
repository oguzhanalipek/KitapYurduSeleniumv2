package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class ProductPage extends Methods {

    Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct() {

        sendKeys(By.id("search-input"), "Oyuncak");
        clickElement(By.cssSelector(".common-sprite.button-search"));
    }

    public void productFav() {
        //Arama sonuçlarında 7.ürüne scroll yapılır
        scrollByIndex(By.xpath("//*[@class='product-list']/div"), 7);

        //3.Ürünü favorilere ekle
        addFavoritesInProductSearchList(3);
        //4.Ürünü favorilere ekle
        addFavoritesInProductSearchList(4);
        //5.Ürünü favorilere ekle
        addFavoritesInProductSearchList(5);
        //6.Ürünü favorilere ekle
        addFavoritesInProductSearchList(6);
        //7.Ürünü favorilere ekle
        addFavoritesInProductSearchList(7);
    }

    public void productCatalog() {
        //Puan Kataloğu butonuna tıkla
        clickElement(By.className("lvl1catalog"));
        logger.info("Puan Kataloğu sayfası açıldı");

        //Türk Klasikleri butonuna scroll yap ve tıkla
        scrollWithAction(By.cssSelector(".landing-block>a>img[title='Puan Kataloğundaki Türk Klasikleri']"));
        clickElement(By.cssSelector(".landing-block>a>img[title='Puan Kataloğundaki Türk Klasikleri']"));
        logger.info("Türk Klasikleri seçildi");

        //Oylama türünü değiştir
        selectByText(By.cssSelector(".sort>select"), "Yüksek Oylama");
        logger.info("Oylama türü 'Yüksek Oylama' olarak seçildi");
    }

    public void selectRandomProduct() {

        //Tüm kitaplar butonuna hover yap
        scrollWithAction(By.cssSelector("[class='js-ajaxCt js-bookCt']>li:nth-of-type(3)"));
        hoverElement(By.cssSelector("[class='js-ajaxCt js-bookCt']>li:nth-of-type(3)"));

        //Tüm Kitaplar > Hobi sekmesi tıklanabilir olana kadar bekle
        waitUntilElementClickable(By.cssSelector(".open-menu-ct.bookAllCategories>.subCategories>ul:nth-of-type(2)>li:nth-of-type(14)>a"));

        //Tüm Kitaplar > Hobi tıkalanır
        clickElement(By.cssSelector(".open-menu-ct.bookAllCategories>.subCategories>ul:nth-of-type(2)>li:nth-of-type(14)>a"));

        //Rastgele ürün seç
        int randomProductIndex = new Random().nextInt(16);
        clickElement(By.cssSelector("ul[class='product-grid jcarousel-skin-opencart']>li:nth-of-type(" + randomProductIndex + ")"));

        //Ürün detay sayfasına gelindiği kontrol edilir
        Assert.assertTrue("Ürün Detay Sayfası Görüntülenemiyor!", isElementVisible(By.cssSelector("div[class='price__item']")));

        //Sepete ekle butonuna scroll...
        scrollWithAction(By.cssSelector("a[id='button-cart']"));

        //Sepete ekle butonuna tıkla
        clickElement(By.cssSelector("a[id='button-cart']"));

        //Ürünün sepete eklenip eklenmediğini kontrol et
        clickElement(By.cssSelector("div[id='cart']"));
        Assert.assertTrue("Ürün sepete eklenemedi!", isElementVisible(By.id("js-cart")));
        logger.info("Ürün sepete eklendi");

    }

    public void deleteThirdProductInFavorites() {

        //Listelerim kısmına hover yap
        hoverElement(By.cssSelector("div[class='menu top my-list']"));

        //Favorilerim butonu tıkalanabilir olana kadar bekle
        waitUntilElementClickable(By.cssSelector("div[class='menu top my-list']>ul>li>div>ul>li>a[href='https://www.kitapyurdu.com/index.php?route=account/favorite&selected_tags=0']"));

        //Favorilerim butonuna tıkla
        clickElement(By.cssSelector("div[class='menu top my-list']>ul>li>div>ul>li>a[href='https://www.kitapyurdu.com/index.php?route=account/favorite&selected_tags=0']"));

        //Favori ürünlerin display modunu listeye çevirme
        String favDisplayListMode = "sprite sprite-icon-list";
        String currentDisplayMode = getAttribute(By.cssSelector("div[class='display']>a>span"), "class");

        if (currentDisplayMode.equals(favDisplayListMode)) {
            clickElement(By.cssSelector("div[class='display']>a>span "));
        }

        //Favoriler listesindeki ürünleri Eskiden-Yeniye sırala
        selectByText(By.cssSelector("div[class='sort']>select"), "Eklenme Tarihi (Eski - Yeni)");

        //Favoriler listesindeki 3.ürünü sil
        deleteProductByIndexInFavList(By.cssSelector("div[class='product-cr']"), 3, By.cssSelector("div[class='grid_2 alpha omega relative']>div[class='hover-menu']>a[data-title='Favorilerimden Sil']"));

    }

    public void shoppingCart() {


        clickElement(By.cssSelector("div[id='cart']"));
        waitUntilElementClickable(By.id("js-cart"));
        //findElement(By.cssSelector("div[id='cart']")).click();

        //Sepete git butonu görünür ise ürün vardır...
        if (isElementVisible(By.id("js-cart"))) {
            clickElement(By.id("js-cart"));

            //Sepette ürün adedinde arttırım
            String currentQuantity = getValue(By.cssSelector("input[name='quantity']"));
            int newValue = Integer.parseInt(currentQuantity);
            newValue++;
            //String newQuantity = "" + newValue;
            String newQuantity = Integer.toString(newValue);

            findElement(By.cssSelector("input[name='quantity']")).clear();
            sendKeys(By.cssSelector("input[name='quantity']"), newQuantity);

            //Miktar güncelleme iconuna tıkla
            findElement(By.cssSelector("[class='fa fa-refresh green-icon']")).click();
            waitBySeconds(1);

            //Satın al butonuna tıkla
            waitUntilElementClickable(By.cssSelector("div[class='buttons']>div>a[class='button red']"));
            clickElement(By.cssSelector("div[class='buttons']>div>a[class='button red']"));

            //Yeni adres ekleme
            clickElement(By.cssSelector("div[id='shipping-tabs']>a:nth-of-type(2)"));
            scrollWithAction(By.cssSelector("textarea[name='address']"));
            sendKeys(By.cssSelector("input[name='firstname_companyname']"), "Csdgjar");
            sendKeys(By.cssSelector("input[name='lastname_title']"), "bafsdf");
            sendKeys(By.cssSelector("select[name='zone_id']"), "İstanbul");
            clickElement(By.cssSelector("select[name='county_id']>option[value='462']"));
            sendKeys(By.cssSelector("input[name='district']"), "SOĞANLIK YENİ MAH");
            sendKeys(By.cssSelector("textarea[name='address']"), "Yeni Adres Yeni Adres Yeni Adres");
            sendKeys(By.cssSelector("input[name='postcode']"), "34483");
            sendKeys(By.cssSelector("input[name='telephone']"), "2161111111");
            sendKeys(By.cssSelector("input[name='mobile_telephone']"), "5301111111");
            scrollWithAction(By.cssSelector("button[id='button-checkout-continue']"));
            clickElement(By.cssSelector("button[id='button-checkout-continue']"));

            //KargoSection kısmı görünür ise
            if (isElementVisible(By.cssSelector("div[id='tab-shipping-companies-non']"))) {
                clickElement(By.cssSelector("button[class='button']"));
            } else {
                logger.info("Kargo metodu bölümü yüklenemedi");
            }

            //MasterPass kısmı görünür ise
            if (isElementVisible(By.cssSelector("div[class='checkMasterpass']"))) {
                logger.info("Ödeme kısmına gelindi");
            } else {
                logger.info("Ödeme metodu bölümü yüklenemedi");
            }
        } else {
            logger.info("Sepette ürün yok!");
        }
    }


}
