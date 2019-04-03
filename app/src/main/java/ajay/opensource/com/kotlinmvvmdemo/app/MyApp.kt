package ajay.opensource.com.kotlinmvvmdemo.app


import ajay.opensource.com.kotlinmvvmdemo.data.room.UserDatabase
import android.app.Application
import androidx.room.Room

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MyApp.database= Room.databaseBuilder(this, UserDatabase::class.java, "users.db").allowMainThreadQueries().build()
    }
    companion object {
        lateinit var database:  UserDatabase
    }
}