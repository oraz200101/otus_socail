package kz.otussocialnetwork.security.model

import kz.otussocialnetwork.model.enums.Role
import java.util.UUID

data class Authentication(
    val userId: UUID,
    val roles: Set<Role>
)
