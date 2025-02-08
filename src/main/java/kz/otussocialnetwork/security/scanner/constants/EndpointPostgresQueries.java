package kz.otussocialnetwork.security.scanner.constants;

import org.intellij.lang.annotations.Language;

public class EndpointPostgresQueries {

  @Language("PostgreSQL")
  public static final String CREATE_ENDPOINT =
    "INSERT INTO otus_social.endpoints (" +
      "id, " +
      "url, " +
      "method_name, " +
      "access_roles, " +
      "request_type, " +
      "default_access_roles, " +
      "active_defaultaccess_roles, " +
      "authenticated, " +
      "permit_all" +
      ") " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

  @Language("PostgreSQL")
  public static final String FIND_ENDPOINT_BY_ID =
    "SELECT * FROM otus_social.endpoints WHERE id = ?";

  @Language("PostgreSQL")
  public static final String UPDATE_ENDPOINT =
    "UPDATE otus_social.endpoints " +
      "SET url = ?, " +
      "    method_name = ?, " +
      "    access_roles = ?, " +
      "    request_type = ?, " +
      "    default_access_roles = ?, " +
      "    active_defaultaccess_roles = ?, " +
      "    authenticated = ?, " +
      "    permit_all = ? " +
      "WHERE id = ?";

  @Language("PostgreSQL")
  public static final String FIND_ENDPOINTS =
    "SELECT * FROM otus_social.endpoints";

  @Language("PostgreSQL")
  public static final String DELETE_ENDPOINT =
    "DELETE FROM otus_social.endpoints WHERE id = ?";

  @Language("PostgreSQL")
  public static final String DELETE_ENDPOINTS =
  "DELETE FROM otus_social.endpoints WHERE id in (:ids)";

  @Language("PostgreSQL")
  public static final String FIND_BY_URL_AND_TYPE =
  "SELECT * FROM otus_social.endpoints WHERE url = ? AND request_type = ?";
}
