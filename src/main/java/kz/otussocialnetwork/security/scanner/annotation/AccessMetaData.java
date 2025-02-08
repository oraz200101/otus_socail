package kz.otussocialnetwork.security.scanner.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kz.otussocialnetwork.model.enums.Role;

/**
 * Аннотация для определения метаданных доступа к эндпоинтам.
 * Позволяет задать роли, аутентификацию и уникальный идентификатор эндпоинта.
 * <p>
 * Может применяться только к методам.
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessMetaData {

  /**
   * Определяет роли, имеющие доступ к эндпоинту по умолчанию.
   * <p>
   * Если указаны роли, параметры {@link #defaultPermitAll()} и {@link #defaultAuthenticated()}
   * игнорируются, так как доступ определяется исключительно указанными ролями.
   * </p>
   *
   * @return массив ролей с доступом
   */
  Role[] defaultAccessRoles() default {};

  /**
   * Определяет, доступен ли эндпоинт для всех пользователей без ограничений.
   * <p>
   * Если установлено в {@code true}, доступ открыт для всех, независимо от статуса аутентификации.
   * Если этот параметр задан, параметр {@link #defaultAuthenticated()} игнорируется.
   * </p>
   *
   * @return {@code true}, если доступ открыт для всех, иначе {@code false}
   */
  boolean defaultPermitAll() default true;

  /**
   * Определяет, требуется ли аутентификация для доступа к эндпоинту.
   * <p>
   * Если установлено в {@code true}, доступ разрешен только аутентифицированным пользователям.
   * Если {@link #defaultPermitAll()} установлено в {@code true}, этот параметр игнорируется.
   * </p>
   *
   * @return {@code true}, если требуется аутентификация, иначе {@code false}
   */
  boolean defaultAuthenticated() default false;

  /**
   * Уникальный идентификатор эндпоинта.
   * <p>
   * Данный идентификатор должен оставаться неизменным после первого назначения.
   * Рекомендуется использовать {@link java.util.UUID} для генерации.
   * Изменение идентификатора может привести к непредсказуемым последствиям в системе безопасности.
   * </p>
   *
   * @return строка, представляющая уникальный идентификатор эндпоинта
   */
  String id() default "";
}
