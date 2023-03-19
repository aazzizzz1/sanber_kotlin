package com.example.sanber_kotlin.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanber_kotlin.R
import com.example.sanber_kotlin.adapter.CoffeaListAdapter
import com.example.sanber_kotlin.data.Coffea
import com.example.sanber_kotlin.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Coffea>()
    var coffeaListAdapter = CoffeaListAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.svSearch.setOnQueryTextListener(SearchQueryTextListener(this::filterList))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.addAll(listCoffea)
        showRecyclerList()

    }

    private fun filterList(query: String?) {
        query ?: return

        val filteredList = listCoffea.filter { it.nama.contains(query) }

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            coffeaListAdapter.setFilteredList(filteredList as ArrayList<Coffea>)
        }
    }


    private val listCoffea: ArrayList<Coffea>
        get() {
            val dataName = resources.getStringArray(R.array.data_nama)
            val dataDescription = resources.getStringArray(R.array.data_deskripsi)
            val dataPhoto = resources.obtainTypedArray(R.array.data_image)
            val listCoffea = java.util.ArrayList<Coffea>()
            for (i in dataName.indices) {
                val coffea = Coffea(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listCoffea.add(coffea)
            }
            dataPhoto.recycle() // tambahkan ini untuk menghindari memory leak
            return listCoffea
        }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvCoffea.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.rvCoffea.layoutManager = LinearLayoutManager(context)
        }
        binding.rvCoffea.adapter = coffeaListAdapter
        coffeaListAdapter.setOnItemClickCallback(object : CoffeaListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Coffea) {
                showSelectedCoffea(data)
            }
        })
    }

    private fun showSelectedCoffea(coffea: Coffea) {
        Toast.makeText(context, "Kamu memilih " + coffea.nama, Toast.LENGTH_SHORT).show()
    }

    class SearchQueryTextListener(private val filterList: (String?) -> Unit) : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            filterList(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}