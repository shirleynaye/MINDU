package ec.repositoriocompartido.androidmasterviu2.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey val nombre: String,
    val pin: String,
    val avatar: String? = null
)
