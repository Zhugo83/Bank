package com.hugo.accounts.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "account")
@Data
@RefreshScope
public class AccountConfig {
    private String name;
    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activesBranches;
}
