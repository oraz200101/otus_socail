package kz.otussocialnetwork.migration;

import lombok.Getter;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MigrationProperties {

  @Value("${migration.file-url}")
  private String fileUrl;

  @Value("${migration.file-name}")
  private String fileName;

  @Value("${migration.is-active}")
  private boolean isActive;

  @Language("PostgreSQL")
  public static final String insertQuery = "INSERT INTO users (id, second_name, first_name, username, birth_date, city, password) VALUES (?, ?, ?, ?, ?, ?, ?);";
}
