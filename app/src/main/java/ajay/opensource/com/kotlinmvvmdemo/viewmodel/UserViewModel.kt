package ajay.opensource.com.kotlinmvvmdemo.viewmodel


import ajay.opensource.com.kotlinmvvmdemo.data.model.User
import ajay.opensource.com.kotlinmvvmdemo.data.repository.UserRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Context


class UserViewModel(val context: Context): ViewModel() {

    fun loginUser(email: String, password: String): LiveData<User>{
        return UserRepository.getInstance(context).login(email, password)
    }

    fun registerUser(user: User): LiveData<User>{
        return UserRepository.getInstance(context).register(user)
    }

    override fun onCleared() {
        super.onCleared()
    }

    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return UserViewModel(context) as T
        }
    }
}