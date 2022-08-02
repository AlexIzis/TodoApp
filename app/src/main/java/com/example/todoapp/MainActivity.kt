package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar: CalendarView = findViewById(R.id.calendarView)
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val todoText: TextView = findViewById(R.id.textView2)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = tAdapter()
        val FullTodoList = ArrayList<Task>()
        val TodoListToAdapter = ArrayList<Task>()

        FullTodoList.add(Task(1,"1.7.2022", "1.7.2022", "one", "hello"))
        FullTodoList.add(Task(2,"2.7.2022", "2.7.2022", "two", "hello"))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.onItemClick = { Task ->
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("id", Task.id)
            intent.putExtra("name", Task.name)
            intent.putExtra("start", Task.date_start)
            intent.putExtra("finish", Task.date_finish)
            intent.putExtra("desc", Task.description)
            startActivity(intent)
        }

        calendar.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val curDate = "$dayOfMonth.$month.$year"
            TodoListToAdapter.clear()
            for (task in FullTodoList){
                if (task.date_start == curDate){
                    TodoListToAdapter.add(task)
                }
            }
            adapter.setInfo(TodoListToAdapter)
        }
//        fab.setOnClickListener{
//
//        }
    }
}