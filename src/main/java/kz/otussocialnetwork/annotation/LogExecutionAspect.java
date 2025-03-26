package kz.otussocialnetwork.annotation;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionAspect {

  @Around("@annotation(LogExecution)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Method       method     = getMethodFromJoinPoint(joinPoint);
    LogExecution annotation = method.getAnnotation(LogExecution.class);

    return logExecution(method, annotation, joinPoint);
  }

  private Method getMethodFromJoinPoint(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
    String     methodName     = joinPoint.getSignature().getName();
    Class<?>   targetClass    = joinPoint.getTarget().getClass();
    Class<?>[] parameterTypes = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getParameterTypes();
    return targetClass.getMethod(methodName, parameterTypes);
  }

  private Object logExecution(Method method, LogExecution annotation, ProceedingJoinPoint joinPoint) throws Throwable {
    String placeId    = annotation.placeId();
    String methodName = annotation.methodName().isEmpty() ? method.getName() : annotation.methodName();

    if (!annotation.needExecutionTime()) {
      Object result = joinPoint.proceed();
      log.info("{} :: Method '{}' executed", placeId, methodName);
      return result;
    }

    long startTime = System.nanoTime();

    Object result = joinPoint.proceed();

    long elapsedTime = System.nanoTime() - startTime;

    double convertedTime = switch (annotation.executionTimeType()) {
      case MILLISECONDS -> elapsedTime / 1_000_000.0;
      case SECONDS -> elapsedTime / 1_000_000_000.0;
      case MINUTES -> elapsedTime / 60_000_000_000.0;
      case NANOSECONDS -> (double) elapsedTime;
    };

    log.info("{} :: Method {} executed in {} {}", placeId, methodName, convertedTime, annotation.executionTimeType().getValue());
    return result;
  }
}
