package kz.otussocialnetwork.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UserUpdateRequest {
  public UUID      id;
  public String    username;
  public String    firstName;
  public String    secondName;
  public LocalDate birthDate;
  public String    biography;
  public String    city;
  public String    password;
}
