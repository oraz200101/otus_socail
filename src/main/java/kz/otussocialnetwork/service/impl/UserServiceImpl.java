package kz.otussocialnetwork.service.impl;

import jakarta.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import kz.otussocialnetwork.exception.NotFoundException;
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
import kz.otussocialnetwork.utils.StrUtil;
import kz.otussocialnetwork.validation.UserValidation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
  private final AuthAdapter     authAdapter;
  private final UserRepository  userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override public UserSignInResponse signIn(@NonNull UserSignInRequest request) {
    UserValidation.validate(request);

    UserEntity entity = UserEntity.register(request, passwordEncoder::encode);

    userRepository.create(entity);

    return UserSignInResponse.of(entity.username);
  }

  @Transactional
  @Override public UserUpdateResponse update(@NonNull UserUpdateRequest request) {
    UserValidation.validate(request);

    UserEntity entity = userRepository.findById(request.id)
                                      .orElseThrow(() -> new NotFoundException("f9gRnOFyMx2", "User was not found"));

    entity.update(request, passwordEncoder::encode);

    userRepository.update(entity);

    return UserUpdateResponse.of(entity.username);
  }

  @Override public UserProfileResponse getProfile() {
    Authentication authentication = authAdapter.getAuthentication();

    UserEntity userEntity = userRepository.findById(authentication.userId)
                                          .orElseThrow(() -> new NotFoundException("Er84YPTAvw", "User was not found"));

    return UserProfileResponse.fromEntity(userEntity);
  }

  @Override public List<UserProfileResponse> searchByFirstNameAndSecondName(@Nullable String firstName,
                                                                            @Nullable String secondName) {
    long start = System.currentTimeMillis();

    List<UserEntity> userEntityList = userRepository.findByFirstNameAndSecondName(
      StrUtil.nullToEmpty(firstName),
      StrUtil.nullToEmpty(secondName)
    );

    long end = System.currentTimeMillis();
    log.info("Pw23UvkJa :: find user executed in {} ms", end - start);

    return userEntityList.stream()
                         .map(UserProfileResponse::fromEntity)
                         .collect(Collectors.toList());
  }

}
