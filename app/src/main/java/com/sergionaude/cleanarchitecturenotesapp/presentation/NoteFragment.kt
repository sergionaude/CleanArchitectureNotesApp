package com.sergionaude.cleanarchitecturenotesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sergionaude.cleanarchitecturenotesapp.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private var binding: FragmentNoteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)

        binding?.floatingActionButton2?.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding?.root
    }
}
