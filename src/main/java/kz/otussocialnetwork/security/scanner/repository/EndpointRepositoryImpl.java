package kz.otussocialnetwork.security.scanner.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.CREATE_ENDPOINT;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.DELETE_ENDPOINT;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.FIND_ENDPOINTS;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.FIND_ENDPOINT_BY_ID;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.UPDATE_ENDPOINT;

@Repository
@RequiredArgsConstructor
public class EndpointRepositoryImpl implements EndpointRepository {
  private final JdbcTemplate jdbcTemplate;

  @Override public Optional<Endpoint> findById(@NonNull UUID id) {
    Endpoint endpoint = jdbcTemplate.queryForObject(
      FIND_ENDPOINT_BY_ID,
      Endpoint.EnpointRowMapper.of(),
      id
    );

    return Optional.ofNullable(endpoint);
  }

  @Override public List<Endpoint> findAll() {
    return jdbcTemplate.query(FIND_ENDPOINTS, Endpoint.EnpointRowMapper.of());
  }

  @Transactional
  @Override public @NonNull Endpoint create(@NonNull Endpoint endpoint) {
    jdbcTemplate.update(
      CREATE_ENDPOINT,
      endpoint.id,
      endpoint.url,
      endpoint.accessRoles.stream().map(Enum::name).toArray(),
      endpoint.requestType.name(),
      endpoint.defaultAccessRoles.stream().map(Enum::name).toArray(),
      endpoint.activeDefaultAccessRoles,
      endpoint.authenticated,
      endpoint.permitAll
    );

    return endpoint;
  }

  @Transactional
  @Override public @NonNull Endpoint update(@NonNull Endpoint endpoint) {
    jdbcTemplate.update(
      UPDATE_ENDPOINT,
      endpoint.url,
      endpoint.accessRoles.stream().map(Enum::name).toArray(),
      endpoint.requestType.name(),
      endpoint.defaultAccessRoles.stream().map(Enum::name).toArray(),
      endpoint.authenticated,
      endpoint.authenticated,
      endpoint.permitAll,
      endpoint.id
    );

    return endpoint;
  }

  @Override public void deleteById(@NonNull UUID id) {
    jdbcTemplate.update(DELETE_ENDPOINT, id);
  }

  @Override
  public void createListBatch(@NonNull List<Endpoint> endpoints) {
    final int batchSize = 500;

    for (int i = 0; i < endpoints.size(); i += batchSize) {
      List<Endpoint> batch = endpoints.subList(i, Math.min(i + batchSize, endpoints.size()));

      jdbcTemplate.batchUpdate(CREATE_ENDPOINT, batch, batch.size(), (ps, endpoint) -> {
        ps.setObject(1, endpoint.id);
        ps.setString(2, endpoint.url);
        ps.setString(3, endpoint.methodName);
        ps.setArray(4, ps.getConnection().createArrayOf(
          "text", endpoint.accessRoles.stream().map(Enum::name).toArray()
        ));
        ps.setString(5, endpoint.requestType.name());
        ps.setArray(6, ps.getConnection().createArrayOf(
          "text", endpoint.defaultAccessRoles.stream().map(Enum::name).toArray()
        ));
        ps.setBoolean(7, endpoint.activeDefaultAccessRoles);
        ps.setBoolean(8, endpoint.authenticated);
        ps.setBoolean(9, endpoint.permitAll);
      });
    }
  }

}
