package eu.codeplumbers.kotlinstarter.feature_demo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.codeplumbers.kotlinstarter.feature_demo.domain.model.Note

@Entity
data class NoteEntity (
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
) {
    fun toNote(): Note {
        return Note(
            title= title,
            content= content,
            timestamp= timestamp,
        )
    }
}

class InvalidNoteException(message:String): Exception(message)