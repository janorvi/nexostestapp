package com.example.nexosapp.database

import com.example.nexosapp.model.Authorization
interface DatabaseRepository {
    suspend fun insertAuthorization(authorization: Authorization)
    suspend fun deleteAuthorization(authorization: Authorization)
    suspend fun updateAuthorizationStatusByReceiptId(receiptId: String, status: String)
    suspend fun getAuthorizationsByStatus(status: String): List<Authorization>
    suspend fun getAuthorizationsByNumber(number: String): List<Authorization>
}