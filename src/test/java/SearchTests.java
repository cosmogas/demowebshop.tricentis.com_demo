package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.WebDriverRunner;


import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @Test
    void searchReturnsResults() {
        // Open homepage
        open("https://demowebshop.tricentis.com");

        // Search for "laptop"
        $("#small-searchterms").setValue("laptop").pressEnter();

        // Assert that results are shown
        $(".product-grid").shouldBe(Condition.visible);
    }

    @Test
    void emptySearchShowsValidationMessage() {
        // Open homepage
        open("https://demowebshop.tricentis.com");

        // Trigger empty search
        $("#small-searchterms").setValue("").pressEnter();

        // Switch to alert dialog and check its text
        WebDriverRunner.getWebDriver().switchTo().alert().accept(); // or .getText() to check message
    }



    @Test
    void searchReturnsNoResultsForNonsenseQuery() {
        // Open homepage
        open("https://demowebshop.tricentis.com");

        // Search with gibberish
        $("#small-searchterms").setValue("asdfghqwertyzzz").pressEnter();

        // Assert that the no result message is displayed
        $(".search-results").shouldBe(Condition.visible)
                .shouldHave(Condition.text("No products were found that matched your criteria."));
    }
}

