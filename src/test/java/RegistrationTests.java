package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationTests {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://demowebshop.tricentis.com/register");
    }


    @Test
    void successfulRegistration() {
        String uniqueEmail = "user_" + UUID.randomUUID() + "@test.com";

        $("#gender-male").click();
        $("#FirstName").setValue("John");
        $("#LastName").setValue("Doe");
        $("#Email").setValue(uniqueEmail);
        $("#Password").setValue("StrongPass123!");
        $("#ConfirmPassword").setValue("StrongPass123!");
        $("#register-button").click();

        $(".result").shouldHave(text("Your registration completed")); // Success message
        $(".account").shouldHave(text(uniqueEmail)); // Email appears in header
    }

    // ⚠️ Attempt to register with an already used email
    @Test
    void registrationWithExistingEmail() {
        String existingEmail = "user@test.com"; // You must manually register this email once

        $("#gender-female").click();
        $("#FirstName").setValue("Jane");
        $("#LastName").setValue("Doe");
        $("#Email").setValue(existingEmail);
        $("#Password").setValue("Test1234!");
        $("#ConfirmPassword").setValue("Test1234!");
        $("#register-button").click();

        $(".message-error").shouldBe(visible)
                .shouldHave(text("The specified email already exists"));
    }

 


}
