package eu.codeplumbers.kotlinstarter.feature_demo.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import eu.codeplumbers.kotlinstarter.feature_demo.data.util.JsonParser
import eu.codeplumbers.kotlinstarter.feature_demo.domain.model.Note

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromNoteJson(json: String): List<Note> {
        return jsonParser.fromJson<ArrayList<Note>>(
            json,
            object : TypeToken<ArrayList<Note>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toNotesJson(meanings: List<Note>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Note>>(){}.type
        ) ?: "[]"
    }
}