package kz.otussocialnetwork.security.jwt.provider;

import java.util.Set;
import kz.otussocialnetwork.model.enums.Role;
import lombok.NonNull;

public interface JwtTokenProvider {

  @NonNull String createAccessToken(@NonNull Long userId, @NonNull Set<@NonNull Role> roles);

  @NonNull String createRefreshToken(@NonNull Long userId, @NonNull Set<@NonNull Role> roles);

  boolean validateAccessToken(@NonNull String accessToken);

  boolean validateRefreshToken(@NonNull String refreshToken);
}
