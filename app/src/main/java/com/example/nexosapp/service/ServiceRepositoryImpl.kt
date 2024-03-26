package com.example.nexosapp.service

import com.example.nexosapp.model.AnnulmentRequest
import com.example.nexosapp.model.AnnulmentResponse
import com.example.nexosapp.model.AuthorizationRequest
import com.example.nexosapp.model.AuthorizationResponse
class ServiceRepositoryImpl(
    private val serviceApi: ServiceApi
): ServiceRepository {
    override suspend fun sendAuthorization(authorizationRequest: AuthorizationRequest): AuthorizationResponse {
        return serviceApi.sendAuthorization(authorizationRequest)
    }

    override suspend fun cancelAuthorization(annulmentRequest: AnnulmentRequest): AnnulmentResponse {
        return serviceApi.cancelAuthorization(annulmentRequest)
    }
}