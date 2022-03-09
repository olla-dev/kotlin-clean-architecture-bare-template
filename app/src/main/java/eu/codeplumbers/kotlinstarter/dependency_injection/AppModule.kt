package eu.codeplumbers.kotlinstarter.dependency_injection

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.codeplumbers.kotlinstarter.feature_demo.data.local.Converters
import eu.codeplumbers.kotlinstarter.feature_demo.data.local.NoteDatabase
import eu.codeplumbers.kotlinstarter.feature_demo.data.remote.NoteApi
import eu.codeplumbers.kotlinstarter.feature_demo.data.repository.NoteRepositoryImpl
import eu.codeplumbers.kotlinstarter.feature_demo.data.util.GsonParser
import eu.codeplumbers.kotlinstarter.feature_demo.domain.repository.NoteRepository
import eu.codeplumbers.kotlinstarter.feature_demo.domain.use_case.GetNotes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotes {
        return GetNotes(repository)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        db: NoteDatabase,
        api: NoteApi
    ): NoteRepository {
        return NoteRepositoryImpl(api, db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app, NoteDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteApi(): NoteApi {
        return Retrofit.Builder()
            .baseUrl(NoteApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApi::class.java)
    }
}