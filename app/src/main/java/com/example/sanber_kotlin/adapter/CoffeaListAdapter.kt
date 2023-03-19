package com.example.sanber_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanber_kotlin.R
import com.example.sanber_kotlin.data.Coffea

class CoffeaListAdapter(private var listCoffea: ArrayList<Coffea>) : RecyclerView.Adapter<CoffeaListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_coffea, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, deskripsi, image) = listCoffea[position]
        holder.apply {
            imgItem.setImageResource(image)
            tvNama.text = nama
            tvDeskripsi.text = deskripsi
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listCoffea[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listCoffea.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgItem: ImageView = itemView.findViewById(R.id.img_item)
        var tvNama: TextView = itemView.findViewById(R.id.tv_item_nama)
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tv_item_deskripsi)
    }

    fun setFilteredList(mList: ArrayList<Coffea>) {
        listCoffea.addAll(mList)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Coffea)
    }

}