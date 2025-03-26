package kz.otussocialnetwork.controller;

import kz.otussocialnetwork.model.dto.AuthResponse;
import kz.otussocialnetwork.model.dto.LoginRequest;
import kz.otussocialnetwork.model.dto.RefreshTokenRequest;
import kz.otussocialnetwork.security.scanner.annotation.AccessMetaData;
import kz.otussocialnetwork.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/log-in")
  @AccessMetaData(id = "a69396d2-a5d4-4d82-a6e3-bd0726fbbc55")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
   return ResponseEntity.ok(authService.login(request.getUsername(), request.getPassword()));
  }

  @PostMapping("/refresh")
  @AccessMetaData(id = "ae02f592-ea9f-4f91-9546-4b55df7fa84c")
  public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
    return ResponseEntity.ok(authService.refreshToken(request.getRefreshToken()));
  }
}
