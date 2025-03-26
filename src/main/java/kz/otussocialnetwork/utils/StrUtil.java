package kz.otussocialnetwork.utils;

public class StrUtil {

  public static boolean isNullOrEmpty(String str) {
    return str == null || str.isEmpty();
  }

  public static boolean isNullOrEmptyOrBlank(String str) {
    return str == null || str.isEmpty() || str.trim().isEmpty();
  }

  public static boolean hasNotUpperLetter(String str) {
    if (isNullOrEmpty(str)) {
      return true;
    }

    for (char c : str.toCharArray()) {
      if (Character.isUpperCase(c)) {
        return false;
      }
    }

    return true;
  }

  public static boolean hasNotLowLetter(String str) {
    if (isNullOrEmpty(str)) {
      return true;
    }

    for (char c : str.toCharArray()) {
      if (Character.isLowerCase(c)) {
        return false;
      }
    }

    return true;
  }

  public static boolean hasNotDigit(String str) {
    if (isNullOrEmpty(str)) {
      return true;
    }

    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        return false;
      }
    }

    return true;
  }

  public static String nullToEmpty(String str) {
    return str == null ? "" : str;
  }
}
