package com.hugo.accounts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Setter
@Getter
public class Properties {
    private String name;
    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activesBranches;

    public Properties(String name, String msg, String buildVersion, Map<String, String> mailDetails, List<String> activesBranches) {
        this.name = name;
        this.msg = msg;
        this.buildVersion = buildVersion;
        this.mailDetails = mailDetails;
        this.activesBranches = activesBranches;
    }
}