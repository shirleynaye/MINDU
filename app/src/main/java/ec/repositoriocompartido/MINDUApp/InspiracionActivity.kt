package ec.repositoriocompartido.MINDUApp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import ec.repositoriocompartido.androidmasterviu2.R
import kotlin.random.Random

class InspiracionActivity : AppCompatActivity() {
    // guarda el último índice mostrado por recurso (key = id de la ImageView)
// usa Int? para permitir "sin valor aún"
    private val lastShownIndex = mutableMapOf<Int, Int?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspiracion)

        val imgs = listOf(
            findViewById<ImageView>(R.id.item1_img),
            findViewById<ImageView>(R.id.item2_img),
            findViewById<ImageView>(R.id.item3_img),
            findViewById<ImageView>(R.id.item4_img),
            findViewById<ImageView>(R.id.item5_img)
        )


        val color = intent.getStringExtra(InicialActivity.EXTRA_COLOR) ?: "#E3F2FD"
        val rootLayout = findViewById<ConstraintLayout>(R.id.rootInspira)
        rootLayout.setBackgroundColor(Color.parseColor(color))








        // Para cada imagen una lista de frases (ejemplo: 3 aquí, reemplaza por tus 10)
        val frasesPorImagen: Map<Int, List<String>> = mapOf(
            R.id.item1_img to listOf(
                "“La felicidad que sientes hoy no es solo un estado pasajero: estudios de\n" +
                        "psicología positiva muestran que revivir conscientemente los momentos alegres\n" +
                        "refuerza tu memoria emocional y fortalece la resiliencia para los días difíciles",
                "Compartir tu alegría con otros no solo multiplica la emoción, también fortalece\n" +
                        "tus vínculos sociales, un factor que la ciencia reconoce como el predictor más\n" +
                        "fuerte de bienestar a largo plazo",
                "Cuando sonríes, aunque sea de manera intencional, tu cerebro libera\n" +
                        "endorfinas y serotonina, lo que genera una retroalimentación positiva que\n" +
                        "aumenta tu sensación de calma y satisfacción",
                "Celebrar tus pequeños logros es fundamental: este acto activa la dopamina, el\n" +
                        "neurotransmisor de la motivación, ayudándote a mantener la energía para\n" +
                        "alcanzar nuevas metas.",
                "La felicidad no se encuentra únicamente en grandes momentos, sino en lo\n" +
                        "cotidiano; la psicología demuestra que disfrutar conscientemente de lo simple\n" +
                        "intensifica la experiencia emocional.",
                "Cuando recuerdas algo positivo, tu cerebro activa las mismas áreas que\n" +
                        "cuando lo viviste, lo que significa que revivir buenos recuerdos es también una\n" +
                        "forma de aumentar tu bienestar.",
                "La música alegre estimula los circuitos cerebrales asociados a la motivación,\n" +
                        "elevando el estado de ánimo de forma natural y ayudándote a enfrentar con\n" +
                        "más energía tu día.",
                "La gratitud, practicada de forma diaria, incrementa la satisfacción con la vida y\n" +
                        "protege contra estados emocionales negativos, siendo una de las estrategias\n" +
                        "más poderosas de la psicología positiva.",
                "El humor y la risa son mecanismos naturales para reducir el estrés; además,\n" +
                        "mejoran tu capacidad de afrontamiento y fortalecen las conexiones sociales.",
                "La felicidad no es ausencia de problemas, sino la capacidad de apreciarlos\n" +
                        "desde una perspectiva más amplia y positiva, lo que la ciencia llama\n" +
                        "crecimiento postraumático."
                // ... hasta 10 frases
            ),
            R.id.item2_img to listOf(
                "La tristeza es una emoción válida y adaptativa; llorar activa mecanismos de liberación de oxitocina y endorfinas que funcionan como un bálsamo natural para calmar el dolor.",
                "Escribir cómo te sientes en un diario reduce la intensidad de la tristeza porque activa áreas racionales del cerebro, transformando la emoción en un proceso consciente y regulado.",
                "Aceptar la tristeza en lugar de evitarla es clave: la psicología indica que la represión emocional intensifica el malestar, mientras que la aceptación permite que la emoción fluya y se transforme.",
                "Buscar apoyo en otros no es signo de debilidad; el contacto humano activa el sistema de recompensa social en el cerebro y libera oxitocina, disminuyendo la sensación de soledad.",
                "La tristeza te invita a detenerte y reflexionar; vista desde la psicología evolutiva, esta emoción cumple la función de motivarte a analizar y aprender de tus experiencias.",
                "Escuchar música melancólica no aumenta tu tristeza, al contrario: permite procesarla y expresarla de forma más saludable.",
                "Compartir lo que sientes con alguien de confianza no solo reduce la carga emocional, también refuerza los lazos sociales y la sensación de acompañamiento.",
                "La autocompasión frente a la tristeza disminuye la rumiación mental y favorece un estado de aceptación más sano y equilibrado.",
                "Aunque la tristeza duele, también es temporal; recordarlo ayuda a mantener una visión más amplia y realista sobre tu estado emocional.",
                "Cada emoción negativa cumple una función: la tristeza, en particular, te recuerda la importancia de cuidarte, priorizar tu bienestar y buscar nuevas formas de crecimiento."
            ),
            R.id.item3_img to listOf(
                "El enojo es una señal de que algo te importa, pero sostenerlo demasiado tiempo puede dañar tu salud; respirar profundamente durante al menos un minuto activa tu sistema nervioso parasimpático y reduce la intensidad emocional.",
                "Observar tu enojo sin reaccionar de inmediato es una habilidad que la práctica del mindfulness desarrolla y que te permite responder de forma más consciente y menos impulsiva.",
                "El enojo reprimido se acumula, pero expresarlo de forma asertiva disminuye conflictos y fortalece la comunicación, transformando la energía en soluciones constructivas.",
                "El ejercicio físico es uno de los mejores reguladores emocionales: ayuda a liberar la tensión acumulada en tu cuerpo cuando la ira se hace presente.",
                "La ira sostenida incrementa la presión arterial y puede afectar tu corazón; aprender a reconocerla y regularla es también un acto de cuidado hacia tu salud.",
                "Nombrar lo que sientes —‘estoy enojado’— te ayuda a disminuir la intensidad de la emoción, porque activa procesos racionales que equilibran la respuesta automática.",
                "Muchas veces, detrás del enojo se esconden otras emociones como el miedo o la tristeza; reconocerlas es la clave para entender realmente lo que sientes.",
                "Escribir lo que te molesta antes de enfrentarlo con alguien ayuda a organizar ideas y a disminuir la carga emocional que acompaña a la ira.",
                "Reconocer las señales físicas de tu enojo —latidos acelerados, tensión muscular, calor— te permite intervenir antes de perder el control.",
                "Transformar el enojo en acción productiva es posible: usar esa energía para resolver un problema fortalece tu autoestima y te da sensación de control."
            ),
            R.id.item4_img to listOf(
                "El estrés no proviene solo de lo que sucede, sino de cómo lo interpretas; al practicar mindfulness entrenas a tu mente a observar sin exagerar amenazas.",
                "Respirar profundamente varias veces activa el nervio vago, regulando el ritmo cardíaco y produciendo una respuesta fisiológica de calma.",
                "Dormir bien no es un lujo, es una necesidad: la falta de descanso aumenta el cortisol y disminuye tu capacidad para manejar el estrés.",
                "Hacer pausas breves durante el día mejora la concentración y reduce la fatiga mental, funcionando como una recarga psicológica.",
                "El contacto con la naturaleza ha demostrado reducir significativamente la ansiedad y la presión arterial, devolviendo calma al sistema nervioso.",
                "La música relajante activa ondas cerebrales alfa, asociadas con la relajación profunda y la reducción del estrés.",
                "Practicar gratitud no solo mejora el bienestar, también reduce la percepción de sobrecarga emocional en momentos difíciles.",
                "El ejercicio regular contrarresta los efectos fisiológicos del estrés, equilibrando el sistema endocrino y mejorando tu estado de ánimo.",
                "Escribir tus preocupaciones en papel libera espacio mental y te ayuda a ver soluciones que antes estaban bloqueadas por la tensión.",
                "Aceptar que no puedes controlarlo todo libera energía para enfocarte en lo que sí está bajo tu influencia."
            ),
            R.id.item5_img to listOf(
                "La ansiedad suele proyectarte al futuro; regresar al presente con tu respiración calma la hiperactividad de la amígdala, el área cerebral del miedo.",
                "Nombrar tu ansiedad en voz alta o escribirla en un diario reduce su intensidad, porque convierte lo difuso en algo concreto y manejable.",
                "La práctica constante de meditación fortalece la corteza prefrontal, que regula las emociones y disminuye la respuesta ansiosa.",
                "El ejercicio físico libera endorfinas, neurotransmisores que contrarrestan la tensión y equilibran el sistema nervioso.",
                "Dormir lo suficiente regula neurotransmisores clave como la serotonina y el GABA, que están directamente relacionados con la calma.",
                "Aceptar que la incertidumbre es parte de la vida es una de las claves psicológicas más efectivas para disminuir pensamientos anticipatorios.",
                "La gratitud redirige tu atención hacia lo positivo, interrumpiendo el ciclo de preocupación y reduciendo la ansiedad.",
                "La terapia cognitivo-conductual demuestra que cuestionar pensamientos ansiosos disminuye su impacto emocional.",
                "Observar tu cuerpo —latidos, respiración, tensión— con atención plena ayuda a reducir la ansiedad, pues cambia tu foco del futuro al presente.",
                "Estudios confirman que con solo 10 minutos diarios de mindfulness se reducen significativamente los síntomas de ansiedad y se mejora la concentración."
            )
        )

        imgs.forEach { imgView ->
            imgView.setOnClickListener { view ->
                val id = view.id
                val lista = frasesPorImagen[id]
                val texto = if (lista == null || lista.isEmpty()) {
                    "Reflexión no disponible."
                } else {
                    // seleccionar índice aleatorio, evitando repetir el último si hay >1 elementos
                    val last = lastShownIndex[id]
                    val selectedIndex = if (lista.size == 1) {
                        0
                    } else {
                        // intenta hasta 5 veces para obtener diferente; si no, acepta el que salga
                        var idx: Int
                        var attempts = 0
                        do {
                            idx = Random.nextInt(lista.size)
                            attempts++
                        } while (idx == last && attempts < 5)
                        idx
                    }
                    lastShownIndex[id] = selectedIndex
                    lista[selectedIndex]
                }

                // muestra en popup (usa tu función existente)
                showParagraphPopup(getTitleForId(id), texto)
            }
        }




        val usuario = intent.getStringExtra(InicialActivity.EXTRA_USERX) ?: ""
        val pin = intent.getStringExtra(InicialActivity.EXTRA_PINX) ?: ""
        val avatar = intent.getStringExtra(InicialActivity.EXTRA_AVATARX) ?: ""


        val imgTopRight = findViewById<ImageView>(R.id.imgTopRight)
        val resId = resources.getIdentifier(avatar, "drawable", packageName)
        if (resId != 0) {
            imgTopRight.setImageResource(resId)
        }


        val btnVolver=findViewById<ImageView>(R.id.imgTopLeft)
        btnVolver.setOnClickListener {
            val intent = Intent(this@InspiracionActivity, InicialActivity::class.java).apply {
                putExtra(InicialActivity.EXTRA_USERX, usuario)
                putExtra(InicialActivity.EXTRA_PINX, pin)
                putExtra(InicialActivity.EXTRA_AVATARX, avatar)
                putExtra(ConfigActivity.EXTRA_COLOR, color)
            }
            startActivity(intent)
            finish()
        }

    }
    // helper para retornar un título según el id (opcional)
    private fun getTitleForId(resId: Int): String {
        return when (resId) {
            R.id.item1_img -> "Animado"
            R.id.item2_img -> "Triste"
            R.id.item3_img -> "Enojado"
            R.id.item4_img -> "Estresado"
            R.id.item5_img -> "Ansioso"
            else -> "Reflexión"
        }
    }

    // función que muestra el diálogo con layout personalizado
    private fun showParagraphPopup(title: String, paragraph: String) {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_paragraph, null)

        val tvTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val tvParagraph = dialogView.findViewById<TextView>(R.id.dialogParagraph)
        val btnClose = dialogView.findViewById<ImageButton>(R.id.btnClose)

        tvTitle.text = title
        tvParagraph.text = paragraph

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // cerrar al presionar la X
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // opcional: cerrar al tocar fuera (true/false)
        dialog.setCanceledOnTouchOutside(true)

        dialog.show()
    }
    companion object {
        const val EXTRA_USERX= "extra_user"
        const val EXTRA_PINX = "extra_pin"
        const val EXTRA_AVATARX = "extra_avatar"
        const val EXTRA_COLOR = "extra_color"}
}