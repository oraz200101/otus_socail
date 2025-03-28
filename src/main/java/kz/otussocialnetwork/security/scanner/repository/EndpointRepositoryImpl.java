package kz.otussocialnetwork.security.scanner.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.CREATE_ENDPOINT;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.DELETE_ENDPOINT;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.DELETE_ENDPOINTS;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.FIND_BY_URL_AND_TYPE;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.FIND_ENDPOINTS;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.FIND_ENDPOINT_BY_ID;
import static kz.otussocialnetwork.security.scanner.constants.EndpointPostgresQueries.UPDATE_ENDPOINT;

@Repository
@RequiredArgsConstructor
public class EndpointRepositoryImpl implements EndpointRepository {
  private final JdbcTemplate               jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final Endpoint.EnpointRowMapper enpointRowMapper;

  @Override public Optional<Endpoint> findById(@NonNull UUID id) {
    Endpoint endpoint = jdbcTemplate.queryForObject(
      FIND_ENDPOINT_BY_ID,
      enpointRowMapper,
      id
    );

    return Optional.ofNullable(endpoint);
  }

  @Override public List<Endpoint> findAll() {
    return jdbcTemplate.query(FIND_ENDPOINTS, enpointRowMapper);
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
  @Override public void update(@NonNull Endpoint endpoint) {
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

  @Override
  public void updateListBatch(@NonNull List<Endpoint> endpoints) {
    final int batchSize = 500;

    for (int i = 0; i < endpoints.size(); i += batchSize) {
      List<Endpoint> batch = endpoints.subList(i, Math.min(i + batchSize, endpoints.size()));

      jdbcTemplate.batchUpdate(UPDATE_ENDPOINT, batch, batch.size(), (ps, endpoint) -> {
        ps.setString(1, endpoint.url);
        ps.setString(2, endpoint.methodName);
        ps.setArray(3, ps.getConnection().createArrayOf(
          "text", endpoint.accessRoles.stream().map(Enum::name).toArray()
        ));
        ps.setString(4, endpoint.requestType.name());
        ps.setArray(5, ps.getConnection().createArrayOf(
          "text", endpoint.defaultAccessRoles.stream().map(Enum::name).toArray()
        ));
        ps.setBoolean(6, endpoint.activeDefaultAccessRoles);
        ps.setBoolean(7, endpoint.authenticated);
        ps.setBoolean(8, endpoint.permitAll);
        ps.setObject(9, endpoint.id);
      });
    }
  }

  @Override public void deleteAll(@NonNull Set<UUID> endpointIds) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("ids", endpointIds);

    namedParameterJdbcTemplate.update(DELETE_ENDPOINTS, parameters);
  }

  @Cacheable(value = "endpoints", key = "#url + ':' + #type")
  @Override public Optional<Endpoint> findByUrlAndType(@NonNull String url, @NonNull RequestType type) {
    Endpoint endpoint = jdbcTemplate.queryForObject(
      FIND_BY_URL_AND_TYPE,
      enpointRowMapper,
      url,
      type.name()
    );

    return Optional.ofNullable(endpoint);
  }
}
