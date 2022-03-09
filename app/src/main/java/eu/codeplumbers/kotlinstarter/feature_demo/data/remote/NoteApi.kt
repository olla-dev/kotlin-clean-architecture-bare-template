package eu.codeplumbers.kotlinstarter.feature_demo.data.remote

import eu.codeplumbers.kotlinstarter.feature_demo.data.remote.dto.NoteDto
import retrofit2.http.GET

interface NoteApi {
    @GET("/api/notes")
    suspend fun getNotes(): List<NoteDto>

    companion object {
        const val BASE_URL = "https://api"
    }
}