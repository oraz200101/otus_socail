package kz.otussocialnetwork.service.impl;

import kz.otussocialnetwork.model.dto.UserProfileResponse;
import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.model.dto.UserSignInResponse;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import kz.otussocialnetwork.model.dto.UserUpdateResponse;
import kz.otussocialnetwork.model.entity.UserEntity;
import kz.otussocialnetwork.repository.user_repository.UserRepository;
import kz.otussocialnetwork.security.adapter.AuthAdapter;
import kz.otussocialnetwork.security.model.Authentication;
import kz.otussocialnetwork.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository  userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthAdapter authAdapter;

  @Transactional
  @Override public UserSignInResponse signIn(@NonNull UserSignInRequest request) {
    UserEntity entity = UserEntity.register(request, passwordEncoder::encode);

    userRepository.create(entity);

    return UserSignInResponse.of(entity.username);
  }

  @Transactional
  @Override public UserUpdateResponse update(@NonNull UserUpdateRequest request) {
    UserEntity entity = userRepository.findById(request.id)
                                      .orElseThrow();

    entity.update(request, passwordEncoder::encode);

    userRepository.update(entity);

    return UserUpdateResponse.of(entity.username);
  }

  @Override public UserProfileResponse getProfile() {
    Authentication authentication = authAdapter.getAuthentication();

    UserEntity userEntity = userRepository.findById(authentication.userId)
                                          .orElseThrow();

    return UserProfileResponse.fromEntity(userEntity);
  }


}
