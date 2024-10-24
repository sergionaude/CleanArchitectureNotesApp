package com.sergionaude.cleanarchitecturenotesapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
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
import timber.log.Timber

class NoteFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()
    private var binding: FragmentNoteBinding? = null
    private lateinit var title: TextInputEditText
    private lateinit var description: TextInputEditText
    private var currentNote =
        Note(
            id = 0L,
            title = "",
            content = "",
            creationTime = 0L,
            updatedTime = 0L,
        )
    private var noteId = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).id
        }
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        title = binding!!.noteTitle
        description = binding!!.noteContent

        initObserver()
        userIntents()

        if (noteId != 0L) {
            viewModel.getNote(noteId)
            Timber.d("IdNote is not null and have call to viewmodel")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun dataValidation(): Boolean = !(title.text?.isEmpty() == true || description.text?.isEmpty() == true)

    private fun initObserver() {
        viewModel.run {
            saved.observe(viewLifecycleOwner) { isSaved ->
                if (isSaved) {
                    Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
            note.observe(viewLifecycleOwner) { noteValue ->
                if (noteValue != null) {
                    currentNote = noteValue
                    title.setText(noteValue.title)
                    description.setText(noteValue.content)
                    Timber.d("It was loaded a note successfully")
                }
            }
        }
    }

    private fun userIntents() {
        binding?.noteBtnSave?.setOnClickListener {
            if (dataValidation()) {
                val time = System.currentTimeMillis()
                currentNote.apply {
                    title = binding?.noteTitle?.text?.toString()!!
                    content = binding?.noteContent?.text?.toString()!!
                    if (currentNote.creationTime == 0L) {
                        creationTime = time
                    }
                    updatedTime = time
                }
                viewModel.saveNote(note = currentNote)
            } else {
                Toast.makeText(context, "Fill out all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.noteBtnDelete?.setOnClickListener {
            Log.e("TAG", "$currentNote")
            viewModel.removeNote(noteValue = currentNote)
            Timber.d("User is trying to remove note")
            findNavController().popBackStack()
        }
    }
}
