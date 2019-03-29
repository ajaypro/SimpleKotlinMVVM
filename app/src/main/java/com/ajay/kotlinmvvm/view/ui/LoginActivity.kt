package com.ajay.kotlinmvvm.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ajay.kotlinmvvm.R
import com.ajay.kotlinmvvm.databinding.ActivityLoginBinding
import com.ajay.kotlinmvvm.view.callback.LoginActivityCallback
import com.ajay.kotlinmvvm.viewmodel.UserViewModel
import es.dmoral.toasty.Toasty

class LoginActivity : AppCompatActivity(), LoginActivityCallback {

    private var activityLoginBinding: ActivityLoginBinding?=null
    private var userViewModel: UserViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        activityLoginBinding?.loginActivityCallback=this
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
