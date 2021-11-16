package ru.siberianhealth.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${deviceHost}.properties"
})
public interface DeviceConfig extends Config {

    @Key("hub.url")
    String hubUrl();

    @DefaultValue("chrome")
    @Key("browser")
    String browser();
}

