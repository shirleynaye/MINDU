package ec.repositoriocompartido.MINDUApp

import NotaAdapter
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R
import ec.repositoriocompartido.androidmasterviu2.db.AppDatabase
import kotlinx.coroutines.launch

class ListaNotasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val recycler = findViewById<RecyclerView>(R.id.recyclerNotas)
        recycler.layoutManager = LinearLayoutManager(this)

        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""



        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayoutx = findViewById<ConstraintLayout>(R.id.rootLista)
        rootLayoutx.setBackgroundColor(Color.parseColor(color))



        val db = AppDatabase.getInstance(applicationContext)
        val notaDao = db.notaDao()

        // Cargar notas del usuario desde la DB
        lifecycleScope.launch {
            notaDao.getNotasForUser(usuario).collect { listaNotas ->
                recycler.adapter = NotaAdapter(listaNotas)
            }
        }

        val btnVolver = findViewById<ImageView>(R.id.btnBack)
        btnVolver.setOnClickListener {
            val intent = Intent(this@ListaNotasActivity, NotaActivity::class.java).apply {
                putExtra(NotaActivity.EXTRA_USERX, usuario)
                putExtra(NotaActivity.EXTRA_PINX, pin)
                putExtra(NotaActivity.EXTRA_AVATARX, avatar)
                putExtra(InicialActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"
        const val EXTRA_COLOR = "extra_color"
    }
}