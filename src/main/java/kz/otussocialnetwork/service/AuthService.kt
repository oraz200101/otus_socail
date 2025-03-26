package kz.otussocialnetwork.service

import kz.otussocialnetwork.model.dto.AuthResponse
import kz.otussocialnetwork.security.model.Authentication

interface AuthService {
    fun login(
        username: String,
        password: String
    ): AuthResponse

    fun refreshToken(refreshToken: String): AuthResponse

    fun getAuthentication(accessToken: String): Authentication
}
