package sv.edu.udb.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Objects;

@Getter
@Configuration
public class EnvironmentDbConfig {

    private final String dbDriver;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    public EnvironmentDbConfig(final @Value("${db.driver}") String dbDriver,
                               final @Value("${db.url}") String dbUrl,
                               final @Value("${db.username}") String dbUsername,
                               final @Value("${db.password}") String dbPassword) {
        this.dbDriver = Objects.requireNonNull(dbDriver);
        this.dbUrl = Objects.requireNonNull(dbUrl);
        this.dbUsername = Objects.requireNonNull(dbUsername);
        this.dbPassword = Objects.requireNonNull(dbPassword);
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(this.dbDriver)
                .url(this.dbUrl)
                .username(this.dbUsername)
                .password(this.dbPassword)
                .build();
    }




   /* private final Environment environment;
    public EnvironmentDbConfig(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
    }
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(environment.getProperty("db.driver"))
                .url(environment.getProperty("db.url"))
                .username(environment.getProperty("db.username"))
                .password(environment.getProperty("db.password"))
                .build();
    }
    public String getDbDriver() {
        return environment.getProperty("db.driver");
    }
    public String getDbUrl() {
        return environment.getProperty("db.url");
    }
    public String getDbUsername() {
        return environment.getProperty("db.username");
    }
    public String getDbPassword() {
        return environment.getProperty("db.password");
    }*/


}
