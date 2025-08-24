package ec.repositoriocompartido.androidmasterviu2.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(usuario: Usuario): Long

    @Query("SELECT COUNT(*) FROM usuario WHERE nombre = :nombre")
    fun countByName(nombre: String): Int

    @Query("SELECT * FROM usuario WHERE nombre = :nombre LIMIT 1")
    fun getByName(nombre: String): Usuario?

    @Delete
    fun delete(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE nombre = :nombre AND pin = :pin LIMIT 1")
    fun login(nombre: String, pin: String): Usuario?

    @Query("""
        UPDATE usuario 
        SET nombre = :nuevoNombre, pin = :pin, avatar = :avatar 
        WHERE nombre = :viejoNombre
    """)
    fun updateNombre(viejoNombre: String, nuevoNombre: String, pin: String, avatar: String?): Int
}
