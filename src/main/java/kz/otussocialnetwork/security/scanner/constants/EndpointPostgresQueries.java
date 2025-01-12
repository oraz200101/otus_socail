package kz.otussocialnetwork.security.scanner.constants;

import org.intellij.lang.annotations.Language;

public class EndpointPostgresQueries {

  @Language("PostgreSQL")
  public static final String CREATE_ENDPOINT =
    "INSERT INTO endpoints (" +
      "url, " +
      "method_name, " +
      "access_roles, " +
      "request_type, " +
      "default_access_roles, " +
      "active_defaultaccess_roles, " +
      "authenticated, " +
      "permit_all" +
      ") " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  @Language("PostgreSQL")
  public static final String FIND_ENDPOINT_BY_ID =
    "SELECT id FROM endpoints WHERE id = ?";

  @Language("PostgreSQL")
  public static final String UPDATE_ENDPOINT =
    "UPDATE endpoints " +
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
    "SELECT * FROM endpoints";

  @Language("PostgreSQL")
  public static final String DELETE_ENDPOINT =
    "DELETE FROM endpoints WHERE id = ?";
}
