package kz.otussocialnetwork.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kz.otussocialnetwork.annotation.enums.ExecutionTimeType;

/**
 * Аннотация для логирования выполнения методов.
 * Позволяет измерять время выполнения метода и выводить его в логах.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecution {

  /**
   * Задает альтернативное имя метода для логирования.
   * Если не указано, в логах будет отображаться фактическое имя метода.
   *
   * @return имя метода для логирования.
   */
  String methodName() default "";

  /**
   * Уникальный идентификатор места вызова метода.
   * Может использоваться для быстрого поиска метода в логах.
   *
   * @return строковый идентификатор (по умолчанию пустая строка).
   */
  String placeId() default "";

  /**
   * Указывает, нужно ли измерять и логировать время выполнения метода.
   *
   * @return {@code true}, если необходимо измерять время выполнения; {@code false} — иначе.
   */
  boolean needExecutionTime() default false;

  /**
   * Определяет единицу измерения времени выполнения метода.
   * Можно выбрать наносекунды, миллисекунды или секунды.
   *
   * @return единица измерения времени (по умолчанию {@link ExecutionTimeType#MILLISECONDS}).
   */
  ExecutionTimeType executionTimeType() default ExecutionTimeType.MILLISECONDS;
}

