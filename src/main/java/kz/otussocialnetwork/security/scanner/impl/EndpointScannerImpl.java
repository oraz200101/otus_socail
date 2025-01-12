package kz.otussocialnetwork.security.scanner.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import kz.otussocialnetwork.model.enums.Role;
import kz.otussocialnetwork.security.scanner.EndpointScanner;
import kz.otussocialnetwork.security.scanner.annotation.DefaultAccess;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
public class EndpointScannerImpl implements EndpointScanner {
  @Value("${server.servlet.context-path}")
  private String contextPath;

  @Override public @NonNull List<@NonNull Endpoint> scan() {
    return List.of();
  }

  private boolean hasAnnotationDefaultAccess(@NonNull Method method) {
    return method.isAnnotationPresent(DefaultAccess.class);
  }

  private boolean isRestController(@NonNull Class<?> controller) {
    return controller.isAnnotationPresent(RestController.class);
  }

  private @NonNull String getRequestMappingValue(@NonNull Class<?> controller) {
    return controller.getAnnotation(RequestMapping.class).value()[0];
  }

  private Endpoint createEndpoint(@NonNull String requestMappingValue,
                                  @NonNull Method method) {
    String      url               = contextPath + requestMappingValue;
    String      methodName        = method.getName();
    RequestType requestType       = getRequestMethod(method);
    Set<Role>   defaultAccessRole = getDefaultAccessRoles(method);
    

    return Endpoint.create(url, methodName, requestType, )
  }

  private @NonNull RequestType getRequestMethod(@NonNull Method method) {
    String requestAnnotationName = Arrays.stream(method.getAnnotations())
                                         .map(Annotation::annotationType)
                                         .map(Class::getSimpleName)
                                         .filter(name -> RequestType.requestAnnotationNames().contains(name))
                                         .findFirst()
                                         .orElseThrow(() -> new IllegalArgumentException("6jNQzlFWon :: no request annotation found"));

    return RequestType.valueOf(requestAnnotationName);
  }

  private Set<Role> getDefaultAccessRoles(@NonNull Method method) {
    if (method.isAnnotationPresent(DefaultAccess.class)) {
      DefaultAccess defaultAccess = method.getAnnotation(DefaultAccess.class);
      return Arrays.stream(defaultAccess.roles()).collect(Collectors.toSet());
    } else {
      return null;
    }
  }
  
  private 
}
