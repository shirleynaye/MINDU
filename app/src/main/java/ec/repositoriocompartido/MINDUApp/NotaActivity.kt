package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import ec.repositoriocompartido.androidmasterviu2.R
import ec.repositoriocompartido.androidmasterviu2.db.AppDatabase
import ec.repositoriocompartido.androidmasterviu2.db.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_nota)
        val fec= findViewById<TextView>(R.id.card2_textLeft)
        val fechaActualc = currentDateTimeIso()
        fec.text = fechaActualc

        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""


        val numA= findViewById<TextView>(R.id.card1_row3_textLeft)
        val palabras= findViewById<TextView>(R.id.card1_row3_textRight)

        lifecycleScope.launch {
            val count = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(applicationContext).notaDao().countByUser(usuario)
            }
            val count2 = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(applicationContext).notaDao().countWordsApproxByUser(usuario)
            }
            // ahora en UI thread
            numA.text=count.toString()
            palabras.text=count2.toString()
        }





        val rootLayout = findViewById<ConstraintLayout>(R.id.rootNota)
        rootLayout.setBackgroundColor(Color.parseColor("#FFEBEE"))


        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayoutx = findViewById<ConstraintLayout>(R.id.rootNota)
        rootLayoutx.setBackgroundColor(Color.parseColor(color))



















        val txtUltima = findViewById<TextView>(R.id.tvParagraph)
        val db = AppDatabase.getInstance(applicationContext)
        val notaDao = db.notaDao()


        lifecycleScope.launch {
            val ultimaNota = withContext(Dispatchers.IO) {
                notaDao.getUltimoDetalle(usuario)  // devuelve String? directamente
            }
            txtUltima.text = ultimaNota ?: "No tienes notas aún"
        }














        val imgTopRight = findViewById<ImageView>(R.id.card1_row1_imageRight)
        val resId = resources.getIdentifier(avatar, "drawable", packageName)
        if (resId != 0) {
            imgTopRight.setImageResource(resId)
        }

        val itxtTopLeft = findViewById<TextView>(R.id.card1_row1_textLeft)
        itxtTopLeft.text =usuario


        val btnVolver=findViewById<ImageView>(R.id.card1_row1_imageLeft)
        btnVolver.setOnClickListener {

            val intent = Intent(this@NotaActivity, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuario)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }


        val txtvertodas=findViewById<TextView>(R.id.card2_textRight)
        val imgAgregarNota=findViewById<ImageView>(R.id.finalCenteredImage)




        imgAgregarNota.setOnClickListener {
            val contenidoX = findViewById<EditText>(R.id.card4_input).text.toString()
            val contenidoXY = findViewById<EditText>(R.id.card4_input)

            val db = AppDatabase.getInstance(applicationContext)
            val notaDao = db.notaDao()

            // generar fecha actual en formato que ordene correctamente (ISO-like)
            val fechaActual = currentDateTimeIso()
            lifecycleScope.launch {
                val insertedId = withContext(Dispatchers.IO) {
                    try {
                        // ADAPTA: si tu data class Nota tiene otros parámetros, cámbialos aquí.
                        val nuevaNota = Nota(

                            nombreUsuario = usuario,
                            detalle = contenidoX,
                            fecha = fechaActual
                        )
                        notaDao.insert(nuevaNota)

                    } catch (e: Exception) {
                        e.printStackTrace()
                        -1L
                    }
                }

                if (insertedId > 0L) {
                    Toast.makeText(this@NotaActivity, "Nota guardada", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        val ultimaNota = withContext(Dispatchers.IO) {
                            notaDao.getUltimoDetalle(usuario)  // devuelve String? directamente
                        }
                        txtUltima.text = ultimaNota ?: "No tienes notas aún"
                    }
                    contenidoXY.setText("")
                    val numA= findViewById<TextView>(R.id.card1_row3_textLeft)
                    val palabras= findViewById<TextView>(R.id.card1_row3_textRight)

                    lifecycleScope.launch {
                        val count = withContext(Dispatchers.IO) {
                            AppDatabase.getInstance(applicationContext).notaDao().countByUser(usuario)
                        }
                        val count2 = withContext(Dispatchers.IO) {
                            AppDatabase.getInstance(applicationContext).notaDao().countWordsApproxByUser(usuario)
                        }
                        // ahora en UI thread
                        numA.text=count.toString()
                        palabras.text=count2.toString()
                    }


                } else {
                    Toast.makeText(this@NotaActivity, "Error guardando la nota", Toast.LENGTH_SHORT).show()
                }
            }
        }









        val txtNota=findViewById<TextView>(R.id.card4_input)
        txtvertodas.setOnClickListener {

            val intent = Intent(this@NotaActivity, ListaNotasActivity::class.java).apply {
                putExtra(ListaNotasActivity.EXTRA_USERX, usuario)
                putExtra(ListaNotasActivity.EXTRA_PINX, pin)
                putExtra(ListaNotasActivity.EXTRA_AVATARX, avatar)
                putExtra(InicialActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }



    }
    // helper para fecha
    fun currentDateTimeIso(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"
        const val EXTRA_COLOR = "extra_color"}
}