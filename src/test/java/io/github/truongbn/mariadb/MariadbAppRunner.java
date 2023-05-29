package io.github.truongbn.mariadb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import io.github.truongbn.mariadb.config.LocalMariaDBInitializer;

@SpringBootTest
@ComponentScan(basePackages = "io.github.truongbn.mariadb")
@ConfigurationPropertiesScan(basePackages = "io.github.truongbn.mariadb")
class MariadbAppRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MariadbAppRunner.class)
                .initializers(new LocalMariaDBInitializer()).run(args);
    }
}
