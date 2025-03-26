package kz.otussocialnetwork.repository.constants;

import org.intellij.lang.annotations.Language;

public class UserPostgresQueries {

  @Language("PostgreSQL")
  public static final String CREATE_USER =
    "INSERT INTO otus_social.users (" +
      "id, " +
      "username, " +
      "first_name, " +
      "second_name, " +
      "birth_date, " +
      "biography, " +
      "city, " +
      "password" +
      ") " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  @Language("PostgreSQL")
  public static final String FIND_USER_BY_ID =
    "SELECT * FROM otus_social.users WHERE id = ?";

  @Language("PostgreSQL")
  public static final String FIND_USER_BY_USERNAME =
    "SELECT * FROM otus_social.users WHERE username = ?";

  @Language("PostgreSQL")
  public static final String UPDATE_USER =
    "UPDATE otus_social.users " +
      "SET username =?, " +
      "first_name = ?, " +
      "second_name = ?, " +
      "birth_date = ?, " +
      "biography = ?, " +
      "city = ?, " +
      "password = ? " +
      "WHERE id = ?";

  @Language("PostgreSQL")
  public static final String DELETE_USER_BY_ID =
    "DELETE FROM otus_social.users WHERE id = ?";

  @Language("PostgreSQL")
  public static final String SEARCH_USER_BY_FIRSTNAME_AND_SECOND_NAME =
    "SELECT * FROM otus_social.users WHERE first_name LIKE ? AND second_name LIKE ?";

  @Language("PostgreSQL")
  public static final String FIND_ALL_USERS =
    "SELECT * FROM otus_social.users";

}
