package ru.siberianhealth.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/localweb.properties"
})
public interface DeviceConfig extends Config {

    @Key("hub.url")
    String hubUrl();

    @Key("selenoid.video.storage")
    String selenoidVideoStorage();

    String browser();
    String browserVersion();
    String browserSize();
}

