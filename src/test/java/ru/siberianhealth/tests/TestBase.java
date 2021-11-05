package ru.siberianhealth.tests;

import ru.siberianhealth.config.Project;
import ru.siberianhealth.drivers.WebDriver;
import ru.siberianhealth.helpers.AllureAttachments;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        WebDriver.configure();
        System.out.println(Project.deviceConfig.hubUrl());
    }

    @AfterEach
    public void allureAttachments() {
        AllureAttachments.addAttachments();
        closeWebDriver();
    }
}
