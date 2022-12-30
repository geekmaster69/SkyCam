package com.example.skycam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.skycam.databinding.ActivityLoginBinding
import com.example.skycam.retrofit.APIService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val sp = getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        checkLogin(sp)


        mBinding.btnLogin.setOnClickListener { setDataUser(sp) }


    }
    private fun setDataUser(sp: SharedPreferences) {
        val user = mBinding.etEmail.text.toString()
        val password = mBinding.etPassword.text.toString()

        if (user.isNotEmpty() && password.isNotEmpty()){

            sp.edit().putString("user", user).apply()
            sp.edit().putString("password", password).apply()


            val retrofit = Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(APIService::class.java)

            lifecycleScope.launch {
                try {
                   val result = service.loginUser(user, password)
                    if (result.Status == "Success"){

                        sp.edit().putBoolean("active", true).apply()

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()

                    }else{
                        Toast.makeText(this@LoginActivity, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    (e as? HttpException)?.let {
                        when(it.code()){
                        }
                    }
                }
            }

        }else{
            mBinding.tilPassword.error
            mBinding.tilPassword.requestFocus()

            mBinding.tilEmail.error
            mBinding.tilEmail.requestFocus()
            Toast.makeText(this@LoginActivity, "Campos obligatorios", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkLogin(sp: SharedPreferences?) {
        if (sp!!.getBoolean("active", true)){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

    }

}