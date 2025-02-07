package kz.otussocialnetwork.security.scanner.repository;

import java.util.List;
import java.util.UUID;
import kz.otussocialnetwork.repository.base.CrudRepository;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.NonNull;

public interface EndpointRepository extends CrudRepository<Endpoint, UUID> {

  void createListBatch(@NonNull List<Endpoint> endpoints);
}
