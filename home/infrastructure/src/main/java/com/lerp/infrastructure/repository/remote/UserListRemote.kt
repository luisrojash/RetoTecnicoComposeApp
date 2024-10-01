package com.lerp.infrastructure.repository.remote


import com.lerp.domain.Result
import com.lerp.domain.entities.UserUi
import com.lerp.domain.repository.UserRepository
import com.lerp.infrastructure.repository.UserService
import com.lerp.networking.api.executeApiService


class UserListRemote(private val apiService: UserService) :
    UserRepository {

    override suspend fun getListUser(): Result<List<UserUi>> {
        return executeApiService(
            api = { apiService.getUserList() },
            transformInfrastructureToDomain = {
                it.map { response ->
                    UserUi(
                        name = response.nombre,
                        image = response.urlImage,
                        statusEmpleado = response.empleado,
                        statusCliente = response.cliente
                    )
                }
            }
        )
    }
}