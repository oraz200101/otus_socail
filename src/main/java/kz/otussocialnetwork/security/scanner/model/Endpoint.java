package kz.otussocialnetwork.security.scanner.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import kz.otussocialnetwork.model.enums.Role;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class Endpoint implements Serializable {
  public UUID        id;
  public String      url;
  public String      methodName;
  public RequestType requestType;
  public boolean     activeDefaultAccessRoles = false;
  public boolean     authenticated            = false;
  public boolean     permitAll                = false;


  public Set<Role> defaultAccessRoles = new HashSet<>();
  public Set<Role> accessRoles        = new HashSet<>();

  public static final class PgFields {
    public static final String id                       = "id";
    public static final String url                      = "url";
    public static final String methodName               = "method_name";
    public static final String accessRoles              = "access_roles";
    public static final String requestType              = "request_type";
    public static final String defaultAccessRoles       = "default_access_roles";
    public static final String activeDefaultAccessRoles = "active_defaultaccess_roles";
    public static final String authenticated            = "authenticated";
    public static final String permitAll                = "permit_all";
  }

  @Component
  public static final class EnpointRowMapper implements RowMapper<Endpoint> {

    @Override public Endpoint mapRow(ResultSet rs, int rowNum) throws SQLException {
      Endpoint endpoint = new Endpoint();

      endpoint.id                       = rs.getObject(PgFields.id, UUID.class);
      endpoint.url                      = rs.getString(PgFields.url);
      endpoint.methodName               = rs.getString(PgFields.methodName);
      endpoint.requestType              = RequestType.valueOf(rs.getString(PgFields.requestType));
      endpoint.authenticated            = rs.getBoolean(PgFields.authenticated);
      endpoint.activeDefaultAccessRoles = rs.getBoolean(PgFields.activeDefaultAccessRoles);
      endpoint.permitAll                = rs.getBoolean(PgFields.permitAll);
      endpoint.accessRoles              = getRoles(rs, PgFields.accessRoles);
      endpoint.defaultAccessRoles       = getRoles(rs, PgFields.defaultAccessRoles);

      return endpoint;
    }

    private Set<Role> getRoles(ResultSet rs, @NonNull String fieldName) throws SQLException {
      return Arrays.stream(
                     (String[]) rs.getArray(fieldName)
                                  .getArray())
                   .map(Role::valueOf)
                   .collect(Collectors.toSet());
    }
  }

}