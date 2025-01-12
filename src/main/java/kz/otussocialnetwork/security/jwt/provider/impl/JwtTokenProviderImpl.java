package kz.otussocialnetwork.security.jwt.provider.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import kz.otussocialnetwork.model.enums.Role;
import kz.otussocialnetwork.security.jwt.enums.TokenType;
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidTypeException;
import kz.otussocialnetwork.security.jwt.properties.JwtProperties;
import kz.otussocialnetwork.security.jwt.provider.JwtTokenProvider;
import kz.otussocialnetwork.utils.DateUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static kz.otussocialnetwork.security.jwt.constants.JwtConstants.ROLES;
import static kz.otussocialnetwork.security.jwt.constants.JwtConstants.TOKEN_TYPE_KEY;

@Component
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProvider {

  private final JwtProperties jwtProperties;
  private       SecretKey     secretKey;

  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
  }

  @Override public @NonNull String createAccessToken(@NonNull Long userId, @NonNull Set<@NonNull Role> roles) {
    Claims claims = Jwts.claims()
                        .subject(userId.toString())
                        .add(ROLES, resolveRoles(roles))
                        .add(TOKEN_TYPE_KEY, TokenType.REFRESH.name())
                        .build();

    LocalDateTime expiration = LocalDateTime.now().plusHours(jwtProperties.getAccess());

    return Jwts.builder()
               .claims(claims)
               .expiration(DateUtil.toDate(expiration))
               .signWith(secretKey)
               .compact();
  }

  @Override public @NonNull String createRefreshToken(@NonNull Long userId , @NonNull Set<@NonNull Role> roles) {
    Claims claims = Jwts.claims()
                        .subject(userId.toString())
                        .add(TOKEN_TYPE_KEY, TokenType.REFRESH.name())
                        .build();

    LocalDateTime expiration = LocalDateTime.now().plusHours(jwtProperties.getRefresh());

    return Jwts.builder()
               .claims(claims)
               .expiration(DateUtil.toDate(expiration))
               .signWith(secretKey)
               .compact();
  }

  @Override public boolean validateAccessToken(@NonNull String accessToken) {
    Jws<Claims> claims = Jwts.parser()
                             .verifyWith(secretKey)
                             .build()
                             .parseSignedClaims(accessToken);

    TokenType type = TokenType.valueOf(claims.getPayload().get(TOKEN_TYPE_KEY).toString());

    if (type == TokenType.REFRESH) {
      throw new TokenInvalidTypeException("zTrx5jxJ", "token type is invalid");
    }

    return claims.getPayload()
                 .getExpiration()
                 .before(DateUtil.toDate(LocalDateTime.now()));
  }



  @Override public boolean validateRefreshToken(@NonNull String refreshToken) {
    Jws<Claims> claims = Jwts.parser()
                             .verifyWith(secretKey)
                             .build()
                             .parseSignedClaims(refreshToken);

    TokenType type = TokenType.valueOf(claims.getPayload().get(TOKEN_TYPE_KEY).toString());

    if (type == TokenType.ACCESS) {
      throw new TokenInvalidTypeException("IgRZGqJ", "token type is invalid");
    }

    return claims.getPayload()
                 .getExpiration()
                 .before(DateUtil.toDate(LocalDateTime.now()));
  }

  private @NonNull Set<@NonNull String> resolveRoles(@NonNull Set<@NonNull Role> roles) {
    return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
  }
}
