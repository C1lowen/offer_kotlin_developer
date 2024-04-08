package com.example.tztest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class ItemsAdapter(var items: List<Item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pulse: TextView = view.findViewById(R.id.item_list_pulse)
        val date: TextView = view.findViewById(R.id.item_list_date)
        val systolic: TextView = view.findViewById(R.id.item_list_systolic)
        val diastolic: TextView = view.findViewById(R.id.item_list_diastolic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dateFormat = String.format("%s, %s", items[position].date, items[position].time)
        holder.date.text = dateFormat
        holder.pulse.text = "Pulse: " + items[position].pulse.toString() + " BPM"
        holder.diastolic.text = items[position].diastolic.toString()
        holder.systolic.text = items[position].systolic.toString()
    }
}