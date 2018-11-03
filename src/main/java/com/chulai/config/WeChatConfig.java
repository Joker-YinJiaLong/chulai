package com.chulai.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wechat")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WeChatConfig {

    private String sessionHost;
    private String secret;
    private String appid;
    private String grantType;
}
