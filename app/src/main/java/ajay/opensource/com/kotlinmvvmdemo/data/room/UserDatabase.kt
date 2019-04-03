package ajay.opensource.com.kotlinmvvmdemo.data.room

import ajay.opensource.com.kotlinmvvmdemo.data.model.User
import ajay.opensource.com.kotlinmvvmdemo.data.room.UserDao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}