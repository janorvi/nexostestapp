package com.example.nexosapp.service

import com.example.nexosapp.model.AnnulmentRequest
import com.example.nexosapp.model.AnnulmentResponse
import com.example.nexosapp.model.AuthorizationRequest
import com.example.nexosapp.model.AuthorizationResponse
interface ServiceRepository {
    suspend fun sendAuthorization(authorizationRequest: AuthorizationRequest): AuthorizationResponse
    suspend fun cancelAuthorization(annulmentRequest: AnnulmentRequest): AnnulmentResponse
}