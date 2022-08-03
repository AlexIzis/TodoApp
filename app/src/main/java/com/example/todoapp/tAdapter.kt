package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class tAdapter: RecyclerView.Adapter<tAdapter.ViewHolder>() {

    var Todolist = ArrayList<Task>()
    var onItemClick: ((Task) -> Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val edt_text = itemView.findViewById<TextView>(R.id.nameText)
        val dt_text = itemView.findViewById<TextView>(R.id.dateText)
        val tm_text = itemView.findViewById<TextView>(R.id.timeText)
        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(Todolist[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: tAdapter.ViewHolder, position: Int) {
        holder.tm_text.text = Todolist[position].time
        holder.edt_text.text = Todolist[position].name
        holder.dt_text.text = Todolist[position].date_start
    }

    override fun getItemCount(): Int {
        return Todolist.size
    }

    fun setInfo(tmp_arr: ArrayList<Task>){
        Todolist.clear()
        for (task in tmp_arr){
            Todolist.add(task)
        }
        notifyDataSetChanged()
    }
}