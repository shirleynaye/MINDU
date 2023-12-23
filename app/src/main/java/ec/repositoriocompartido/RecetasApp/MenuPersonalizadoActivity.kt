package ec.repositoriocompartido.RecetasApp

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.PlatilloComponets.Platillo
import ec.repositoriocompartido.PlatilloComponets.PlatilloClickListener
import ec.repositoriocompartido.PlatilloComponets.PlatillosAdapter
import ec.repositoriocompartido.androidmasterviu2.R

class MenuPersonalizadoActivity : AppCompatActivity() , PlatilloClickListener {

    private var mediaPlayer: MediaPlayer? = null

    private lateinit var PlatillosList: List<Platillo>
    private var textoFinalMenu:String=""
    private var cantidadComida:Int=800




    //Declaracion de variables
    lateinit var nombreEnviado: String
    lateinit var titulo: TextView



    //Recupera todos los demas datos enviados
     var metaNum: Int=0
     var HorasSueno: Int=0
    var HorasSedentarias: Int=0
     var HorasFisicas: Int=0

    lateinit var ingredientesList: ArrayList<Int>


    //Aplicar restricciones
    lateinit var mensajeNombre: String
    //control de acuerdo a la meta que quiere lograr el usuario
    lateinit var mensajeMeta: String

