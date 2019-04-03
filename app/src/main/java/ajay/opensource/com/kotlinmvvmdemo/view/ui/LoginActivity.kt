package ajay.opensource.com.kotlinmvvmdemo.view.ui

import ajay.opensource.com.kotlinmvvmdemo.R
import ajay.opensource.com.kotlinmvvmdemo.databinding.ActivityLoginBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ajay.opensource.com.kotlinmvvmdemo.view.handler.LoginActivityHandler
import ajay.opensource.com.kotlinmvvmdemo.viewmodel.UserViewModel
import android.widget.Toast
import es.dmoral.toasty.Toasty

class LoginActivity : AppCompatActivity(), LoginActivityHandler {

    private var activityLoginBinding: ActivityLoginBinding?=null
    private var userViewModel: UserViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityLoginBinding?.loginActivityHandler=this
        userViewModel= ViewModelProviders.of(this, UserViewModel.Factory(this)).get(UserViewModel::class.java)
    }

    override fun onLoginClick(view: View) {
        observeLogin(activityLoginBinding?.edtEmail?.text.toString(), activityLoginBinding?.edtPassword?.text.toString())
    }

    override fun onRegisterClick(view: View) {
        val mainIntent=Intent(this, RegisterActivity::class.java)
        startActivity(mainIntent)
    }

    private fun observeLogin(email: String, password: String){
        userViewModel?.loginUser(email, password)?.observe(this, Observer { loginUser->
            if(loginUser!=null){
                Toasty.success(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                val mainIntent=Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }else{
                Toasty.error(applicationContext, "Login Failed, please try again", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
