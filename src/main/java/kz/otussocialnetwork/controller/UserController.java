package kz.otussocialnetwork.controller;

import kz.otussocialnetwork.model.dto.UserProfileResponse;
import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.model.dto.UserSignInResponse;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import kz.otussocialnetwork.model.dto.UserUpdateResponse;
import kz.otussocialnetwork.security.scanner.annotation.AccessMetaData;
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
  @AccessMetaData(id = "d2de2da7-6179-4e0d-9fe4-4dd55d6acbf6")
  public ResponseEntity<UserProfileResponse> getUserProfile() {
    return ResponseEntity.ok(userService.getProfile());
  }

  @PostMapping("/create")
  @AccessMetaData(id = "d596f523-dd62-4e95-b6b1-4f124d7d35fb")
  public ResponseEntity<UserSignInResponse> signIn(@RequestBody UserSignInRequest request) {
    return ResponseEntity.ok(userService.signIn(request));
  }

  @PutMapping("/update")
  @AccessMetaData(id = "3b142ce1-65ea-4d05-9eef-4876d0097aa0")
  public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request) {
    return ResponseEntity.ok(userService.update(request));
  }
}
