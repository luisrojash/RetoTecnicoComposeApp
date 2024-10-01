package com.lerp.infrastructure.repository

import com.lerp.infrastructure.repository.response.GetProductListResponse
import retrofit2.Response
import retrofit2.http.GET


interface UserService {

    @GET("/")
    suspend fun getUserList(): Response<List<GetProductListResponse>>
}