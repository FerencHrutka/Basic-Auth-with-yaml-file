package com.sec.entity;

import com.sec.service.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@PropertySource(value = "classpath:appUserList.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "constants")
public class AppUserProperties {

    private List<AppUser> appUserList;

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }
}
