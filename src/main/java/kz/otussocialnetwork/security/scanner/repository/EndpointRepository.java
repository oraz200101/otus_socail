package kz.otussocialnetwork.security.scanner.repository;

import java.util.List;
import kz.otussocialnetwork.repository.base.CrudRepository;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.NonNull;

public interface EndpointRepository extends CrudRepository<Endpoint, Long> {

  void createListBatch(@NonNull List<Endpoint> endpoints);
}
