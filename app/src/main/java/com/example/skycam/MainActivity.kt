package com.example.skycam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.chuckerteam.chucker.api.ChuckerCollector
import com.example.skycam.databinding.ActivityMainBinding
import com.example.skycam.http.base.BaseHttpClient
import com.example.skycam.http.base.BaseRetrofit
import com.example.skycam.http.base.RxJavaCustomCallAdapterFactory
import com.example.skycam.retrofit.LoginResponse
import com.example.skycam.retrofit.LoginService
import com.example.skycam.retrofit.UserInfo
import com.google.gson.Gson
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val user = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addCallAdapterFactory(RxJavaCustomCallAdapterFactory.create()) //Has to be on top of the other adapters
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))

            .build()

        val service = retrofit.create(LoginService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.login(UserInfo("skiper0125", ".SkIpEr0125.666."))

              mBinding.tvName.text = result.toString()


            } catch (e: Exception) {
                (e as? HttpException)?.let { checkError(e) }


            }
        }

   /*     service.login(UserInfo(user, password)).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                   when(response.code()){
                       200 -> {
                           val result = response.body()
                           Toast.makeText(this@MainActivity, "Peticion exitosa", Toast.LENGTH_SHORT).show()
                       }
                       400 -> {
                           Toast.makeText(this@MainActivity, "Error en la peticion", Toast.LENGTH_SHORT).show()

                       }
                   }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Retrofit", "Probele,as con el servidor")
                }
            }
        )*/
    }

    private suspend fun checkError(e: HttpException)= when(e.code()){
        400 -> {
            mBinding.tvName.text = "error 400"
        }
        else -> {
            mBinding.tvName.text = "error desconocido"
        }
    }
}