package com.malik.noteappmvi.core.di

import android.app.Application
import androidx.room.Room
import com.malik.noteappmvi.add_note.domain.use_case.SearchImages
import com.malik.noteappmvi.add_note.domain.use_case.UpsertNote
import com.malik.noteappmvi.core.data.local.NoteDb
import com.malik.noteappmvi.core.data.remote.api.ImagesApi
import com.malik.noteappmvi.core.data.repository.ImagesRepositoryImpl
import com.malik.noteappmvi.core.data.repository.NoteRepositoryImple
import com.malik.noteappmvi.core.domain.repository.ImagesRepository
import com.malik.noteappmvi.core.domain.repository.NoteRepository
import com.malik.noteappmvi.note_list.domain.use_case.DeleteNote
import com.malik.noteappmvi.note_list.domain.use_case.GetAllNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

 @Provides
 @Singleton
 fun provideNoteDb(application: Application): NoteDb {

     return Room.databaseBuilder(
         application,
         NoteDb::class.java,
         "note_db.db)"
     ).build()
 }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDb: NoteDb
    ):NoteRepository{
        return NoteRepositoryImple(noteDb)
    }

    @Provides
    @Singleton
    fun providegetAllNotesUseCase(
        noteRepository: NoteRepository
    ):GetAllNotes{
        return GetAllNotes(noteRepository)
    }


    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
        noteRepository: NoteRepository
    ):DeleteNote{
        return DeleteNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideUpsertNoteUseCase(
        noteRepository: NoteRepository
    ):UpsertNote{
        return UpsertNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideImageApi(): ImagesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ImagesApi.BASE_URL)
            .build()
            .create(ImagesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImagesRepository(
        imagesApi: ImagesApi
    ): ImagesRepository {
        return ImagesRepositoryImpl(imagesApi)
    }

    @Provides
    @Singleton
    fun provideSearchImagesUseCase(
        imagesRepository: ImagesRepository
    ): SearchImages {
        return SearchImages(imagesRepository)
    }











}