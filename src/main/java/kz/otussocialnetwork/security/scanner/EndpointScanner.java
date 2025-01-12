package kz.otussocialnetwork.security.scanner;

import java.util.List;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.NonNull;

public interface EndpointScanner {
  void scanSave();

  @NonNull List<@NonNull Endpoint> scan();
}
