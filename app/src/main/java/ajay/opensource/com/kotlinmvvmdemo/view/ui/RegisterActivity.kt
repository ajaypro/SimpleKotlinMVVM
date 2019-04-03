package ajay.opensource.com.kotlinmvvmdemo.view.ui


import ajay.opensource.com.kotlinmvvmdemo.R
import ajay.opensource.com.kotlinmvvmdemo.data.model.User
import ajay.opensource.com.kotlinmvvmdemo.databinding.ActivityRegisterBinding
import ajay.opensource.com.kotlinmvvmdemo.view.handler.RegisterActivityHandler
import ajay.opensource.com.kotlinmvvmdemo.viewmodel.UserViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import es.dmoral.toasty.Toasty

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterActivityHandler {
    lateinit var activityRegisterBinding: ActivityRegisterBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegisterBinding=DataBindingUtil.setContentView(this, R.layout.activity_register)
        activityRegisterBinding.registerActivityHandler=this
        userViewModel=ViewModelProviders.of(this, UserViewModel.Factory(this)).get(UserViewModel::class.java)

    }

    override fun onRegisterClick(view: View) {
        observeRegister(
            User(activityRegisterBinding.edtName.text.toString(),
                            activityRegisterBinding.edtEmail.text.toString(),
                            activityRegisterBinding.edtMobile.text.toString(),
                            activityRegisterBinding.edtPassword.text.toString())
        )
    }

    private fun observeRegister(user: User){
        userViewModel?.registerUser(user).observe(this, Observer { user->
            if (user != null) {
                Toasty.success(applicationContext, "Register Success", Toast.LENGTH_SHORT).show()
            }else{
                Toasty.error(applicationContext, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
