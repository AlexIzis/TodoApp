package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val nameView: EditText = findViewById(R.id.editTextTextPersonName)
        val descView: EditText = findViewById(R.id.editTextTextPersonName2)
        val timeView: EditText = findViewById(R.id.editTextTime)
        val dateView: EditText = findViewById(R.id.editTextDate)
        val testView: TextView = findViewById(R.id.textView2)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener{
            val name = nameView.getText().toString()
            val desc = descView.getText().toString()
            val time = timeView.getText().toString()
            val date = dateView.getText().toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("crName", name)
            intent.putExtra("crDesc", desc)
            intent.putExtra("crTime", time)
            intent.putExtra("crDate", date)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}