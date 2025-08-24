package ec.repositoriocompartido.androidmasterviu2.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {
    @Insert
    fun insert(nota: Nota): Long

    @Update
    fun update(nota: Nota)

    @Delete
    fun delete(nota: Nota)

    @Query("SELECT * FROM nota WHERE nombreUsuario = :user ORDER BY fecha DESC")
    fun getNotasForUser(user: String): Flow<List<Nota>>

    @Query("SELECT * FROM nota WHERE id = :id LIMIT 1")
    fun getById(id: Int): Nota?


    @Query("SELECT detalle FROM nota WHERE nombreUsuario = :usuario ORDER BY fecha DESC LIMIT 1")
    fun getUltimoDetalle(usuario: String): String?


    @Query("SELECT COUNT(*) FROM nota WHERE nombreUsuario = :usuario")
    fun countByUser(usuario: String): Int



    // en NotaDao
    @Query("""
  SELECT SUM(
    CASE
      WHEN trim(detalle) = '' THEN 0
      ELSE length(trim(detalle)) - length(replace(trim(detalle), ' ', '')) + 1
    END
  ) FROM nota WHERE nombreUsuario = :usuario
""")
    fun countWordsApproxByUser(usuario: String): Int?


}
