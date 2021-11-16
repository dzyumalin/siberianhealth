package ru.siberianhealth.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.siberianhealth.config.Project;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllureAttachments {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] addPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId, String url) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + url
                + "' type='video/mp4'></video></body></html>";
    }

    public static void addAttachments() {
        String sessionId;
        sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        addScreenshotAs("Last screenshot");
        addPageSource();
        closeWebDriver();
    }
}
