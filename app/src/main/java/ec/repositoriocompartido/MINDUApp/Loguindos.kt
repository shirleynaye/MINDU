package ec.repositoriocompartido.MINDUApp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R

class Loguindos : AppCompatActivity() {


    private val expectedPin = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loguindos)



        val usuarioViejo = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""




        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayoutx = findViewById<ConstraintLayout>(R.id.rootLoguindos)
        rootLayoutx.setBackgroundColor(Color.parseColor(color))




        val btnVolverx=findViewById<TextView>(R.id.tvVolver)
        btnVolverx.setOnClickListener {

            val intent = Intent(this@Loguindos, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuarioViejo)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }






        fun Context.showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        // Los 4 EditText que definiste en el layout para el PIN
        val pin1 = findViewById<EditText>(R.id.pin1)
        val pin2 = findViewById<EditText>(R.id.pin2)
        val pin3 = findViewById<EditText>(R.id.pin3)
        val pin4 = findViewById<EditText>(R.id.pin4)

        val pins = arrayOf(pin1, pin2, pin3, pin4)


        val btnRegistrar = findViewById<Button>(R.id.btnRegistro)

        // Configuración común para cada caja del PIN
        pins.forEach { et ->
            // 1 carácter máximo
            et.filters = arrayOf(InputFilter.LengthFilter(1))
            // Solo números
            et.inputType = InputType.TYPE_CLASS_NUMBER
            // Centrar texto (opcional si ya lo pusiste en XML)
            et.isCursorVisible = false
        }

        // Función para ocultar teclado
        fun hideKeyboard() {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }


        pins.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        // Avanzar foco si no es el último
                        if (index < pins.size - 1) {
                            pins[index + 1].requestFocus()
                        } else {
                            // último dígito -> ocultar teclado y opcionalmente procesar automáticamente
                            hideKeyboard()
                            // puedes validar automáticamente llamando a onPinComplete aquí si quieres
                        }
                    }
                }
            })

            // Si presionan backspace en una caja vacía, ir al anterior
            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                    if (editText.text.isEmpty() && index > 0) {
                        pins[index - 1].requestFocus()
                        pins[index - 1].setSelection(pins[index - 1].text.length)
                    }
                }
                false
            }
        }

        // Acción del botón: toma usuario + PIN y valida
        btnRegistrar.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val pin = pins.joinToString(separator = "") { it.text.toString() }

            // Validaciones sencillas
            if (usuario.isEmpty()) {
                showToast("Ingrese el usuario")
                return@setOnClickListener
            }
            if (pin.length < 4) {
                showToast("Ingrese los 4 dígitos del PIN")
                return@setOnClickListener
            }
            val usuarioViejoc = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
            val intent = Intent(this, AvatarActivitydos::class.java).apply {
                putExtra(AvatarActivitydos.EXTRA_USER, usuario)
                putExtra(AvatarActivitydos.EXTRA_PIN, pin)
                putExtra(AvatarActivitydos.EXTRA_USERVIEJO, usuarioViejoc)
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
