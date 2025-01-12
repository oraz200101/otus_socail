package kz.otussocialnetwork.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class AuthResponse {
  public String accessToken;
  public String refreshToken;
}
