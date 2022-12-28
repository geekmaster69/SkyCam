package com.example.skycam

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import android.os.Bundle
import android.util.Log
import com.example.skycam.BuildConfig.DEBUG
import com.example.skycam.databinding.ActivityMainBinding
import com.example.skycam.retrofit.APIService
import com.example.skycam.retrofit.UserInfo

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
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build()

        val service = retrofit.create(APIService::class.java)

        service.login(UserInfo("skiper0125",".SkIpEr0125.666.")).enqueue(
            object : Callback<DataResponseServer>{
                override fun onResponse(call: Call<DataResponseServer>, response: Response<DataResponseServer>) {

                    val result = response.body()
                    val responceCode = response.code()
                    mBinding.tvStatus.text = result?.Status.toString()
                    mBinding.tvName.text = result?.Message.toString()
                    mBinding.tvIdUser.text = responceCode.toString()
                    mBinding.tvUser.text = result?.Data?.user.toString()
                    mBinding.tvUnits.text = result?.Data?.units.toString()
                }

                override fun onFailure(call: Call<DataResponseServer>, t: Throwable) {
                    Log.e("Retrofit", "Problemas con el servidor")

                }
            }
        )

/*        val user = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        val service = retrofit.create(LoginService::class.java)

        lifecycleScope.launch {
            try {
                val response = service.login(UserInfoDto("skiper0125", ".SkIpEr0125.666."))

                mBinding.tvName.text = response.toString()
                Log.i("response", response.toString())

            } catch (e: Exception) {
                (e as? HttpException)?.let { checkError(e) }
            }
        }*/
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(
            HttpLoggingInterceptor().apply { level =
                if (DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE })
            .build()
    }
}