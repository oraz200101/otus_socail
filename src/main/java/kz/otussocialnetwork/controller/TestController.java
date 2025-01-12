package kz.otussocialnetwork.controller;

import kz.otussocialnetwork.security.scanner.annotation.DefaultAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/do-test")
  @DefaultAccess(defaultAuthenticated = true)
  public String test() {
    return "test";
  }
}
