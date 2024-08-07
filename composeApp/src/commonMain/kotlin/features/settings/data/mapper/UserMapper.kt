package features.settings.data.mapper

import features.settings.data.models.UserDto
import features.settings.domain.entities.User

fun UserDto.mapToUser(): User = User(
    firstname = firstname,
    lastname = lastname,
    nickname = nickname,
)

fun User.mapToUserDto(): UserDto = UserDto(
    firstname = firstname,
    lastname = lastname,
    nickname = nickname,
)