package com.sergionaude.core.usecase

import com.sergionaude.core.data.Note
import com.sergionaude.core.repository.NoteRepository

class RemoveNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(noteValue = note)
}
