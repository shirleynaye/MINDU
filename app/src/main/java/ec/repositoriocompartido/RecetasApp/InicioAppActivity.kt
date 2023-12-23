package ec.repositoriocompartido.RecetasApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ec.repositoriocompartido.androidmasterviu2.R

class InicioAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_app)

        val btnEmpezar= findViewById<Button>(R.id.btnEmpezar)
        val btnFin= findViewById<Button>(R.id.btnFinalizar)
        btnEmpezar.setOnClickListener {

            val intent=Intent(this, MetaActivity::class.java)

            startActivity(intent)
        }
        btnFin.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }

    }

}