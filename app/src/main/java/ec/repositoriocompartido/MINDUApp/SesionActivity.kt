package ec.repositoriocompartido.MINDUApp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ec.repositoriocompartido.androidmasterviu2.R
import android.util.Log
import androidx.core.content.getSystemService
import androidx.lifecycle.lifecycleScope
import ec.repositoriocompartido.androidmasterviu2.db.AppDatabase
import ec.repositoriocompartido.androidmasterviu2.db.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SesionActivity : AppCompatActivity() {

    // helper para mostrar Toast rápido
    private fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        // referencias UI
        val etUsuario = findViewById<EditText>(R.id.etUsuarioSesion)
        val pin1 = findViewById<EditText>(R.id.pin1)
        val pin2 = findViewById<EditText>(R.id.pin2)
        val pin3 = findViewById<EditText>(R.id.pin3)
        val pin4 = findViewById<EditText>(R.id.pin4)
        val btnIngresar = findViewById<Button>(R.id.btnEmpezarSesion)

        val pins = arrayOf(pin1, pin2, pin3, pin4)

        // asegurar maxLength=1 y comportamiento numérico desde código
        pins.forEach { et ->
            et.filters = arrayOf(InputFilter.LengthFilter(1))
            et.isCursorVisible = false
            // inputType ya está en XML, pero si quieres reforzar:
            // et.inputType = InputType.TYPE_CLASS_NUMBER
        }

        // función para ocultar teclado
        fun hideKeyboard() {
            val imm = getSystemService<InputMethodManager>()
            imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

        // función que arma el pin completo
        fun readPin(): String = pins.joinToString(separator = "") { it.text.toString() }

        // intento de login/continuar: valida y envía a AvatarActivity
        fun attemptLogin() {
            val usuarioStr = etUsuario.text.toString().trim()
            val pinStr = readPin().trim()

            if (usuarioStr.isEmpty()) {
                showToast("Ingrese el usuario")
                return
            }
            if (pinStr.length < 4) {
                showToast("Ingrese los 4 dígitos del PIN")
                return
            }

            // obtener DAO
            val db = AppDatabase.getInstance(applicationContext)
            val usuarioDao = db.usuarioDao()

            // consulta asíncrona
            lifecycleScope.launch {
                val user: Usuario? = withContext(Dispatchers.IO) {
                    try {
                        usuarioDao.login(usuarioStr, pinStr) // tu @Query("SELECT * FROM usuario WHERE nombre = :nombre AND pin = :pin LIMIT 1")
                    } catch (e: Exception) {
                        // log si quieres: Log.e("SesionActivity", "login error", e)
                        null
                    }
                }

                if (user != null) {
                    // login exitoso: user.avatar contiene el nombre del drawable o la cadena que guardaste
                    showToast("Bienvenido ${user.nombre}")

                    val intent = Intent(this@SesionActivity, InicialActivity::class.java).apply {
                        putExtra(InicialActivity.EXTRA_USERX, user.nombre)
                        putExtra(InicialActivity.EXTRA_PINX, user.pin)
                        putExtra(InicialActivity.EXTRA_AVATARX, user.avatar) // <- aquí va el avatar recuperado
                    }
                    startActivity(intent)
                    finish()
                } else {
                    showToast("Usuario o PIN incorrectos")
                }
            }
        }

        // TextWatcher y KeyListener para cada pin
        pins.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        // avanza foco
                        if (index < pins.size - 1) {
                            pins[index + 1].requestFocus()
                        } else {
                            // último dígito: esconder teclado
                            hideKeyboard()
                            // opcional: llamar a attemptLogin automáticamente
                            // attemptLogin()
                        }
                    }
                }
            })

            // manejar retroceso: si presionan DEL en una caja vacía, ir al anterior
            editText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                    if (editText.text.isEmpty() && index > 0) {
                        pins[index - 1].requestFocus()
                        pins[index - 1].setSelection(pins[index - 1].text.length)
                    }
                }
                false
            }
        }

        // click en boton: validar y enviar
        btnIngresar.setOnClickListener {
            attemptLogin()
            Log.d("SesionActivity", "Intent login: user='${etUsuario.text}' pin='${readPin()}'")
        }
    }
}
