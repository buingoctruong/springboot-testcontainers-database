package io.github.truongbn.mariadb.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@AutoConfiguration
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@EnableJpaRepositories(basePackages = "io.github.truongbn.mariadb.repository",
        entityManagerFactoryRef = "mariadbEntityManager",
        transactionManagerRef = "mariadbTransactionManager")
@ConditionalOnMissingBean(
        name = {"mariadbDataSource", "mariadbEntityManager", "mariadbTransactionManager"})
public class MariaDBConfig {
    @Bean
    @ConfigurationProperties(prefix = "mariadb")
    public HikariDataSource mariadbDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mariadbEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            HikariDataSource mariadbDataSource) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("database-platform", "org.hibernate.dialect.MariaDB10Dialect");
        return entityManagerFactoryBuilder.dataSource(mariadbDataSource)
                .packages("io.github.truongbn.mariadb.entity").persistenceUnit("mariadbDataSource")
                .properties(jpaProperties).build();
    }

    @Bean
    public PlatformTransactionManager mariadbTransactionManager(
            EntityManagerFactory mariadbEntityManager) {
        return new JpaTransactionManager(mariadbEntityManager);
    }
}
