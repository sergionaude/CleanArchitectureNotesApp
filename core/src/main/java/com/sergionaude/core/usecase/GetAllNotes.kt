package com.sergionaude.core.usecase

import com.sergionaude.core.repository.NoteRepository

class GetAllNotes(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}
