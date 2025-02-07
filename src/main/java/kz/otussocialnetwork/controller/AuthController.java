package kz.otussocialnetwork.controller;

import kz.otussocialnetwork.model.dto.AuthResponse;
import kz.otussocialnetwork.model.dto.LoginRequest;
import kz.otussocialnetwork.model.dto.RefreshTokenRequest;
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
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
   return ResponseEntity.ok(authService.login(request.username, request.password));
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
    return ResponseEntity.ok(authService.refreshToken(request.refreshToken));
  }
}
