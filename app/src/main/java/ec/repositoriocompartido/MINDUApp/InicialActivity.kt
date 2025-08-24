package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R

class InicialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)


        val usuario = intent.getStringExtra( EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra( EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra( EXTRA_AVATARX) ?: ""
        val color = intent.getStringExtra( EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayout = findViewById<ConstraintLayout>(R.id.rootInicial)
        rootLayout.setBackgroundColor(Color.parseColor(color))

        val imgTopRight = findViewById<ImageView>(R.id.imgTopRight)
        val resId = resources.getIdentifier(avatar, "drawable", packageName)
        if (resId != 0) {
            imgTopRight.setImageResource(resId)
        }




        val btnAvatar=findViewById<ImageView>(R.id.imgTopRight)
        btnAvatar.setOnClickListener {

            val intent = Intent(this@InicialActivity, ConfigActivity::class.java).apply {
                putExtra(ConfigActivity.EXTRA_USERX, usuario)
                putExtra(ConfigActivity.EXTRA_PINX, pin)
                putExtra(ConfigActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }

        val btnNota=findViewById<ImageView>(R.id.large1)
        btnNota.setOnClickListener {

            val intent = Intent(this@InicialActivity, NotaActivity::class.java).apply {
                putExtra(NotaActivity.EXTRA_USERX, usuario)
                putExtra(NotaActivity.EXTRA_PINX, pin)
                putExtra(NotaActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }


        val btnRespira=findViewById<ImageView>(R.id.large2)
        btnRespira.setOnClickListener {

            val intent = Intent(this@InicialActivity, RespiraActivity::class.java).apply {
                putExtra(RespiraActivity.EXTRA_USERX, usuario)
                putExtra(RespiraActivity.EXTRA_PINX, pin)
                putExtra(RespiraActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }

        val btnBlog=findViewById<ImageView>(R.id.large3)
        btnBlog.setOnClickListener {

            val intent = Intent(this@InicialActivity, BlogActivity::class.java).apply {
                putExtra(BlogActivity.EXTRA_USERX, usuario)
                putExtra(BlogActivity.EXTRA_PINX, pin)
                putExtra(BlogActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }


        val btnInspira=findViewById<ImageView>(R.id.large4)
        btnInspira.setOnClickListener {

            val intent = Intent(this@InicialActivity, InspiracionActivity::class.java).apply {
                putExtra(RespiraActivity.EXTRA_USERX, usuario)
                putExtra(RespiraActivity.EXTRA_PINX, pin)
                putExtra(RespiraActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"
        const val EXTRA_COLOR = "extra_color"}
}