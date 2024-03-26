package com.example.nexosapp.database

import com.example.nexosapp.model.Authorization
class DatabaseRepositoryImpl(
    private val authorizationDAO: AuthorizationDAO
): DatabaseRepository {
    override suspend fun insertAuthorization(authorization: Authorization) {
        authorizationDAO.insert(authorization)
    }

    override suspend fun deleteAuthorization(authorization: Authorization) {
        authorizationDAO.delete(authorization)
    }

    override suspend fun updateAuthorizationStatusByReceiptId(receiptId: String, status: String) {
        authorizationDAO.updateStatusByReceiptId(receiptId, status)
    }

    override suspend fun getAuthorizationsByStatus(status: String): List<Authorization> {
        return authorizationDAO.getAuthorizationsByStatus(status)
    }

    override suspend fun getAuthorizationsByNumber(number: String): List<Authorization> {
        return authorizationDAO.getAuthorizationsByNumber(number)
    }
}