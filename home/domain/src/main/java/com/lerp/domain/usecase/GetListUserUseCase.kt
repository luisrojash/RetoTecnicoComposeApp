package com.lerp.domain.usecase

import com.lerp.domain.Result
import com.lerp.domain.entities.UserUi
import com.lerp.domain.repository.UserRepository


class GetListUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(): Result<List<UserUi>> {
        return repository.getListUser()
    }
}