package com.example.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat

/** Activity запускается при нажатии на заметку
 * Происходит получение данных о заметке, преобразование в json формат и его отображение на экран */
class ShowTodoActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_todo_activity)

        /** Инициализация необходимых переменных */
        val text: TextView = findViewById(R.id.textView)
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val start = intent.getStringExtra("start")
        val finish = intent.getStringExtra("finish")
        val desc = intent.getStringExtra("desc")
        val time = intent.getStringExtra("time")

        /** Перевод даты в формат unix time */
        val dateStart = start?.let { SimpleDateFormat("dd.mm.yyyy").parse(it) }
        val dateFinish = finish?.let { SimpleDateFormat("dd.mm.yyyy").parse(it) }

        val resStr = "ID заметки: $id\n" +
                "Дата начала: ${dateStart?.time}\n" +
                "Дата окончания: ${dateFinish?.time}\n" +
                "Время: $time\n" +
                "Название: $name\n" +
                "Подробное описание: $desc\n"

        /** Отображение информации о заметке */
        text.text = resStr
    }
}