package kz.otussocialnetwork.service;

import kz.otussocialnetwork.model.dto.AuthResponse;
import lombok.NonNull;

public interface AuthService {

  AuthResponse login(@NonNull String username,
                     @NonNull String password);

  AuthResponse refreshToken(@NonNull String refreshToken);
}
