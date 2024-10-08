package com.example.app_tp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InversionAdapter(private val inversiones: List<Inversion>) : RecyclerView.Adapter<InversionAdapter.InversionViewHolder>() {

    class InversionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.text_view_nombre)
        val textViewMonto: TextView = itemView.findViewById(R.id.text_view_monto)
        val textViewTasa: TextView = itemView.findViewById(R.id.text_view_tasa)
        val textViewPlazo: TextView = itemView.findViewById(R.id.text_view_plazo)
        val textViewCapitalFinal: TextView = itemView.findViewById(R.id.text_view_capital_final)
        val textViewROI: TextView = itemView.findViewById(R.id.text_view_roi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InversionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inversion, parent, false)
        return InversionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InversionViewHolder, position: Int) {
        val inversion = inversiones[position]
        holder.textViewNombre.text = inversion.nombre
        holder.textViewMonto.text = "Monto: $${inversion.monto}"
        holder.textViewTasa.text = "Tasa: ${inversion.tasa}%"
        holder.textViewPlazo.text = "Plazo: ${inversion.plazo} meses"
        holder.textViewCapitalFinal.text = "Capital Final: $${inversion.capitalFinal}"
        holder.textViewROI.text = "ROI: ${"%.2f".format(inversion.ROI * 100)}%"
    }

    override fun getItemCount(): Int {
        return inversiones.size
    }
}