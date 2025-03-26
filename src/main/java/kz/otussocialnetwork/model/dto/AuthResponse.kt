package kz.otussocialnetwork.model.dto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
