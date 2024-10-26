package com.sergionaude.cleanarchitecturenotesapp.framework

import com.sergionaude.core.usecase.AddNote
import com.sergionaude.core.usecase.GetAllNotes
import com.sergionaude.core.usecase.GetNote
import com.sergionaude.core.usecase.GetWordCount
import com.sergionaude.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount,
)
