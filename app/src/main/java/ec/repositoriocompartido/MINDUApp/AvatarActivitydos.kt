package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import ec.repositoriocompartido.androidmasterviu2.R
import ec.repositoriocompartido.androidmasterviu2.db.AppDatabase
import ec.repositoriocompartido.androidmasterviu2.db.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvatarActivitydos : AppCompatActivity() {
    private var previouslySelected: ImageView? = null
    private var avatarSeleccionado: String = ""
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_PIN  = "extra_pin"
        const val EXTRA_USERVIEJO  = "extra_userviejo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatardos)


        val usuario = intent.getStringExtra(EXTRA_USER) ?: ""
        val pin = intent.getStringExtra(EXTRA_PIN) ?: ""
        val usuarioviejo = intent.getStringExtra(EXTRA_USERVIEJO) ?: ""
        var avatar = "avatarSeleccionado"
        // ejemplo: mostrar en TextViews (pon los TextView en tu layout)
        //val tvUser = findViewById<TextView>(R.id.tvUser)


        //tvUser.text = usuario

        // ********************img superior que muestra la selección*************************
        val ivAvatar = findViewById<ImageView>(R.id.ivAvatar)
        val btnRegsitrar=findViewById<Button>(R.id.btnCerrar)

        val avatares = arrayOf(
            R.id.avatar1, R.id.avatar2, R.id.avatar3,
            R.id.avatar4, R.id.avatar5, R.id.avatar6,
            R.id.avatar7, R.id.avatar8, R.id.avatar9,
            R.id.avatar10, R.id.avatar11, R.id.avatar12
        )

        avatares.forEachIndexed { index, avatarId ->
            val img = findViewById<ImageView>(avatarId)

            img.setOnClickListener { view ->
                previouslySelected?.isSelected = false

                view.isSelected = true
                previouslySelected = view as ImageView

                avatarSeleccionado = resources.getResourceEntryName(view.id)
                val idSeleccionado = view.id

                val drawableName = avatarSeleccionado
                avatar=drawableName
                val drawableRes = resources.getIdentifier(drawableName, "drawable", packageName)
                if (drawableRes != 0) {
                    ivAvatar.setImageResource(drawableRes)
                } else {
                    ivAvatar.setImageDrawable((view as ImageView).drawable)
                }

            }
        }

        btnRegsitrar.setOnClickListener {
            // obtener valores desde UI (ajusta los nombres a tus variables/edits)
            val nombre = usuario.trim() // o etUsuario.text.toString().trim()
            val pinValue = pin.trim()   // o la variable pin que tengas
            val avatarName = avatar     // string con nombre del drawable, p.e. "avatar1"

            // validaciones básicas en UI thread
            if (nombre.isEmpty()) {
                Toast.makeText(
                    this@AvatarActivitydos,
                    "Ingrese el nombre de usuario",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (pinValue.length < 4) {
                Toast.makeText(
                    this@AvatarActivitydos,
                    "El PIN debe tener 4 dígitos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // acceso a la BD
            val db = AppDatabase.getInstance(applicationContext)
            val usuarioDao = db.usuarioDao()
            val notaDao = db.notaDao()

            // operación asíncrona
            lifecycleScope.launch {
                val alreadyExists = withContext(Dispatchers.IO) {
                    usuarioDao.countByName(nombre) > 0   // usa countByName en tu DAO
                }

                if (alreadyExists) {
                    Toast.makeText(
                        this@AvatarActivitydos,
                        "El nombre ya está en uso",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }

                // intentar insertar (segunda línea de defensa ante carreras)
                val insertedOk = withContext(Dispatchers.IO) {
                    try {
                        val usuarioviejoc = intent.getStringExtra(EXTRA_USERVIEJO) ?: ""

                        usuarioDao.updateNombre(usuarioviejoc, nombre, pinValue, avatarName)



                        true
                    } catch (e: SQLiteConstraintException) {
                        false
                    } catch (e: Exception) {
                        // opcional: loguear e
                        false
                    }
                }

                if (insertedOk) {
                    Toast.makeText(this@AvatarActivitydos, "Registro exitoso", Toast.LENGTH_SHORT)
                        .show()

                    // solo si se registró, navegar y pasar extras
                    val intent = Intent(this@AvatarActivitydos, InicialActivity::class.java).apply {
                        putExtra(InicialActivity.EXTRA_USERX, nombre)
                        putExtra(InicialActivity.EXTRA_PINX, pinValue)
                        putExtra(InicialActivity.EXTRA_AVATARX, avatarName)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@AvatarActivitydos,
                        "No se pudo registrar (nombre en uso)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        }


    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }









}