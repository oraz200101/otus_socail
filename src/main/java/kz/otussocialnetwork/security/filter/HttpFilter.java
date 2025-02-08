package kz.otussocialnetwork.security.filter;

import lombok.NonNull;

public interface HttpFilter extends Comparable<HttpFilter> {

  void accept(@NonNull HttpRequestMetadata metadata);

  int order();

  int compareTo(@NonNull HttpFilter other);
}
