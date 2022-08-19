package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * Activity запускается при нажатии кнопки создания заметки
 * Происходит сбор информации о заметке и передача его в главное activity */
class CreateTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_todo_activity)

        /** Инициализация необходимых переменных */
        val nameView: EditText = findViewById(R.id.editTextTextPersonName)
        val descView: EditText = findViewById(R.id.editTextTextPersonName2)
        val timeView: EditText = findViewById(R.id.editTextTime)
        val dateView: EditText = findViewById(R.id.editTextDate)
        val button: Button = findViewById(R.id.buttonCreate)
        val buttonBack: Button = findViewById(R.id.buttonBack)

        /** Создание шаблонов для проверки даты и времени */
        val timeRegex = Regex("[0-1][0-9]:[0-5][0-9]")
        val dateRegex = Regex("""\w\.\w\.\d{4}""")

        /** Слушатель кнопки создания заметки
         * Получение данных с полей ввода и отправка их в клавное activity */
        button.setOnClickListener{
            val name = nameView.text.toString()
            val desc = descView.text.toString()
            val time = timeView.text.toString()
            val date = dateView.text.toString()
            if (time.matches(timeRegex) && date.matches(dateRegex)){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("crName", name)
                intent.putExtra("crDesc", desc)
                intent.putExtra("crTime", time)
                intent.putExtra("crDate", date)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, проверьте введённые значения",
                    Toast.LENGTH_LONG).show()
            }
        }

        /** Слушатель кнопки возвращения в MainActivity */
        buttonBack.setOnClickListener {
            finish()
        }
    }
}