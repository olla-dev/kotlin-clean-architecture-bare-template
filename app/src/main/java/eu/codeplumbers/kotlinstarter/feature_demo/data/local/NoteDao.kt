package eu.codeplumbers.kotlinstarter.feature_demo.data.local

import androidx.room.*
import eu.codeplumbers.kotlinstarter.feature_demo.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("Select * from noteentity")
    fun getNotes(): List<NoteEntity>

    @Query("Select * from noteentity where id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<NoteEntity>)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM noteentity WHERE content IN(:contents)")
    suspend fun deleteNotes(contents: List<String>)
}