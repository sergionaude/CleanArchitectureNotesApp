package com.sergionaude.cleanarchitecturenotesapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergionaude.cleanarchitecturenotesapp.R
import com.sergionaude.cleanarchitecturenotesapp.databinding.FragmentListBinding
import com.sergionaude.cleanarchitecturenotesapp.framework.vm.ListViewModel
import com.sergionaude.cleanarchitecturenotesapp.presentation.recycler.NoteListAdapter

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()
    private var binding: FragmentListBinding? = null
    private var noteListAdapter = NoteListAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding?.notesAddBtn?.setOnClickListener {
            findNavController().navigate(R.id.actionGoToNote)
        }
        binding?.notesList?.apply {
            adapter = noteListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.getAllNotes()
        initObserver()
    }

    private fun initObserver() {
        viewModel.notesList.observe(viewLifecycleOwner) { notes ->
            if (!notes.isNullOrEmpty()) {
                binding?.notesProgressBar?.visibility = View.GONE
                binding?.notesList?.visibility = View.VISIBLE
                noteListAdapter.updateNotes(
                    notes.sortedBy {
                        it.updatedTime
                    },
                )
            }
        }
    }
}
