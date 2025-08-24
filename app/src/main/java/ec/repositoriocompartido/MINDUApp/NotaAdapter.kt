import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.repositoriocompartido.androidmasterviu2.R
import ec.repositoriocompartido.androidmasterviu2.db.Nota

class NotaAdapter(private val notas: List<Nota>) :
    RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNota: TextView = itemView.findViewById(R.id.tvNota) // tu TextView en item_nota.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.tvNota.text = nota.detalle  // mostramos solo el detalle
    }

    override fun getItemCount(): Int = notas.size
}
