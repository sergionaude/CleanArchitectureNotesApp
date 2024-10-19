package com.sergionaude.cleanarchitecturenotesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sergionaude.cleanarchitecturenotesapp.R
import com.sergionaude.cleanarchitecturenotesapp.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        binding?.floatingActionButton?.setOnClickListener {
            findNavController().navigate(R.id.actionGoToNote)
        }

        return binding?.root
    }
}
