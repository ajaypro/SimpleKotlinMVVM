package ajay.opensource.com.kotlinmvvmdemo.data.repository

import ajay.opensource.com.kotlinmvvmdemo.app.MyApp
import ajay.opensource.com.kotlinmvvmdemo.data.model.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context

class UserRepository {
    companion object {
        private var loginRepository: UserRepository? = null
        private var context: Context?=null
        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): UserRepository {
            this.context=context
            if (loginRepository == null) loginRepository = UserRepository()
            return loginRepository!!
        }
    }

    fun login(email: String, password: String): LiveData<User>{
        val loginData=MutableLiveData<User>()
        val user= MyApp.database?.userDao()?.getUser(email, password)
        loginData.value=user
        return loginData
    }

    fun register(user: User): LiveData<User>{
        val registerData=MutableLiveData<User>()
        MyApp.database?.userDao()?.insertUser(user)
        registerData.value=user
        return registerData
    }
}