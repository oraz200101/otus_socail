package kz.otussocialnetwork.security.jwt.exception

class AuthPasswordInvalidException(placeId: String, message: String) : RuntimeException("$placeId :: $message")
