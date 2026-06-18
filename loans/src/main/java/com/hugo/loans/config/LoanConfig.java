package com.hugo.loans.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "ms-loans")
@Getter
@Setter
@ToString
@RefreshScope
public class LoanConfig {
    private String msg;
    private String buildVersion;
    private Map<String,String> mailDetails;
    private List<String> activeBranches;
}