package kz.otussocialnetwork.security.scanner.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import kz.otussocialnetwork.repository.base.CrudRepository;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import lombok.NonNull;

public interface EndpointRepository extends CrudRepository<Endpoint, UUID> {

  void createListBatch(@NonNull List<Endpoint> endpoints);

  void updateListBatch(@NonNull List<Endpoint> endpoints);

  void deleteAll(@NonNull Set<UUID> endpointIds);

  Optional<Endpoint> findByUrlAndType(@NonNull String url, @NonNull RequestType type);
}
