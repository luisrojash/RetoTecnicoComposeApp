package com.lerp.domain.repository

import com.lerp.domain.entities.UserUi
import com.lerp.domain.Result

interface UserRepository {
    suspend fun getListUser(): Result<List<UserUi>>
}
