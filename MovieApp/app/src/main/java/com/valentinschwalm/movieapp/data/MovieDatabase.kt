package com.valentinschwalm.movieapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valentinschwalm.movieapp.models.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDAO(): IMovieDAO

    companion object {
        @Volatile private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}