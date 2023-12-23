package ec.repositoriocompartido.IngredienteComponets

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R

class IngredientesViewHolder (view:View):RecyclerView.ViewHolder(view) {
    private val txtIngrediente: TextView =view.findViewById(R.id.txtCanal)
    val checkboxIngredientes: CheckBox = itemView.findViewById(R.id.cbCanal)

    fun render( ingrediente: Ingrediente){
        txtIngrediente.text=ingrediente.nombre

    }


}