package ec.repositoriocompartido.IngredienteComponets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R

class IngredientesAdapter  (private val ingredientes:List<Ingrediente>, private val itemClickListener: ItemClickListener?):RecyclerView.Adapter<IngredientesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_ingrediente,parent,false)
        return IngredientesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }

    override fun onBindViewHolder(holder: IngredientesViewHolder, position: Int) {

        val currentItem = ingredientes[position]
        holder.checkboxIngredientes.setOnClickListener {
            itemClickListener?.onItemClick(currentItem)
        }
        holder.render(currentItem)
    }
}