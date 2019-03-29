package com.ajay.kotlinmvvm.service.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ajay.kotlinmvvm.service.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}