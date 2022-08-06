package com.example.todoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Класс адаптера для RecyclerView */
class TAdapter: RecyclerView.Adapter<TAdapter.ViewHolder>() {

    var todolist = ArrayList<Task>()
    var onItemClick: ((Task) -> Unit)? = null /** Инициализация слушателя нажатия на заметку */


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
        holder.tmText.text = todolist[position].time
        holder.edtText.text = todolist[position].name
        holder.dtText.text = todolist[position].date_start
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    /** Метод, с помощью которого происходит передача заметок для отображения */
    @SuppressLint("NotifyDataSetChanged")
    fun setInfo(tmp_arr: ArrayList<Task>){
        todolist.clear()
        for (task in tmp_arr){
            todolist.add(task)
        }
        notifyDataSetChanged()
    }
}