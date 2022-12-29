package com.example.skycam

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.skycam.BuildConfig.DEBUG
import com.example.skycam.data.network.CamService
import com.example.skycam.databinding.ActivityMainBinding
import com.example.skycam.retrofit.APIService
import kotlinx.coroutines.launch

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val gson: Gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

       val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.loginUser("skiper0125", ".SkIpEr0125.666.")
                updateUi(result)
            } catch (e: Exception) {

                Toast.makeText(this@MainActivity, "Error en el servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUi(result: DataResponseServer) {
        val responceCode = result.Status
        mBinding.tvName.text = result.Data.name
        mBinding.tvIdUser.text = result.Data.idUser
        mBinding.tvUser.text = result.Data.user
        mBinding.tvUnits.text = result.Data.units
    }
}