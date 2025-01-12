package kz.otussocialnetwork.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PostgresConfiguration {

  @Value("${spring.pg-properties.url}")
  private String url;

  @Value("${spring.pg-properties.username}")
  private String username;

  @Value("${spring.pg-properties.password}")
  private String password;

  @Value("${spring.pg-properties.schema}")
  private String schema;

  @Value("${spring.pg-properties.hikari.pool-name}")
  private String poolName;

  @Value("${spring.pg-properties.hikari.maximum-pool-size}")
  private int maximumPoolSize;

  @Value("${spring.pg-properties.hikari.minimum-idle}")
  private int minimumIdle;

  @Value("${spring.pg-properties.hikari.idle-timeout}")
  private int idlTimeout;

  @Value("${spring.pg-properties.hikari.connection-timeout}")
  private int connectionTimeout;

  @Bean
  public DataSource dataSource() {
    HikariConfig hikariConfig = new HikariConfig();

    hikariConfig.setJdbcUrl(url);
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);
    hikariConfig.setPoolName(poolName);
    hikariConfig.setSchema(schema);
    hikariConfig.setMaximumPoolSize(maximumPoolSize);
    hikariConfig.setMinimumIdle(minimumIdle);
    hikariConfig.setIdleTimeout(idlTimeout);
    hikariConfig.setConnectionTimeout(connectionTimeout);

    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

}
