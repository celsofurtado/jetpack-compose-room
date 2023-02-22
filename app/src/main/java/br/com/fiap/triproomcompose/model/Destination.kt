package br.com.fiap.triproomcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination")
data class Destination(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String = "",
    var country: String = "",
    var price: Double = 0.0,
    var description: String = ""
)
