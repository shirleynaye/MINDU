package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R

class BlogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""

        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayout = findViewById<ConstraintLayout>(R.id.rootBlog)
        rootLayout.setBackgroundColor(Color.parseColor(color))












        val imgTopRight = findViewById<ImageView>(R.id.imgTopRight)
        val resId = resources.getIdentifier(avatar, "drawable", packageName)
        if (resId != 0) {
            imgTopRight.setImageResource(resId)
        }

        val btnVolver=findViewById<ImageView>(R.id.imgTopLeft)
        btnVolver.setOnClickListener {
            val intent = Intent(this@BlogActivity, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuario)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
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