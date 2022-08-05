package com.example.todoapp

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar: CalendarView = findViewById(R.id.calendarView)
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = tAdapter()
        val fullTodoList = ArrayList<Task>()
        val todoListToAdapter = ArrayList<Task>()

        fullTodoList.add(Task(1,"1.7.2022", "1.7.2022", "14:00", "one", "hello"))
        fullTodoList.add(Task(2,"2.7.2022", "2.7.2022", "15:00", "two", "hello"))
        fullTodoList.add(Task(3,"2.7.2022", "2.7.2022", "14:00", "three", "hello"))
        fullTodoList.add(Task(4,"1.7.2022", "1.7.2022", "12:31", "four", "hello"))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.onItemClick = { Task ->
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("id", Task.id)
            intent.putExtra("name", Task.name)
            intent.putExtra("start", Task.date_start)
            intent.putExtra("finish", Task.date_finish)
            intent.putExtra("time", Task.time)
            intent.putExtra("desc", Task.description)
            startActivity(intent)
        }

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                val id = fullTodoList.size + 1
                val name = it.data?.getStringExtra("crName").toString()
                val dateStart = it.data?.getStringExtra("crDate").toString()
                val dateFinish = it.data?.getStringExtra("crDate").toString()
                val time = it.data?.getStringExtra("crTime").toString()
                val desc = it.data?.getStringExtra("crDesc").toString()
                fullTodoList.add(Task(id, dateStart,dateFinish, time, name, desc))
            }
        }

        fab.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            getResult.launch(intent)
        }


        calendar.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val curDate = "$dayOfMonth.$month.$year"
            todoListToAdapter.clear()
            for (task in fullTodoList){
                if (task.date_start == curDate){
                    todoListToAdapter.add(task)
                }
            }
            todoListToAdapter.sortBy { it.time }
            adapter.setInfo(todoListToAdapter)
        }
    }
}