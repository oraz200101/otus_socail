package kz.otussocialnetwork.security.scanner.impl;

import jakarta.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import kz.otussocialnetwork.security.scanner.EndpointScanner;
import kz.otussocialnetwork.security.scanner.annotation.AccessMetaData;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import kz.otussocialnetwork.security.scanner.repository.EndpointRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
public class EndpointScannerImpl implements EndpointScanner {
  @Value("${server.servlet.context-path}")
  private String contextPath;

  private final ApplicationContext applicationContext;
  private final EndpointRepository endpointRepository;

  @PostConstruct
  @Override public void scanSave() {
    Set<UUID> endpoints = endpointRepository.findAll()
                                            .stream()
                                            .map(endpoint -> endpoint.id)
                                            .collect(Collectors.toSet());

    if (!endpoints.isEmpty()) {
      endpointRepository.deleteAll(endpoints);
    }

    endpointRepository.createListBatch(scan());
  }

  @Override public @NonNull List<@NonNull Endpoint> scan() {
    return collectEndpoints();
  }

  private List<Endpoint> collectEndpoints() {
    return collectControllers().stream()
                               .flatMap(controller -> collectEndpointsOfController(controller).stream())
                               .toList();
  }

  private List<Endpoint> collectEndpointsOfController(@NonNull Class<?> controller) {
    String requestMappingPath = getRequestMappingPath(controller);

    List<Method> methods = Arrays.stream(controller.getMethods())
                                 .filter(this::isRequestAnnotationPresent)
                                 .toList();

    return methods.stream()
                  .map(method -> createEndpoint(requestMappingPath, method))
                  .collect(Collectors.toList());
  }

  private Endpoint createEndpoint(@NonNull String requestMappingValue,
                                  @NonNull Method method) {
    Endpoint.EndpointBuilder builder = Endpoint.builder();

    setMethodName(method, builder);
    setRequestMethod(method, builder);
    setDefaultAccessParams(method, builder);
    setMethodUrl(requestMappingValue, method, builder);
    builder.defaultAccessRoles(new HashSet<>());
    builder.accessRoles(new HashSet<>());

    return builder.build();
  }


  private @NonNull String getRequestMappingPath(@NonNull Class<?> controller) {
    return controller.getAnnotation(RequestMapping.class).value()[0];
  }

  private List<Class<?>> collectControllers() {
    return applicationContext.getBeansWithAnnotation(RestController.class)
                             .values()
                             .stream()
                             .map(Object::getClass)
                             .collect(Collectors.toList());
  }

  private void setMethodName(@NonNull Method method,
                             @NonNull Endpoint.EndpointBuilder builder) {
    builder.methodName(method.getName());
  }

  private void setMethodUrl(@NonNull String requestMappingPath,
                            @NonNull Method method,
                            @NonNull Endpoint.EndpointBuilder endpointBuilder) {
    Annotation requestAnnotation = getRequestAnnotation(method);

    String methodPath = switch (requestAnnotation) {
      case GetMapping getMapping -> getMapping.value().length == 1 ? getMapping.value()[0] : "";
      case PostMapping postMapping -> postMapping.value().length == 1 ? postMapping.value()[0] : "";
      case PutMapping putMapping -> putMapping.value().length == 1 ? putMapping.value()[0] : "";
      case PatchMapping patchMapping -> patchMapping.value().length == 1 ? patchMapping.value()[0] : "";
      case DeleteMapping deleteMapping -> deleteMapping.value().length == 1 ? deleteMapping.value()[0] : "";
      case null,
           default -> throw new RuntimeException("Abd3I75kQ :: Unsupported request annotation " + requestAnnotation);
    };

    endpointBuilder.url(contextPath + requestMappingPath + methodPath);
  }

  private void setRequestMethod(@NonNull Method method,
                                @NonNull Endpoint.EndpointBuilder endpointBuilder) {
    String requestAnnotationName = getRequestAnnotation(method).annotationType().getSimpleName();
    endpointBuilder.requestType(RequestType.fromAnnotationName(requestAnnotationName));
  }

  private void setDefaultAccessParams(@NonNull Method method,
                                      @NonNull Endpoint.EndpointBuilder endpointBuilder) {
    if (method.isAnnotationPresent(AccessMetaData.class)) {
      AccessMetaData accessMetaData = method.getAnnotation(AccessMetaData.class);

      if (accessMetaData.id().isEmpty()) {
        throw new RuntimeException("crG4izXm4 :: can't scan endpoint because id doesn't set");
      }

      endpointBuilder.id(UUID.fromString(accessMetaData.id()));
      endpointBuilder.defaultAccessRoles(Arrays.stream(accessMetaData.defaultAccessRoles())
                                               .collect(Collectors.toSet()));

      endpointBuilder.authenticated(accessMetaData.defaultAuthenticated());
      endpointBuilder.permitAll(accessMetaData.defaultPermitAll());
    } else {
      throw new RuntimeException("qbdV1wlzIk :: can't scan endpoint metadata doesn't set" + method);
    }
  }

  private boolean isRequestAnnotationPresent(@NonNull Method method) {
    return method.isAnnotationPresent(GetMapping.class)
      || method.isAnnotationPresent(PostMapping.class)
      || method.isAnnotationPresent(PutMapping.class)
      || method.isAnnotationPresent(PatchMapping.class)
      || method.isAnnotationPresent(DeleteMapping.class);
  }

  private Annotation getRequestAnnotation(@NonNull Method method) {
    if (method.isAnnotationPresent(GetMapping.class)) {
      return method.getAnnotation(GetMapping.class);
    }

    if (method.isAnnotationPresent(PostMapping.class)) {
      return method.getAnnotation(PostMapping.class);
    }

    if (method.isAnnotationPresent(DeleteMapping.class)) {
      return method.getAnnotation(DeleteMapping.class);
    }

    if (method.isAnnotationPresent(PutMapping.class)) {
      return method.getAnnotation(PutMapping.class);
    }

    if (method.isAnnotationPresent(PatchMapping.class)) {
      return method.getAnnotation(PatchMapping.class);
    }

    throw new IllegalArgumentException("QKoD8ijf :: no request annotation found");
  }
}
