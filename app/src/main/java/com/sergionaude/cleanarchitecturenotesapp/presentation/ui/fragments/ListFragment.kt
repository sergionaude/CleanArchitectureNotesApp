package com.sergionaude.cleanarchitecturenotesapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergionaude.cleanarchitecturenotesapp.databinding.FragmentListBinding
import com.sergionaude.cleanarchitecturenotesapp.framework.vm.ListViewModel
import com.sergionaude.cleanarchitecturenotesapp.presentation.recycler.ListAction
import com.sergionaude.cleanarchitecturenotesapp.presentation.recycler.NoteListAdapter

class ListFragment :
    Fragment(),
    ListAction {
    private val viewModel: ListViewModel by viewModels()
    private var binding: FragmentListBinding? = null
    private var noteListAdapter = NoteListAdapter(noteList = mutableListOf(), listAction = this)

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
            goToNoteDetails()
        }
        binding?.notesList?.apply {
            adapter = noteListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }

    private fun initObserver() {
        viewModel.notesList.observe(viewLifecycleOwner) { notes ->
            println(
                "${notes.size}",
            )
            if (notes.isNotEmpty()) {
                binding?.notesProgressBar?.visibility = View.GONE
                binding?.notesList?.visibility = View.VISIBLE
                noteListAdapter.updateNotes(
                    notes.sortedBy {
                        it.updatedTime
                    },
                )
            } else {
                binding?.apply {
                    notesProgressBar.visibility = View.VISIBLE
                    notesList.visibility = View.GONE
                }
            }
        }
    }

    private fun goToNoteDetails(idNote: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(idNote)
        binding?.notesList?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onClick(id: Long) {
        goToNoteDetails(idNote = id)
    }
}
