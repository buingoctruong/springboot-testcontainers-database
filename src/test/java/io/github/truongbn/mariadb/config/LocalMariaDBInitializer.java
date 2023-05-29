package io.github.truongbn.mariadb.config;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class LocalMariaDBInitializer
        implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NotNull ConfigurableApplicationContext context) {
        mariaDbLocalSetup(context);
    }

    private void mariaDbLocalSetup(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        DockerImageName dockerImageName = DockerImageName.parse("mariadb:10.5.5");
        MariaDBContainer<?> mariaDB = new MariaDBContainer<>(dockerImageName)
                .withDatabaseName("appDB").withUsername("admin").withPassword("password")
                .withInitScript("MariaDbLocalSetupScript.sql");
        mariaDB.start();
        String jdbcUrl = mariaDB.getJdbcUrl();
        setProperties(environment, "mariadb.jdbc-url", jdbcUrl);
    }

    private void setProperties(ConfigurableEnvironment environment, String name, Object value) {
        MutablePropertySources sources = environment.getPropertySources();
        PropertySource<?> source = sources.get(name);
        if (source == null) {
            source = new MapPropertySource(name, new HashMap<>());
            sources.addFirst(source);
        }
        ((Map<String, Object>) source.getSource()).put(name, value);
    }
}
