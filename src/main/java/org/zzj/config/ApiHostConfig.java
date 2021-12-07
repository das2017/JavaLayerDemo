package org.zzj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基础配置类
 * 读 application.yml
 */
@Component
@Data
@ConfigurationProperties(prefix = "apiHost")
public class ApiHostConfig {
    public String userApi;
    public String goodsApi;
    public String orderApi;
    public String marketApi;
    public String accountApi;
}
