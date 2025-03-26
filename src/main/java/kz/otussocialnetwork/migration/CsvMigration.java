package kz.otussocialnetwork.migration;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CsvMigration {
  private final MigrationProperties migrationProperties;
  private final JdbcTemplate        jdbcTemplate;

  @PostConstruct
  public void importCsvToDatabase() {
    if (migrationProperties.isActive()) {
      String         csvFile   = migrationProperties.getFileUrl() + migrationProperties.getFileName();
      String         sql       = MigrationProperties.insertQuery;
      List<Object[]> batchArgs = new ArrayList<>();

      try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        br.readLine();
        String line;

        while ((line = br.readLine()) != null) {
          String[] values = line.split(",");

          String   fullName  = values[0];
          String[] nameParts = fullName.split(" ");

          UUID      id         = UUID.randomUUID();
          String    secondName = nameParts[0];
          String    firstName  = nameParts[1];
          String    username   = secondName + "_" + firstName + "_" + id;
          LocalDate birthday   = getRandomBirthday(Integer.parseInt(values[1]));
          String    city       = values[2];
          String    password   = UUID.randomUUID().toString();

          batchArgs.add(new Object[]{id, secondName, firstName, username, birthday, city, password});

          if (batchArgs.size() == 1000) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
            batchArgs.clear();
          }
        }

        if (!batchArgs.isEmpty()) {
          jdbcTemplate.batchUpdate(sql, batchArgs);
        }

        System.out.println("Данные успешно загружены в PostgreSQL!");
      } catch (IOException e) {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
      }
    }
  }

  private static LocalDate getRandomBirthday(int age) {
    LocalDate startDate = LocalDate.now().minusYears(age).withDayOfYear(1);
    LocalDate endDate   = LocalDate.now().minusYears(age).withMonth(12).withDayOfMonth(31);

    long startEpochDay = startDate.toEpochDay();
    long endEpochDay   = endDate.toEpochDay();

    long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
    return LocalDate.ofEpochDay(randomDay);
  }
}
