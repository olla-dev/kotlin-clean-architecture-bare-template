package eu.codeplumbers.kotlinstarter.feature_demo.domain.use_case

import eu.codeplumbers.kotlinstarter.core.api.Resource
import eu.codeplumbers.kotlinstarter.feature_demo.domain.model.Note
import eu.codeplumbers.kotlinstarter.feature_demo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<Note>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getNotes()
    }
}