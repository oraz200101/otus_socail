package kz.otussocialnetwork.controller;

import kz.otussocialnetwork.model.dto.UserProfileResponse;
import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.model.dto.UserSignInResponse;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import kz.otussocialnetwork.model.dto.UserUpdateResponse;
import kz.otussocialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/profile")
  public ResponseEntity<UserProfileResponse> getUserProfile() {
    return ResponseEntity.ok(userService.getProfile());
  }

  @PostMapping("/create")
  public ResponseEntity<UserSignInResponse> signIn(@RequestBody UserSignInRequest request) {
    return ResponseEntity.ok(userService.signIn(request));
  }

  @PutMapping("/update")
  public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request) {
    return ResponseEntity.ok(userService.update(request));
  }
}
