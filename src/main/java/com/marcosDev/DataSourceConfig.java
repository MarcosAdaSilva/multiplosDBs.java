package com.marcosDev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DataSourceConfig {
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.posts")
    @Bean
    DataSourceProperties postDbProps() {
        return new DataSourceProperties();
    }
    @Primary
    @Bean
    DataSource postsDb(DataSourceProperties DbProps) {
        return DbProps.initializeDataSourceBuilder().build();
    }
    @Primary
    @Bean
    JdbcClient postsJdbcClient(DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }
    @Bean
    DataSourceScriptDatabaseInitializer postsDbInit(DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:posts-schema.sql"));
        settings.setMode(DatabaseInitializationMode.ALWAYS);
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }

    @ConfigurationProperties(prefix = "app.datasource.comments")
    @Bean
    DataSourceProperties commentsDbProps() {
        return new DataSourceProperties();
    }

    @Bean
    DataSource commentsDb(DataSourceProperties DbProps) {
        return DbProps.initializeDataSourceBuilder().build();
    }
    @Bean
    JdbcClient commentsJdbcClient(
            @Qualifier("commentsDb") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }
    @Bean
    DataSourceScriptDatabaseInitializer commentsDbInit(
        @Qualifier("commentsDb") DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:comments-schema.sql"));
        settings.setMode(DatabaseInitializationMode.ALWAYS);
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }

}
