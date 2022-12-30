package com.example.skycam.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skycam.R
import com.example.skycam.databinding.ItemCompanyBinding
import com.example.skycam.main.Company

class CompanyAdapter(private val company: List<Company>): RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = company[position]

        with(holder){
            binding.tvCompany.text = company.name
        }
    }

    override fun getItemCount(): Int = company.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCompanyBinding.bind(view)

    }
}