package kz.otussocialnetwork.security.scanner.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kz.otussocialnetwork.model.enums.Role;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultAccess {
  Role[] defaultAccessRoles() default {};

  boolean defaultPermitAll() default true;

  boolean defaultAuthenticated() default false;
}
