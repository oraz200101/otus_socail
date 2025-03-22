package kz.otussocialnetwork.utils;

public class StrUtil {

  public static boolean isNullOrEmpty(String str) {
    return str == null || str.isEmpty();
  }

  public static boolean isNullOrEmptyOrBlank(String str) {
    return str == null || str.isEmpty() || str.trim().isEmpty();
  }

  public static boolean hasUpperLetter(String str) {
    if (isNullOrEmpty(str)) {
      return false;
    }

    for (char c : str.toCharArray()) {
      if (Character.isUpperCase(c)) {
        return true;
      }
    }

    return false;
  }

  public static boolean hasLowLetter(String str) {
    if (isNullOrEmpty(str)) {
      return false;
    }

    for (char c : str.toCharArray()) {
      if (Character.isLowerCase(c)) {
        return true;
      }
    }

    return false;
  }

  public static boolean hasDigit(String str) {
    if (isNullOrEmpty(str)) {
      return false;
    }

    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        return true;
      }
    }

    return false;
  }
}
