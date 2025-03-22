package kz.otussocialnetwork.validation;

import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.exception.ValidationException;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import kz.otussocialnetwork.utils.StrUtil;
import lombok.NonNull;

public class UserValidation {

  public static void validate(@NonNull UserSignInRequest request) {
    if (StrUtil.isNullOrEmptyOrBlank(request.username)){
      throw new ValidationException("uiXiO6ep4", "username is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.firstName)) {
      throw new ValidationException("M5OYWWb", "user first name is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.secondName)){
      throw new ValidationException("lbuDAQRiJz", "user second name is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.password)){
      throw new ValidationException("M5OYWWb", "user password is required");
    }

    if (StrUtil.hasNotUpperLetter(request.password)){
      throw new ValidationException("51NUlZ02SK", "user password must contain upper letter");
    }

    if (StrUtil.hasNotLowLetter(request.password)){
      throw new ValidationException("84fFX1iCZz", "user password must contain lower letter");
    }

    if (StrUtil.hasNotDigit(request.password)){
      throw new ValidationException("zme6lFPAZkr", "user password must contain digit");
    }
  }

  public static void validate(@NonNull UserUpdateRequest request) {
    if (StrUtil.isNullOrEmptyOrBlank(request.username)){
      throw new ValidationException("MTo0co2yB", "username is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.firstName)) {
      throw new ValidationException("InSwEA4kqA", "user first name is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.secondName)){
      throw new ValidationException("Q0kFWYQl", "user second name is required");
    }

    if (StrUtil.isNullOrEmptyOrBlank(request.password)){
      throw new ValidationException("wxu4SxcUotl", "user password is required");
    }

    if (StrUtil.hasNotUpperLetter(request.password)){
      throw new ValidationException("FBbXMK2K3", "user password must contain upper letter");
    }

    if (StrUtil.hasNotLowLetter(request.password)){
      throw new ValidationException("L2KLNad", "user password must contain lower letter");
    }

    if (StrUtil.hasNotDigit(request.password)){
      throw new ValidationException("gjAA03v3hzx", "user password must contain digit");
    }
  }
}
