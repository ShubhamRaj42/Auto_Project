package tests;

import base.BaseTest;
import pages.NegativeSearchPage;

import org.testng.annotations.Test;

public class NegativeSearchTests extends BaseTest {

    @Test
    public void randomTextSearch() {

        NegativeSearchPage page = new NegativeSearchPage(driver);

        page.invalidSearch("asdfgh");
    }

    @Test
    public void specialCharactersSearch() {

        NegativeSearchPage page = new NegativeSearchPage(driver);

        page.invalidSearch("@@@###");
    }

    @Test
    public void numbersOnlySearch() {

        NegativeSearchPage page = new NegativeSearchPage(driver);

        page.invalidSearch("12345");
    }

    @Test
    public void sqlInjectionSearch() {

        NegativeSearchPage page = new NegativeSearchPage(driver);

        page.invalidSearch("' OR 1=1 --");
    }

    @Test
    public void scriptTagSearch() {

        NegativeSearchPage page = new NegativeSearchPage(driver);

        page.invalidSearch("<script>alert()</script>");
    }
}