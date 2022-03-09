package eu.codeplumbers.kotlinstarter.feature_demo.data.remote.dto

import eu.codeplumbers.kotlinstarter.feature_demo.data.local.entities.NoteEntity


data class NoteDto(
    val title: String,
    val content: String,
    val timestamp: Long,
) {
    fun toNoteEntity(): NoteEntity {
        return NoteEntity(
            title= title,
            content= content,
            timestamp= timestamp,
        )
    }
}