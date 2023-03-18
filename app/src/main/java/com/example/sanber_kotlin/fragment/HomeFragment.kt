package com.example.sanber_kotlin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sanber_kotlin.R
import com.example.sanber_kotlin.activity.MoveActivity
import com.example.sanber_kotlin.activity.MoveWithDataActivity
import com.example.sanber_kotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.btnMoveFragmentData.text = "Haii Aku Aziz"
        binding.btnMoveFragment.setOnClickListener {  }

        binding.btnMoveFragment.setOnClickListener {
            val intent = Intent(activity, MoveActivity::class.java)
            startActivity(intent)
        }

        binding.btnMoveFragmentData.setOnClickListener {
            val intent = Intent(activity, MoveWithDataActivity::class.java)
            intent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Aziz")
            intent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
            startActivity(intent)
        }


        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMoveFragmentData.text = "Hello World!"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}