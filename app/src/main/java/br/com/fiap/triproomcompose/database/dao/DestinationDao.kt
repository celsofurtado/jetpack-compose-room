package br.com.fiap.triproomcompose.database.dao

import androidx.room.*
import br.com.fiap.triproomcompose.model.Destination

@Dao
interface DestinationDao {

    @Insert
    fun save(destination: Destination): Long // Id from created destination

    @Update
    fun update(destination: Destination): Int // Number of updated lines in database

    @Delete
    fun delete(destination: Destination): Int // Number of deleted lines in database

    @Query("SELECT * FROM destination WHERE id = :id")
    fun getDestinationById(id: Int): Destination

    @Query("SELECT * FROM destination ORDER BY name ASC")
    fun getAll(): List<Destination>

}