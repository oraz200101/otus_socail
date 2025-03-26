package kz.otussocialnetwork.service.impl

import kz.otussocialnetwork.exception.NotFoundException
import kz.otussocialnetwork.model.dto.AuthResponse
import kz.otussocialnetwork.model.enums.Role
import kz.otussocialnetwork.repository.user_repository.UserRepository
import kz.otussocialnetwork.security.jwt.exception.AuthPasswordInvalidException
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidException
import kz.otussocialnetwork.security.jwt.provider.JwtTokenProvider
import kz.otussocialnetwork.security.model.Authentication
import kz.otussocialnetwork.service.AuthService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) : AuthService {

    override fun login(username: String, password: String): AuthResponse {
        val entity = userRepository.findByUsername(username)
            .orElseThrow {
                NotFoundException(
                    "f9gRnOFyMx2",
                    "User was not found"
                )
            }

        if (!passwordEncoder.matches(password, entity.password)) {
            throw AuthPasswordInvalidException("Avb3wrK", "auth password incorrect")
        }

        val accessToken = jwtTokenProvider.createAccessToken(entity.id, mutableSetOf(Role.USER))
        val refreshToken = jwtTokenProvider.createRefreshToken(entity.id, mutableSetOf(Role.USER))

        return AuthResponse(accessToken, refreshToken)
    }

    override fun refreshToken(refreshToken: String): AuthResponse {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw TokenInvalidException("0xrjF7V", "token is invalid")
        }

        val userId = jwtTokenProvider.getUserId(refreshToken)

        val accessToken = jwtTokenProvider.createAccessToken(userId, mutableSetOf(Role.USER))
        val newRefreshToken = jwtTokenProvider.createRefreshToken(userId, mutableSetOf(Role.USER))

        return AuthResponse(accessToken, newRefreshToken)
    }

    override fun getAuthentication(accessToken: String): Authentication {
        val userId = jwtTokenProvider.getUserId(accessToken)
        val roles = mutableSetOf(Role.USER)

        return Authentication(userId, roles)
    }
}
