package com.sergionaude.cleanarchitecturenotesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.sergionaude.cleanarchitecturenotesapp.databinding.FragmentNoteBinding
import com.sergionaude.cleanarchitecturenotesapp.framework.vm.NoteViewModel
import com.sergionaude.core.data.Note

class NoteFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()
    private var binding: FragmentNoteBinding? = null
    private lateinit var title: TextInputEditText
    private lateinit var description: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)

        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        title = binding!!.noteTitle
        description = binding!!.noteContent

        initObserver()
        binding?.noteBtnSave?.setOnClickListener {
            if (dataValidation()) {
                val time = System.currentTimeMillis()
                val currentNote =
                    Note(
                        id = 0L,
                        title = binding?.noteContent?.text?.toString()!!,
                        content = binding?.noteContent?.text?.toString()!!,
                        creationTime = time,
                        updatedTime = time,
                    )
                viewModel.saveNote(note = currentNote)
            } else {
                Toast
                    .makeText(context, "Please fill out all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun dataValidation(): Boolean =
        !(
            title.text?.toString()?.isEmpty() == true ||
                description.text?.toString()?.isEmpty() == true
        )

    private fun initObserver() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast
                    .makeText(context, "An error saving note has happen", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
