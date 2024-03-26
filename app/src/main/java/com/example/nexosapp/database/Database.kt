package com.example.nexosapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexosapp.model.Authorization
@Database(entities = [Authorization::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun authorizationDao(): AuthorizationDAO
}