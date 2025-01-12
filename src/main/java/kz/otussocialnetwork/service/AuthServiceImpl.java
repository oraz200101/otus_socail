package kz.otussocialnetwork.service;

import kz.otussocialnetwork.model.dto.AuthResponse;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Override public AuthResponse login(@NonNull String username, @NonNull String password) {
    return null;
  }

  @Override public AuthResponse refreshToken(@NonNull String refreshToken) {
    return null;
  }
}
