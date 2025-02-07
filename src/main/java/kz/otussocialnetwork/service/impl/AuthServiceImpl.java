package kz.otussocialnetwork.service.impl;

import java.util.Set;
import java.util.UUID;
import kz.otussocialnetwork.model.dto.AuthResponse;
import kz.otussocialnetwork.model.entity.UserEntity;
import kz.otussocialnetwork.model.enums.Role;
import kz.otussocialnetwork.repository.user_repository.UserRepository;
import kz.otussocialnetwork.security.jwt.exception.AuthPasswordInvalidException;
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidException;
import kz.otussocialnetwork.security.jwt.provider.JwtTokenProvider;
import kz.otussocialnetwork.security.model.Authentication;
import kz.otussocialnetwork.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository   userRepository;
  private final PasswordEncoder  passwordEncoder;

  @Override public AuthResponse login(@NonNull String username, @NonNull String password) {
    UserEntity entity = userRepository.findByUsername(username)
                                      .orElseThrow();

    if (!passwordEncoder.matches(password, entity.password)) {
      throw new AuthPasswordInvalidException("Avb3wrK", "auth password incorrect");
    }

    String accessToken  = jwtTokenProvider.createAccessToken(entity.id, Set.of(Role.USER));
    String refreshToken = jwtTokenProvider.createRefreshToken(entity.id, Set.of(Role.USER));

    return AuthResponse.of(accessToken, refreshToken);
  }

  @Override public AuthResponse refreshToken(@NonNull String refreshToken) {
    if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
      throw new TokenInvalidException("0xrjF7V", "token is invalid");
    }

    UUID userId = jwtTokenProvider.getUserId(refreshToken);

    String accessToken     = jwtTokenProvider.createAccessToken(userId, Set.of(Role.USER));
    String newRefreshToken = jwtTokenProvider.createRefreshToken(userId, Set.of(Role.USER));

    return AuthResponse.of(accessToken, newRefreshToken);
  }

  @Override public Authentication getAuthentication(@NonNull String accessToken) {
    UUID userId = jwtTokenProvider.getUserId(accessToken);
    Set<Role> roles = Set.of(Role.USER);

    return Authentication.of(userId, roles);
  }

}
