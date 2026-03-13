package tests;

import base.BaseTest;
import pages.SearchPage;

import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @Test
    public void searchWithProductName() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("Shoes");
    }

    @Test
    public void searchWithBrand() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("Nike");
    }

    @Test
    public void searchWithCategory() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("Men T Shirts");
    }

    @Test
    public void searchWithPartialKeyword() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("sho");
    }

    @Test
    public void searchUppercase() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("SHOES");
    }

    @Test
    public void searchLowercase() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("shoes");
    }

    @Test
    public void searchMixedCase() {

        SearchPage search = new SearchPage(driver);

        search.searchProduct("ShOeS");
    }
}