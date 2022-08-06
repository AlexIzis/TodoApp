package com.example.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject
import java.text.SimpleDateFormat

/** Activity запускается при нажатии на заметку
 * Происходит получение данных о заметке, преобразование в json формат и его отображение на экран */
class MainActivity2 : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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

        /** Создание json объекта заметки */
        val resStr = JSONObject()
        resStr.put("id", id)
        if (dateStart != null) {
            resStr.put("date_start", dateStart.time)
        }
        if (dateFinish != null) {
            resStr.put("date_finish", dateFinish.time)
        }
        resStr.put("time", time)
        resStr.put("name", name)
        resStr.put("description", desc)

        /** Отображение информации о заметке */
        text.text = resStr.toString(4)
    }
}