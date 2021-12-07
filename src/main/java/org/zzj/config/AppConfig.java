package org.zzj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    //*system变量*//
    @Value("${system.name}")
    public String system_name;
    @Value("${system.charset}")
    public String system_charset;
}