    lateinit var ingredientesFuera:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_personalizado)

        //inicializamos l alista y componentes y verificaciones
        initComponets()





    }

    private fun initComponets() {
        //inicializamos la lista
        PlatillosList = listOf(

            Platillo(
                "op1", 1, "Ensalada de Quinoa con Frambuesas", "Quinoa cocida\n" +
                        "Frambuesas\n" +
                        "Espárragos\n" +
                        "Tomate cherry\n" +
                        "Aguacate"
            ),
            Platillo(
                "op2", 2, "Sopa de Zanahoria y Jengibre", "Zanahorias\n" +
                        "Jengibre fresco\n" +
                        "Caldo de verduras\n" +
                        "Cilantro\n" +
                        "Leche de coco"
            ),
            Platillo(
                "op3", 3, "Tacos de Pescado con Mango", "Filetes de pescado\n" +
                        "Tortillas de maíz\n" +
                        "Mango\n" +
                        "Col morada\n" +
                        "Aguacate"
            ),
            Platillo(
                "op4", 4, "Bowl de Pollo con Berenjena Asada", "Pechuga de pollo\n" +
                        "Quinoa\n" +
                        "Berenjena\n" +
                        "Tomate\n" +
                        "Garbanzos"
            ),
            Platillo(
                "op5", 5, "Rollitos de Lechuga con Tofu y Granada", "Hojas de lechuga\n" +
                        "Tofu\n" +
                        "Zanahorias ralladas\n" +
                        "Granos de granada\n" +
                        "Menta fresca"
            ),
            Platillo(
                "op6", 6, "Ceviche de Mango y Camarones", "Camarones cocidos\n" +
                        "Mango\n" +
                        "Limón\n" +
                        "Cebolla roja\n" +
                        "Cilantro fresco"
            ),
            Platillo(
                "op7", 7, "Hamburguesa de Pavo con Arándanos", "Carne molida de pavo\n" +
                        "Pan integral\n" +
                        "Arándanos frescos\n" +
                        "Queso de cabra\n" +
                        "Espinacas"
            ),
            Platillo(
                "op8", 8, "Pasta Integral con Pesto de Rúcula", "Pasta integral\n" +
                        "Rúcula\n" +
                        "Piñones\n" +
                        "Queso parmesano\n" +
                        "Ajo"
            ),
            Platillo(
                "op9", 9, "Ensalada de Quinoa y Papaya", "Quinoa cocida\n" +
                        "Papaya\n" +
                        "Pepino\n" +
                        "Pimientos de colores\n" +
                        "Albahaca fresca"
            ),
            Platillo(
                "op10", 10, "Salteado de Tofu con Naranja", "Tofu\n" +
                        "Brócoli\n" +
                        "Zanahorias\n" +
                        "Naranjas\n" +
                        "Almendras"
            ),
            Platillo(
                "op11", 11, "Ensalada de Garbanzos y Pomelo", "Garbanzos cocidos\n" +
                        "Pomelo\n" +
                        "Aguacate\n" +
                        "Pepino\n" +
                        "Cilantro fresco"
            ),
            Platillo(
                "op12", 12, "Bowl de Burrito con Alcachofas", "Arroz integral\n" +
                        "Frijoles negros\n" +
                        "Maíz\n" +
                        "Aguacate\n" +
                        "Alcachofas"
            ),
            Platillo(
                "op13",
                13,
                "Wraps de Pollo con Pesto de Espárragos",
                "Pechuga de pollo a la parrilla\n" +
                        "Tortillas integrales\n" +
                        "Pesto de espárragos\n" +
                        "Lechuga romana\n" +
                        "Tomate"
            ),
            Platillo(
                "op14", 14, "Cazuela de Quinoa y Champiñone", "Quinoa cocida\n" +
                        "Champiñones\n" +
                        "Espinacas\n" +
                        "Tomate\n" +
                        "Queso feta"
            ),
            Platillo(
                "op15", 15, "Tortitas de Avena y Higos", "Avena\n" +
                        "Plátano maduro\n" +
                        "Clara de huevo\n" +
                        "Canela\n" +
                        "Higos frescos"
            ),
            Platillo(
                "op16",
                16,
                "Sopa de Tomate con Albóndigas de Pavo y Calabaza",
                "Albóndigas de pavo\n" +
                        "Tomates pelados\n" +
                        "Calabaza\n" +
                        "Albahaca fresca\n" +
                        "Queso parmesano rallado"
            )
        )





        //METODOS PARA REGRESAR AL MENU PRINCIPAL
        val regreso = findViewById<ImageView>(R.id.regresar)
        regreso.setOnClickListener {
            val intent = Intent(this, InicioAppActivity::class.java)
            startActivity(intent)
        }


        //Rellena el titulo con el nombre del usuario
         nombreEnviado= intent.extras?.getString("nombre").orEmpty()
        titulo = findViewById<TextView>(R.id.titulo)



        //Recupera todos los demas datos enviados

         metaNum = intent.extras?.getInt("meta")?: 0
         HorasSueno = intent.extras?.getInt("HorasSueno")?: 0
         HorasSedentarias = intent.extras?.getInt("HorasSedentarias")?: 0
         HorasFisicas = intent.extras?.getInt("HorasFisicas")?: 0
        val ingredientesList: ArrayList<Int> = intent.getIntegerArrayListExtra("ingredientes")?.filterNotNull()?.toCollection(ArrayList()) ?: arrayListOf()



        //Aplicar restricciones
         mensajeNombre = nombreEnviado + ", a continuacion se muestra su menu personalizado de acuerdo a sus preferencias:\n\n "
        textoFinalMenu += "\n$mensajeNombre\n"
        //control de acuerdo a la meta que quiere lograr el usuario
         mensajeMeta = " "
        if (metaNum==1){
            cantidadComida-=100
            mensajeMeta= " Se redujo 100 gramos a su comida de acuerdo a su meta bajar de peso"
        }else if(metaNum==2){
            mensajeMeta= " Se mantuvo la cantidad de gramos a su comida de cuerdo a su meta mantene rel peso"
        }else{
            cantidadComida-=100
            mensajeMeta= " Se agregaron 100 gramos a su comida de acuerdo a su meta aumentar de masa corporal"
        }
        textoFinalMenu += "\n$mensajeMeta\n"

        //CONTROL DE ACUERDO A LAS HORAS DE SUENO
        HorasSueno?.let { horas ->
            cantidadComida -= horas * 10
            textoFinalMenu += "\nSe le restaron ${horas * 10} gramos, de acuerdo a sus horas de sueño.\n"
        }


        //CONTROL DE ACUERDO A LAS HORAS DE ACTIVIDADES SEDENTARIAS
        HorasSedentarias?.let { horas ->
            cantidadComida -= horas * 13
            textoFinalMenu += "\nSe le restaron ${horas * 13} gramos, de acuerdo a sus horas de actividades sedentarias.\n"
        }

        //CONTROL DE ACUERDO A LAS HORAS DE ACTIVIDADES FISICAS
        HorasFisicas?.let { horas ->
            cantidadComida += horas * 11
            textoFinalMenu += "\nSe le agregaron ${horas * 11} gramos, de acuerdo a sus horas de actividades fisicas.\n"
        }
        //CONTROL DE ACUEDO A INGREDIENTES A LOS QUE TIENE ALERGIAS
       ingredientesFuera=""

        val nuevaPlatillosList = PlatillosList.filter { platillo ->
            val platilloNoContieneIngrediente = ingredientesList?.contains(platillo.ingredienteID) != true

            if (!platilloNoContieneIngrediente) {
                ingredientesFuera+="\nEl platillo: ${platillo.nombre}, con el ingrediente ID: ${platillo.ingredienteID} fue removido"
            }

            platilloNoContieneIngrediente
        }

        PlatillosList=nuevaPlatillosList

        textoFinalMenu +=ingredientesFuera
        titulo.text = "Menu personalizado " + textoFinalMenu


        //Cargar toda la lista de platillos que hayan pasado las restricciones
        val rvOpciones = findViewById<RecyclerView>(R.id.rvOpciones)
        val categoriasAdapter = PlatillosAdapter(PlatillosList, this@MenuPersonalizadoActivity)
        val gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
        rvOpciones.layoutManager = gridLayoutManager
        rvOpciones.adapter = categoriasAdapter

    }

    override fun onPlatilloClick(platillo: Platillo) {

        mostrarPopup(this, platillo)
    }

    private fun mostrarPopup(context: Context, platillo: Platillo) {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(R.layout.popuop_layoutt, null)

        val popupImageView: ImageView = popupView.findViewById(R.id.popupImageView)

        val popupSeekBar: SeekBar = popupView.findViewById(R.id.seekBar)

        // Obtiene la ruta de la imagen y el sonido desde el objeto Platillo
        val rutaImagen = platillo.imagen
        val rutaSonido = platillo.imagen

        // Carga la imagen en el ImageView
        cargarImagenDesdeRuta(context, rutaImagen, popupImageView)

        // Configura el SeekBar para el sonido
        configurarSeekBar(context, rutaSonido, popupSeekBar)

        // Construye y muestra el cuadro de diálogo
        val alertDialogBuilder = AlertDialog.Builder(context)
            .setView(popupView)
            .setTitle(platillo.nombre)
            .setMessage(platillo.descripcion)
            .setPositiveButton("Aceptar") { dialog, _ ->
                detenerReproduccion()
                dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun cargarImagenDesdeRuta(context: Context, rutaImagen: String, imageView: ImageView) {
        val resourceId = context.resources.getIdentifier(rutaImagen, "drawable", context.packageName)
        imageView.setImageResource(resourceId)
    }


    private fun configurarSeekBar(context: Context, rutaSonido: String, seekBar: SeekBar) {
        val idSonido = obtenerIdSonido(context, rutaSonido)
        val duracion = obtenerDuracionSonido(context, idSonido)

        seekBar.max = duracion

        mediaPlayer = MediaPlayer.create(context, idSonido)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        mediaPlayer?.start()

        // Inicializa el hilo para actualizar la posición del SeekBar
        actualizarSeekBar(seekBar)
    }



    private fun obtenerIdSonido(context: Context, rutaSonido: String): Int {
        return context.resources.getIdentifier(rutaSonido, "raw", context.packageName)
    }

    private fun obtenerDuracionSonido(context: Context, idSonido: Int): Int {
        val mediaPlayer = MediaPlayer.create(context, idSonido)
        val duracion = mediaPlayer?.duration ?: 0
        mediaPlayer?.release()
        return duracion
    }

    private fun detenerReproduccion() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun actualizarSeekBar(seekBar: SeekBar) {
        val handler = Handler()

        // Este Runnable se ejecutará cada 100 milisegundos
        val runnable = object : Runnable {
            override fun run() {
                // Verifica si el MediaPlayer está inicializado y está reproduciendo
                if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                    val currentPosition = mediaPlayer!!.currentPosition
                    seekBar.progress = currentPosition
                    handler.postDelayed(this, 100)  // Programa la próxima actualización
                } else {
                    handler.removeCallbacks(this)  // Detiene la actualización cuando la reproducción se detiene
                }
            }
        }
        handler.post(runnable)  // Inicia el proceso de actualización
    }

}



