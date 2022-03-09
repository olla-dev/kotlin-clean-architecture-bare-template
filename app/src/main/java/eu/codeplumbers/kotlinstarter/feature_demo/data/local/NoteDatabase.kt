package eu.codeplumbers.kotlinstarter.feature_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eu.codeplumbers.kotlinstarter.feature_demo.data.local.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version= 1
)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}