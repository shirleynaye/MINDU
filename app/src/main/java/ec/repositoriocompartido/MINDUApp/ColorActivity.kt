package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R

class ColorActivity : AppCompatActivity() {
    var colorAsig= "#E3F2FD"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)



        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""



        val btnVolvere=findViewById<TextView>(R.id.tvVolver)
        btnVolvere.setOnClickListener {
            val intent = Intent(this@ColorActivity, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuario)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
                putExtra(InicialActivity.EXTRA_COLOR, colorAsig)

            }
            startActivity(intent)
            finish()
        }







        val rootLayout = findViewById<ConstraintLayout>(R.id.rootLayout)
        val switchTema = findViewById<Switch>(R.id.switchTema)


        switchTema.setOnCheckedChangeListener { _, isChecked ->
            applyTheme(rootLayout, isChecked)

        }
    }
    private fun applyTheme(rootLayout: ConstraintLayout, darkMode: Boolean) {
        if (darkMode) {
            rootLayout.setBackgroundColor(Color.parseColor("#E3F2FD")) // Gris negruzco
            colorAsig="#E3F2FD"
        } else {
            rootLayout.setBackgroundColor(Color.parseColor("#8a8a8a")) // Blanco
            colorAsig="#4f4e4e"
        }
    }

    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"}
}


