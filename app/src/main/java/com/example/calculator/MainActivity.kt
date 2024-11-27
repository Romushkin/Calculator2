package com.example.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar;
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbarMain: Toolbar

    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button

    private lateinit var resultTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Калькулятор времени"
        toolbarMain.subtitle = "версия 1.0"

        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)
        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDifBTN = findViewById(R.id.buttonDifBTN)
        resultTV = findViewById(R.id.resultTV)

        buttonSumBTN.setOnClickListener(this)
        buttonDifBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstOperandET.text.clear()
                secondOperandET.text.clear()
                resultTV.setTextColor(Color.BLACK)
                resultTV.text = "Результат"
                Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    "Приложение закрыто",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {
        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()) {
            return
        }
        val first = firstOperandET.text.toString().filter { it != ' ' }
        val second = secondOperandET.text.toString().filter { it != ' ' }
        val operation = Operation()
        val result = when (v?.id) {
            R.id.buttonSumBTN -> operation.sum(first, second)
            R.id.buttonDifBTN -> operation.dif(first, second)
            else -> '0'
        }
        resultTV.setTextColor(getColor(R.color.resultColor))
        resultTV.text = result.toString()
        Toast.makeText(
            applicationContext,
            "Результат: ${resultTV.text}",
            Toast.LENGTH_SHORT
        ).show()
    }
}