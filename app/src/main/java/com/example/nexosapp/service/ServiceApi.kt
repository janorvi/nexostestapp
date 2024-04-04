package com.example.nexosapp.service

import com.example.nexosapp.model.AnnulmentRequest
import com.example.nexosapp.model.AnnulmentResponse
import com.example.nexosapp.model.AuthorizationRequest
import com.example.nexosapp.model.AuthorizationResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
interface ServiceApi {
    companion object{
        const val BASE_URL = "http://10.0.2.2:8080/api/payments/"
    }
    @Headers("Authorization: Basic MDAwMTIzMDAwQUJD")
    @POST("authorization")
    suspend fun sendAuthorization(@Body request: AuthorizationRequest): AuthorizationResponse

    @Headers("Authorization: Basic MDAwMTIzMDAwQUJD")
    @POST("annulment")
    suspend fun cancelAuthorization(@Body request: AnnulmentRequest): AnnulmentResponse
}