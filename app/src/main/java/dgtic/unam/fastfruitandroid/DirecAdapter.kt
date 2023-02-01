package dgtic.unam.fastfruitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DirecAdapter(private val direcList: ArrayList<Direc>): RecyclerView.Adapter<DirecAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.direc_row_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = direcList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDirecNueva.text = direcList[position].direc
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDirecNueva: TextView = itemView.findViewById(R.id.tvDirecNueva)
    }
}