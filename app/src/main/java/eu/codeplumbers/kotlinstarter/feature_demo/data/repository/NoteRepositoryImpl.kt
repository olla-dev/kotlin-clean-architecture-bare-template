package eu.codeplumbers.kotlinstarter.feature_demo.data.repository

import eu.codeplumbers.kotlinstarter.core.api.Resource
import eu.codeplumbers.kotlinstarter.feature_demo.data.local.NoteDao
import eu.codeplumbers.kotlinstarter.feature_demo.data.remote.NoteApi
import eu.codeplumbers.kotlinstarter.feature_demo.domain.model.Note
import eu.codeplumbers.kotlinstarter.feature_demo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class NoteRepositoryImpl(
    private val api: NoteApi,
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())

        val notes = dao.getNotes().map{ it.toNote() }
        emit(Resource.Loading(data = notes))

        try {
            val remoteNotes = api.getNotes()
            // update cached data
            dao.deleteNotes(remoteNotes.map { it.content })
            dao.insertNotes(remoteNotes.map { it.toNoteEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = notes
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = notes
                )
            )
        }

        val newNotes = dao.getNotes().map { it.toNote() }
        emit(Resource.Success(newNotes))
    }
}