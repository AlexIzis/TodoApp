package com.example.todoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Класс адаптера для RecyclerView */
class TAdapter(private val onItemClick: ((Task) -> Unit)?): RecyclerView.Adapter<TAdapter.ViewHolder>() {

    private val todolist = mutableListOf<Task>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val edtText: TextView = itemView.findViewById(R.id.nameText)
        val dtText: TextView = itemView.findViewById(R.id.dateText)
        val tmText: TextView = itemView.findViewById(R.id.timeText)
        /** Вызов слушателя по нажатию на заметку */
        init {
                itemView.setOnClickListener{
                    onItemClick?.invoke(todolist[adapterPosition])
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TAdapter.ViewHolder, position: Int) {
        val tmpItem = todolist[position]
        holder.tmText.text = tmpItem.time
        holder.edtText.text = tmpItem.name
        holder.dtText.text = tmpItem.dateStart
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    /** Метод, с помощью которого происходит передача заметок для отображения */
    @SuppressLint("NotifyDataSetChanged")
    fun setInfo(tmp_arr: MutableList<Task>){
        todolist.clear()
        todolist.addAll(tmp_arr)
        notifyDataSetChanged()
    }
}