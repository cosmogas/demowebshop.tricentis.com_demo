package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.CollectionCondition;


import static com.codeborne.selenide.Selenide.*;

@Epic("Product Catalog")
@Feature("Browsing Products")
public class ProductCatalogTests {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 8000;
    }

    @BeforeEach
    void openHomePage() {
        open("https://demowebshop.tricentis.com/");
    }

    @Test
    @Story("Open Catalog Page")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("UI: Open Books category")
    @Description("Open Books category and check that products are displayed")
    void openBooksCategory() {
        $$(".top-menu a").findBy(Condition.text("Books")).click();
        $$(".product-item").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    @Story("Product Info Visibility")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("UI: Product name and price are visible")
    @Description("Check that each product has a name and price displayed")
    void productNameAndPriceVisible() {
        $$(".top-menu a").findBy(Condition.text("Books")).click();
        $$(".product-item").forEach(product -> {
            product.$(".product-title").shouldBe(Condition.visible);
            product.$(".prices").shouldBe(Condition.visible);
        });
    }

    @Test
    @Story("Product Page Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("UI: Open product details")
    @Description("Click on a product and verify that product detail page opens")
    void openProductDetailsPage() {
        $$(".top-menu a").findBy(Condition.text("Books")).click();
        $$(".product-item").first().$(".product-title a").click();
        $(".product-name").shouldBe(Condition.visible);
    }
}
