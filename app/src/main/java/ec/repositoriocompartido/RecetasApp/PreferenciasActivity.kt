package ec.repositoriocompartido.RecetasApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R
import ec.repositoriocompartido.IngredienteComponets.ApiService
import ec.repositoriocompartido.IngredienteComponets.IngredienteResponse
import ec.repositoriocompartido.IngredienteComponets.Ingrediente
import ec.repositoriocompartido.IngredienteComponets.IngredientesAdapter
import ec.repositoriocompartido.IngredienteComponets.ItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreferenciasActivity : AppCompatActivity() , ItemClickListener{





    val listaMutableChecks: MutableList<Int> = mutableListOf()
    private val ingredientesInit= mutableListOf<Ingrediente>()



    private lateinit var rvIngredientes:RecyclerView
    private lateinit var ingredientesAdapter: IngredientesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias)




        //recuperamos los datos que vienen como parametro de la calse anterior
        val nombre:String=intent.extras?.getString("nombre").orEmpty()
        val metaNum: Int? = intent?.extras?.getInt("meta")
        val HorasSueno: Int? = intent?.extras?.getInt("HorasSueno")
        val HorasSedentarias: Int? = intent?.extras?.getInt("HorasSedentarias")
        val HorasFisicas: Int? = intent?.extras?.getInt("HorasFisicas")




        val btnSiguiente= findViewById<Button>(R.id.btnSiguiente)
        val btnAnterior= findViewById<Button>(R.id.btnAnterior)

        //Volvemos a recolectar los datos para enviar a la pantalla final
        btnSiguiente.setOnClickListener {

            val intent= Intent(this, MenuPersonalizadoActivity::class.java)
            intent.putExtra("nombre",nombre)
            intent.putExtra("meta",metaNum)
            intent.putExtra("HorasSueno",HorasSueno)
            intent.putExtra("HorasSedentarias",HorasSedentarias)
            intent.putExtra("HorasFisicas",HorasFisicas)
            //En est parte se debe convertir la list amutable en este tipo para poder pasarla como parametro en intet
            val ingredientesList = ArrayList<Int>(listaMutableChecks)
           intent.putIntegerArrayListExtra("ingredientes", ingredientesList)


            startActivity(intent)


        }
        btnAnterior.setOnClickListener {
            val intent= Intent(this, MetaActivity::class.java)
            startActivity(intent)
        }


        initComponets()
        initUI()
        //Realizamos la peticion al API
        getCanales()

    }

    //INICIALIZACION COMPONENTES VISUALES
    private fun initComponets(){

        rvIngredientes=findViewById(R.id.rvCanales)
    }

    //INICIALIZACION RECYCLEVIEW
    private fun initUI(){


        ingredientesAdapter= IngredientesAdapter(ingredientesInit,this@PreferenciasActivity )
        rvIngredientes.layoutManager=LinearLayoutManager(this)
        rvIngredientes.adapter=ingredientesAdapter
    }

     fun getCanalesRetrofit():Retrofit{
         return Retrofit.Builder().baseUrl("http://192.168.0.110:3000/")
             .addConverterFactory(GsonConverterFactory.create()).build()

    }
    fun getCanales(){
        CoroutineScope(Dispatchers.IO).launch {
            val peticion=getCanalesRetrofit().create(ApiService::class.java).getCanalesYoutube("data")
            val canalesResponse: IngredienteResponse?=peticion.body()
            runOnUiThread{
                if (peticion.isSuccessful){
                    Log.i("canalesyoutube","exito")
                    val canalesData=canalesResponse?.ingredientes?:emptyList()
                    ingredientesInit.clear()
                    ingredientesInit.addAll(canalesData)
                    ingredientesAdapter.notifyDataSetChanged()
                }else{
                    Log.i("canalesyoutube","error")
                }

            }
        }
    }
    override fun onItemClick(item: Ingrediente) {
        mostrarDialogo(item.id)
    }
    private fun mostrarDialogo(id:Int) {
        var mensaje:String=""
        if (eliminarNumero(id)){
            mensaje="Ingrediente removido de la lista de exclusion"
        }else {
            mensaje="Ingrediente agregado a la lista de exclusion"

        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Accion:")
        builder.setMessage(mensaje)

        builder.setNegativeButton("Cerrar") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    //Metodo para verificar si existe el id del checkbox
    //Para quitarlo o agregalo a la lista de alimentos excluidos
    fun eliminarNumero( numero: Int): Boolean {
        return if (listaMutableChecks.contains(numero)) {
            listaMutableChecks.remove(numero)
            true
        } else {
            listaMutableChecks.add(numero)
            false
        }
    }

}