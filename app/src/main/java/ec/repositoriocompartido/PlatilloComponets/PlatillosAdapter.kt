package ec.repositoriocompartido.PlatilloComponets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R

class PlatillosAdapter  (private val Itemes:List<Platillo>, private val itemClickListener: PlatilloClickListener?):RecyclerView.Adapter<PlatillosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatillosViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_platillo,parent,false)
        return PlatillosViewHolder(view)

    }

    override fun getItemCount(): Int {
        return Itemes.size
    }

    override fun onBindViewHolder(holder: PlatillosViewHolder, position: Int) {


        val currentItem = Itemes[position]

        holder.itemView.setOnClickListener {
            itemClickListener?.onPlatilloClick(currentItem)
        }

        holder.render(currentItem)

    }




}