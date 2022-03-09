package eu.codeplumbers.kotlinstarter.feature_demo.domain.repository

import eu.codeplumbers.kotlinstarter.core.api.Resource
import eu.codeplumbers.kotlinstarter.feature_demo.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<Resource<List<Note>>>
}