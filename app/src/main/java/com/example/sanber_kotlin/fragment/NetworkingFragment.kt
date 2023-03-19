package com.example.sanber_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanber_kotlin.R
import com.example.sanber_kotlin.adapter.AdapterCat
import com.example.sanber_kotlin.api.APIConfig
import com.example.sanber_kotlin.data.ResponseCat
import com.example.sanber_kotlin.databinding.FragmentHomeBinding
import com.example.sanber_kotlin.databinding.FragmentNetworkingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkingFragment : Fragment() {

    private var _binding: FragmentNetworkingBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNetworkingBinding.inflate(inflater, container, false)

        APIConfig.getService().getBreeds().enqueue(object : Callback<ResponseCat> {
            override fun onResponse(call: Call<ResponseCat>, response: Response<ResponseCat>) {
                if (response.isSuccessful) {
                    val responseCat = response.body()
                    val dataItem = responseCat?.data
                    val adapterCat = AdapterCat(dataItem)
                    binding.rvCat.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                        adapter = adapterCat
                    }

                }
            }

            override fun onFailure(call: Call<ResponseCat>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}