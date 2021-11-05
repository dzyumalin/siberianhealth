package ru.siberianhealth.pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.siberianhealth.tests.TestData.CHOOSE_DELIVERY;

public class AccessCookies {

    public void cookieUsageAgreement() {
        $(".top-informer__btn").click();
    }

    public void chooseDelivery() {
        $$(".top-informer__controls").findBy(visible)
                .$(byText(CHOOSE_DELIVERY))
                .click();
    }

}
