package br.com.fiap.triproomcompose.database.repository

import android.content.Context
import br.com.fiap.triproomcompose.database.DestinationDb
import br.com.fiap.triproomcompose.model.Destination

class DestinationRepository(context: Context) {

    // Obtain the database access
    private val db = DestinationDb.getDatabase(context).destinationDao()

    fun save(destination: Destination): Long {
        return db.save(destination)
    }

    fun update(destination: Destination): Int {
        return db.update(destination)
    }

    fun delete(destination: Destination): Int {
        return db.delete(destination)
    }

    fun getAll(): List<Destination> {
        return db.getAll()
    }

    fun getDestinationById(id: Int): Destination {
        return db.getDestinationById(id)
    }

}