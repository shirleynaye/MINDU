package ec.repositoriocompartido.androidmasterviu2.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "nota",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["nombre"],
            childColumns = ["nombreUsuario"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [ Index("nombreUsuario") ]
)
data class Nota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val detalle: String,
    val fecha: String,    // "YYYY-MM-DD"
    val nombreUsuario: String
)
