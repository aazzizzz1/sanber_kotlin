package com.example.sanber_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sanber_kotlin.R
import com.example.sanber_kotlin.data.DataItem

class AdapterCat(val dataCat: List<DataItem?>?) : RecyclerView.Adapter<AdapterCat.ListViewHolder>() {

    class ListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvBreed = view.findViewById<TextView>(R.id.tv_breed_fill)
        val tvCountry = view.findViewById<TextView>(R.id.tv_country_fill)
        val tvOrigin = view.findViewById<TextView>(R.id.tv_origin_fill)
        val tvCoat = view.findViewById<TextView>(R.id.tv_coat_fill)
        val tvPattern = view.findViewById<TextView>(R.id.tv_pattern_fill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_cat, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.apply {
            tvBreed.text = dataCat?.get(position)?.breed
            tvCountry.text = dataCat?.get(position)?.country
            tvOrigin.text = dataCat?.get(position)?.origin
            tvCoat.text = dataCat?.get(position)?.coat
            tvPattern.text = dataCat?.get(position)?.pattern

            itemView.setOnClickListener {
                val breed = dataCat?.get(position)?.breed
                Toast.makeText(holder.itemView.context, "${breed}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        if (dataCat != null) {
            return dataCat.size
        }
        return 0
    }
}