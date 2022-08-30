package com.example.todoapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * Activity запускается при нажатии кнопки создания заметки
 * Происходит сбор информации о заметке и передача его в главное activity */
class CreateTodoActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_todo_activity)

        /** Отображение кнопки назад на ActionBar */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /** Инициализация необходимых переменных */
        val nameView: EditText = findViewById(R.id.editTextTextPersonName)
        val descView: EditText = findViewById(R.id.editTextTextPersonName2)
        val timeView: TextView = findViewById(R.id.editTextTime)
        val dateView: TextView = findViewById(R.id.editTextDate)
        val button: Button = findViewById(R.id.buttonCreate)
        val buttonTime: Button = findViewById(R.id.buttonTime)
        val buttonDate: Button = findViewById(R.id.buttonDate)

        /** Слушатель кнопки создания заметки
         * Получение данных с полей ввода и отправка их в клавное activity */
        button.setOnClickListener{
            val name = nameView.text.toString()
            val desc = descView.text.toString()
            val time = timeView.text.toString()
            val date = dateView.text.toString()
            if (name.isEmpty()){
                Toast.makeText(this, "Название заметки не заполнено", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("crName", name)
                intent.putExtra("crDesc", desc)
                intent.putExtra("crTime", time)
                intent.putExtra("crDate", date)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        /** Слушатель кнопки выбора времени */
        buttonTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                val time = SimpleDateFormat("HH:mm").format(cal.time)
                timeView.text = time
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

        /** Слушатель кнопки выбора даты */
        buttonDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                val date = "$mDay.$mMonth.$mYear"
                dateView.text = date
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    /** Переопределение функции возвращения в предыдущее activity */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}