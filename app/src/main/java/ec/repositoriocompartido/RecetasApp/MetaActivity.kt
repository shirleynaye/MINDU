package ec.repositoriocompartido.RecetasApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import ec.repositoriocompartido.androidmasterviu2.R

class MetaActivity : AppCompatActivity() {

    private var HorasSueno:Int=7
    private var HorasSedentarias:Int=7
    private var HorasFisicas:Int=8

    private  var meta:Int=1
    //cardViews para seleciconar la meta
    private lateinit var cvReducir:CardView
    private lateinit var  cvMantener:CardView
    private lateinit var  cvAumentar:CardView

    private lateinit var txtHorasSueno:TextView
    private lateinit var rgnHorasSueno:RangeSlider

    private lateinit var txtHorasSedentarias:TextView
    private lateinit var txtHorasFisicas:TextView

    private  lateinit var btnSedentariasMenos:FloatingActionButton
    private  lateinit var btnSedentariasMas:FloatingActionButton
    private  lateinit var btnFisicasMenos:FloatingActionButton
    private  lateinit var btnFisicasMas:FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meta)

        val btnSiguiente= findViewById<Button>(R.id.btnSiguiente)
        val btnAnterior= findViewById<Button>(R.id.btnAnterior)
        val txtSubTituloMeta= findViewById<EditText>(R.id.txtSubTituloMeta)

        //Agregamos la informacion que pasaremos a la siguiente pantalla para ir recopilando la informacion de preferencias de alimentacion
        btnSiguiente.setOnClickListener {
            val intent= Intent(this, PreferenciasActivity::class.java)
            intent.putExtra("nombre",txtSubTituloMeta.text.toString())
            intent.putExtra("meta",meta)
            intent.putExtra("HorasSueno",HorasSueno)
            intent.putExtra("HorasSedentarias",HorasSedentarias)
            intent.putExtra("HorasFisicas",HorasFisicas)
            startActivity(intent)
        }
        btnAnterior.setOnClickListener {
            val intent= Intent(this,InicioAppActivity::class.java)
            startActivity(intent)
        }
        initComponets()
        initListeners()
    }


    private fun initComponets(){
        //Iniciamos el select con opciones default para que el usuario seleccione al menos un opcion valida


        cvReducir=findViewById(R.id.cvReducir)
        cvMantener=findViewById(R.id.cvMantener)
        cvAumentar=findViewById(R.id.cvAumentar)
        //Listeners de HorasSueno
        txtHorasSueno=findViewById(R.id.txtHorasSueno)
        txtHorasSueno.text= "${HorasSueno.toString()} hr"
        rgnHorasSueno=findViewById(R.id.sngHorasSueno)


        btnSedentariasMenos=findViewById(R.id.btnSedentariasMenos)
        btnSedentariasMas=findViewById(R.id.btnSedentariasMas)
        btnFisicasMenos=findViewById(R.id.btnFisicasMenos)
        btnFisicasMas=findViewById(R.id.btnFisicasMas)

        //listeners de horas de actividades sedentarias y fisicas
        txtHorasSedentarias=findViewById(R.id.txtSedentarias)
        txtHorasFisicas=findViewById(R.id.txtFisicas)
        txtHorasSedentarias.text= "${HorasSedentarias.toString()} Hr"
        txtHorasFisicas.text= "${HorasFisicas.toString()} Hr"

    }


    private fun  initListeners(){
        cvReducir.setOnClickListener{cambiarColorSexo(1)}
        cvMantener.setOnClickListener{cambiarColorSexo(2)}
        cvAumentar.setOnClickListener{cambiarColorSexo(3)}

        rgnHorasSueno.addOnChangeListener{slider,value,fromUser ->
            HorasSueno=value.toInt()
            txtHorasSueno.text="${HorasSueno.toString()} Hr"
        }
        btnSedentariasMenos.setOnClickListener{
            if (HorasSedentarias>1){
                HorasSedentarias-=1
                setPeso()
            }
        }
        btnSedentariasMas.setOnClickListener{
            if (HorasSedentarias<24){
                HorasSedentarias+=1
                setPeso()
            }
        }


        btnFisicasMenos.setOnClickListener{
            if (HorasFisicas>1){
                HorasFisicas-=1
                setEdad()
            }
        }
        btnFisicasMas.setOnClickListener{
            if (HorasFisicas<24){
                HorasFisicas+=1
                setEdad()
            }
        }



    }

    private fun setPeso(){
        txtHorasSedentarias.text="${HorasSedentarias.toString()} Hr"
    }
    private fun setEdad(){
        txtHorasFisicas.text="${HorasFisicas.toString()} Hr"
    }

    private fun  cambiarColorSexo(numero:Int){
        if ( numero==1 ){
            meta=1
            cvReducir.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component_selected))
            cvMantener.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))
            cvAumentar.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))

        }else if( numero==2 ){
            meta=2
            cvReducir.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))
            cvMantener.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component_selected))
            cvAumentar.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))

        }else if( numero==3 ){
            meta=3
            cvReducir.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))
            cvMantener.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component))
            cvAumentar.setCardBackgroundColor(ContextCompat.getColor(this,R.color.background_component_selected))

        }

    }




}