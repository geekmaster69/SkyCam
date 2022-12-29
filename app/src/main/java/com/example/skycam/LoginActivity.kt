package com.example.skycam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skycam.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupListener()
    }

    private fun setupListener() {
        mBinding.btnLogin.setOnClickListener {

        }
    }
}