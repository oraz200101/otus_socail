package kz.otussocialnetwork.security.jwt.provider;

import java.util.Set;
import java.util.UUID;
import kz.otussocialnetwork.model.enums.Role;
import lombok.NonNull;

public interface JwtTokenProvider {

  @NonNull String createAccessToken(@NonNull UUID userId, @NonNull Set<@NonNull Role> roles);

  @NonNull String createRefreshToken(@NonNull UUID userId, @NonNull Set<@NonNull Role> roles);

  boolean validateAccessToken(@NonNull String accessToken);

  boolean validateRefreshToken(@NonNull String refreshToken);

  @NonNull UUID getUserId(@NonNull String accessToken);
}
