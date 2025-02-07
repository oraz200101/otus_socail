package kz.otussocialnetwork.service;

import kz.otussocialnetwork.model.dto.UserProfileResponse;
import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.model.dto.UserSignInResponse;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import kz.otussocialnetwork.model.dto.UserUpdateResponse;
import lombok.NonNull;

public interface UserService {

  UserSignInResponse signIn(@NonNull UserSignInRequest request);

  UserUpdateResponse update(@NonNull UserUpdateRequest request);

  UserProfileResponse getProfile();
}
