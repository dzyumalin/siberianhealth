package ru.siberianhealth.drivers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.siberianhealth.config.Project;

public class WebDriver {

    public static void configure() {
        Configuration.browserVersion = Project.deviceConfig.browserVersion();
        Configuration.browser = Project.deviceConfig.browser();
        Configuration.browserSize = Project.deviceConfig.browserSize();
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.remote = Project.deviceConfig.hubUrl();

        Configuration.browserCapabilities = capabilities;
    }

}
