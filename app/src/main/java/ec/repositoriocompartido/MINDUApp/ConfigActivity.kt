package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R

class ConfigActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"
        const val EXTRA_COLOR = "extra_color"}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""



        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayout = findViewById<ConstraintLayout>(R.id.rootConfig)
        rootLayout.setBackgroundColor(Color.parseColor(color))



        val imgTopRight = findViewById<ImageView>(R.id.imgTopRight)
        val resId = resources.getIdentifier(avatar, "drawable", packageName)
        if (resId != 0) {
            imgTopRight.setImageResource(resId)
        }








        val btnVolver=findViewById<ImageView>(R.id.imgTopLeft)
        btnVolver.setOnClickListener {
            val intent = Intent(this@ConfigActivity, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuario)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
                putExtra(InicialActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }


        val btnCerrar=findViewById<ImageView>(R.id.item4_img)
        btnCerrar.setOnClickListener {
            val intent = Intent(this@ConfigActivity, InicioAppActivity::class.java).apply {

            }
            startActivity(intent)
            finish()
        }






        val btnCuenta=findViewById<ImageView>(R.id.item1_img)
        btnCuenta.setOnClickListener {
            val intent = Intent(this@ConfigActivity,    Loguindos::class.java).apply {
                putExtra(Loguindos.EXTRA_USERX, usuario)
                putExtra(Loguindos.EXTRA_PINX, pin)
                putExtra(Loguindos.EXTRA_AVATARX, avatar)
                putExtra(InicialActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }


        val btnColor=findViewById<ImageView>(R.id.item3_img)
        btnColor.setOnClickListener {
            val intent = Intent(this@ConfigActivity,    ColorActivity::class.java).apply {
                putExtra(ColorActivity.EXTRA_USERX, usuario)
                putExtra(ColorActivity.EXTRA_PINX, pin)
                putExtra(ColorActivity.EXTRA_AVATARX, avatar)
            }
            startActivity(intent)
            finish()
        }


    }
}