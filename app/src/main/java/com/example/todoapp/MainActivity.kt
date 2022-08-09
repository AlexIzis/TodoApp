package com.example.todoapp

import android.app.Activity
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

        /** Инициализация необходимых переменных */
        val calendar: CalendarView = findViewById(R.id.calendarView)
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = TAdapter() /** Адаптер RecyclerView */
        val fullTodoList = mutableListOf<Task>() /** Массив для хранения всего списка заметок */
        val todoListToAdapter = mutableListOf<Task>() /** Массив с отобранными по дню заметками,
                                                    который будет передан в адаптер */

        /** Заполнение массива заметок для проверки работоспособности программы */
        fullTodoList.add(Task(1,"1.7.2022", "1.7.2022", "14:00",
            "one", "hello"))
        fullTodoList.add(Task(2,"2.7.2022", "2.7.2022", "15:00",
            "two", "hello"))
        fullTodoList.add(Task(3,"2.7.2022", "2.7.2022", "14:00",
            "three", "hello"))
        fullTodoList.add(Task(4,"1.7.2022", "1.7.2022", "12:31",
            "four", "hello"))


        /** Настройка RecyclerView */
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        /** По нажатию на заметку выполняется передача данных в отдельное activity и его запуск*/
        adapter.onItemClick = { Task ->
            val intent = Intent(this, ShowTodoActivity::class.java)
            intent.putExtra("id", Task.id)
            intent.putExtra("name", Task.name)
            intent.putExtra("start", Task.dateStart)
            intent.putExtra("finish", Task.dateFinish)
            intent.putExtra("time", Task.time)
            intent.putExtra("desc", Task.description)
            startActivity(intent)
        }

        /** Получение данных из activity, которое отвечает за сбор информации о новой заметке,
         *  и её создание */
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

        /** Слушатель нажатия кнопки добавления заметки
         * getResult.launch аналог startActivityForResult */
        fab.setOnClickListener{
            val intent = Intent(this, CreateTodoActivity::class.java)
            getResult.launch(intent)
        }

        /** Слушатель нажатия на дату в календаре
         * Происходит отбор заметок выбранной даты в отдельный массив, его сортировка по времени
         * и отправка в адаптер для отображения */
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val curDate = "$dayOfMonth.$month.$year"
            todoListToAdapter.clear()
            for (task in fullTodoList){
                if (task.dateStart == curDate){
                    todoListToAdapter.add(task)
                }
            }
            todoListToAdapter.sortBy { it.time }
            adapter.setInfo(todoListToAdapter)
        }
    }
}