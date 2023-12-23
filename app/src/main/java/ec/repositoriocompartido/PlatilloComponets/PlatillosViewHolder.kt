package ec.repositoriocompartido.PlatilloComponets

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.TextView
import ec.repositoriocompartido.androidmasterviu2.R


class PlatillosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imagenObj: ImageView = view.findViewById(R.id.imageView)

    private val context: Context = view.context

    fun render(plato: Platillo) {
        //qui consigo los detalles del plato y les agrego a cada cosita solo seria el titulo del plato nomas aqui
        val resourceId = context.resources.getIdentifier(plato.imagen, "drawable", "ec.repositoriocompartido.androidmasterviu2")
        imagenObj.setImageResource(resourceId)
        imagenObj.id =resourceId




    }
}