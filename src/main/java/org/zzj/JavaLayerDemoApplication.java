package org.zzj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.zzj.dao.mapper")
//@ImportResource("classpath:elastic-job.xml")
//@PropertySource(value = {"file:${CONFIG_PATH}/db.properties", "file:${CONFIG_PATH}/app.properties"})
public class JavaLayerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(org.zzj.JavaLayerDemoApplication.class, args);
    }

}
