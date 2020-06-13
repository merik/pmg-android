package com.dmc.sw.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dmc.sw.framework.db.entities.MovieCharacterCrossRef
import com.dmc.sw.framework.db.entities.MovieCharacterEntity
import com.dmc.sw.framework.db.entities.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieCharacterEntity::class, MovieCharacterCrossRef::class],
    version = 1,
    exportSchema = true     // TODO("exportSchema = false in Prod" )
)
abstract class SwDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: SwDatabase? = null

        fun getDatabase(context: Context): SwDatabase {
            var tempInstance =
                INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SwDatabase::class.java,
                    "sw_database"
                )
                    .build()

                INSTANCE = instance
                return instance

            }
        }
    }

}