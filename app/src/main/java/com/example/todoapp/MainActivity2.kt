package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val text: TextView = findViewById(R.id.textView)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val start = intent.getStringExtra("start")
        val finish = intent.getStringExtra("finish")
        val desc = intent.getStringExtra("desc")


        val res_str = JSONObject()
        res_str.put("id", id)
        res_str.put("date_start", start)
        res_str.put("date_finish", finish)
        res_str.put("name", name)
        res_str.put("description", desc)

        text.text = res_str.toString(4)
    }
}