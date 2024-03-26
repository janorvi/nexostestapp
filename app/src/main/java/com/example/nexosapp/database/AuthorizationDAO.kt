package com.example.nexosapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nexosapp.model.Authorization
@Dao
interface AuthorizationDAO {

    @Transaction
    @Insert
    suspend fun insert (authorization: Authorization)

    @Delete
    suspend fun delete(authorization: Authorization)

    @Query("UPDATE authorizations_table SET authorization_status_column =:status WHERE receipt_id_column =:receiptId")
    suspend fun updateStatusByReceiptId(receiptId: String, status: String)

    @Query("SELECT * FROM authorizations_table WHERE authorization_status_column =:status")
    suspend fun getAuthorizationsByStatus(status: String?): List<Authorization>

    @Query("SELECT * FROM authorizations_table WHERE id =:number AND authorization_status_column='Aprobada'")
    suspend fun getAuthorizationsByNumber(number: String?): List<Authorization>
}