package com.example.room.di

import android.content.Context
import androidx.room.Room
import com.example.room.TaskRoomDatabase
import com.example.room.TaskappDAO
import com.example.room.models.TaskAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Singleton
    @Provides
    fun provideAPIService():TaskAPI{
        val builder=Retrofit.Builder().baseUrl("http://13.232.169.202:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        return builder.create(TaskAPI::class.java)

    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context):TaskRoomDatabase{
       val builder= Room.databaseBuilder(context,TaskRoomDatabase::class.java,"task_db")
        builder.fallbackToDestructiveMigration()
        return builder.build()

    }

    @Singleton
    @Provides
    fun provideTaskDAO(db:TaskRoomDatabase):TaskappDAO{
        return db.getTaskDAO()
    }
}