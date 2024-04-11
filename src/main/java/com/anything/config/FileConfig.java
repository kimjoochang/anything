package com.anything.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
@Setter
public class FileConfig {
    public String fileMaxSize;
}

