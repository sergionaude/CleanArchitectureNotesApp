package com.sergionaude.core.usecase

import com.sergionaude.core.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteValue(idNote = id)
}
