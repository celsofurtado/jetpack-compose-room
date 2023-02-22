package br.com.fiap.triproomcompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.triproomcompose.database.dao.DestinationDao
import br.com.fiap.triproomcompose.model.Destination

@Database(entities = [Destination::class], version = 2)
abstract class DestinationDb(): RoomDatabase() {

    abstract fun destinationDao(): DestinationDao

    companion object {

        private lateinit var instance: DestinationDb

        fun getDatabase(context: Context): DestinationDb {

            if(!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        DestinationDb::class.java,
                        "destination_db"
                    )
                    .allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
            }

            return instance

        }

    }

}