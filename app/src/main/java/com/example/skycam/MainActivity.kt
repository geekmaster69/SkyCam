package com.example.skycam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import android.os.Bundle
import android.widget.AbsSpinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skycam.data.network.model.UnitsEntity
import com.example.skycam.databinding.ActivityMainBinding
import com.example.skycam.main.Company
import com.example.skycam.main.adapter.CompanyAdapter
import com.example.skycam.retrofit.APIService
import kotlinx.coroutines.launch

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mCompanyAdapter: CompanyAdapter
    private lateinit var mLinearLayoutManager: RecyclerView.LayoutManager
    private val gson: Gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val sp = getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        setRecyclerVIew()
        mBinding.btnLogaut.setOnClickListener {
            logOut(sp)
        }



    }

    private fun logOut(sp: SharedPreferences?) {
        sp!!.edit().putBoolean("active", false).apply()
        startActivity(Intent(this,LoginActivity::class.java ))
        finish()
    }

    private fun setRecyclerVIew() {
        mCompanyAdapter = CompanyAdapter(mutableListOf() )
        mLinearLayoutManager = LinearLayoutManager(this)

        mBinding.rvCompany.apply {
            layoutManager = mLinearLayoutManager
            adapter = mCompanyAdapter
        }
    }

    private fun getData() {
        val sp = getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        val user = sp.getString("user","")
        val password = sp.getString("password", "")

       val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.loginUser(user!!, password!!)

                val units = result.Data.units
                val unitsList1 = service.getCamerasApi(units)

                updateUi(unitsList1, result)


            } catch (e: Exception) {

                Toast.makeText(this@MainActivity, "Error en el servidor", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun updateUi(result: UnitsEntity, comon: DataResponseServer) {
        val unitsConvert = result.DataCamera

        mBinding.tvName.text = unitsConvert.toString()
        mBinding.tvUser.text = result.Status
        mBinding.tvIdUser.text = comon.Status.toString()
    }
}