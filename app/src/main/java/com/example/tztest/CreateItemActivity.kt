package com.example.tztest

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)

        val pulsePicker1: NumberPicker = findViewById(R.id.systolic_number_picker)
        pulsePicker1.minValue = 50
        pulsePicker1.maxValue = 200

        val pulsePicker2: NumberPicker = findViewById(R.id.diastolic_number_picker)
        pulsePicker2.minValue = 20
        pulsePicker2.maxValue = 200

        val pulsePicker3: NumberPicker = findViewById(R.id.pulse_number_picker)
        pulsePicker3.minValue = 20
        pulsePicker3.maxValue = 200

        val buttonBack: ImageButton = findViewById(R.id.button_back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            this.startActivity(intent)
        }

        val dateButton: Button = findViewById(R.id.dateButton)
        val timeButton: Button = findViewById(R.id.timeButton)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateButton.text =  dateFormat.format(Calendar.getInstance().time)

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeButton.text =  timeFormat.format(Calendar.getInstance().time)

        setDate()
        setTime()

        saveButton()
    }

    private fun saveButton() {
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val systolic: NumberPicker = findViewById(R.id.systolic_number_picker)
            val diastolic: NumberPicker = findViewById(R.id.diastolic_number_picker)
            val pulse: NumberPicker = findViewById(R.id.pulse_number_picker)
            val date: Button = findViewById(R.id.dateButton)
            val time: Button = findViewById(R.id.timeButton)

            ObjectStorage.addItem(Item(0, systolic.value, diastolic.value, pulse.value, date.text.toString(), time.text.toString()))

            Toast.makeText(this, "Entry added" , Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java )
            this.startActivity(intent)

        }
    }

    private fun setTime() {
        val timeButton: Button = findViewById(R.id.timeButton)

        // Слушатель нажатия на кнопку выбора времени
        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Создание TimePickerDialog и установка слушателя выбора времени
            val timePickerDialog = TimePickerDialog(this,
                { view: TimePicker, selectedHour: Int, selectedMinute: Int ->
                    // Обработка выбранного времени с правильным форматом
                    val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    timeButton.text = selectedTime
                    Toast.makeText(this, "Выбранное время: $selectedTime", Toast.LENGTH_SHORT).show()
                },
                hour,
                minute,
                true // Включение 24-часового формата времени
            )

            // Отображение TimePickerDialog
            timePickerDialog.show()
        }
    }

    private fun setDate() {
        val dateButton: Button = findViewById(R.id.dateButton)

        // Инициализация DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Форматирование выбранной даты
                val selectedDate = formatDate(dayOfMonth, month + 1, year)
                dateButton.text = selectedDate
                Toast.makeText(this, "Выбранная дата: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            // Установка текущей даты в качестве даты по умолчанию
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )

        // Слушатель нажатия на кнопку выбора даты
        dateButton.setOnClickListener {
            // Отображение DatePickerDialog
            datePickerDialog.show()
        }
    }

    private fun formatDate(day: Int, month: Int, year: Int): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return dateFormat.format(calendar.time)
    }
}