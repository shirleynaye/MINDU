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

class AvatarActivity : AppCompatActivity() {
    private var previouslySelected: ImageView? = null
    private var avatarSeleccionado: String = ""
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_PIN  = "extra_pin"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar)


        val usuario = intent.getStringExtra(EXTRA_USER) ?: ""
        val pin = intent.getStringExtra(EXTRA_PIN) ?: ""
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
                    this@AvatarActivity,
                    "Ingrese el nombre de usuario",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (pinValue.length < 4) {
                Toast.makeText(
                    this@AvatarActivity,
                    "El PIN debe tener 4 dígitos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // acceso a la BD
            val db = AppDatabase.getInstance(applicationContext)
            val usuarioDao = db.usuarioDao()

            // operación asíncrona
            lifecycleScope.launch {
                val alreadyExists = withContext(Dispatchers.IO) {
                    usuarioDao.countByName(nombre) > 0   // usa countByName en tu DAO
                }

                if (alreadyExists) {
                    Toast.makeText(
                        this@AvatarActivity,
                        "El nombre ya está en uso",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }

                // intentar insertar (segunda línea de defensa ante carreras)
                val insertedOk = withContext(Dispatchers.IO) {
                    try {
                        val u = Usuario(nombre = nombre, pin = pinValue, avatar = avatarName)
                        usuarioDao.insert(u) // DAO con OnConflictStrategy.ABORT idealmente
                        true
                    } catch (e: SQLiteConstraintException) {
                        false
                    } catch (e: Exception) {
                        // opcional: loguear e
                        false
                    }
                }

                if (insertedOk) {
                    Toast.makeText(this@AvatarActivity, "Registro exitoso", Toast.LENGTH_SHORT)
                        .show()

                    // solo si se registró, navegar y pasar extras
                    val intent = Intent(this@AvatarActivity, InicialActivity::class.java).apply {
                        putExtra(InicialActivity.EXTRA_USERX, nombre)
                        putExtra(InicialActivity.EXTRA_PINX, pinValue)
                        putExtra(InicialActivity.EXTRA_AVATARX, avatarName)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@AvatarActivity,
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