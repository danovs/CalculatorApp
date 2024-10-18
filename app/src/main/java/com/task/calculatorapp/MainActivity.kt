package com.task.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import com.task.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener {
            clearInput()
        }

        binding.buttonEquals.setOnClickListener {
            calculateResult()
        }

        binding.button0.setOnClickListener { appendToInput("0") }
        binding.button1.setOnClickListener { appendToInput("1") }
        binding.button2.setOnClickListener { appendToInput("2") }
        binding.button3.setOnClickListener { appendToInput("3") }
        binding.button4.setOnClickListener { appendToInput("4") }
        binding.button5.setOnClickListener { appendToInput("5") }
        binding.button6.setOnClickListener { appendToInput("6") }
        binding.button7.setOnClickListener { appendToInput("7") }
        binding.button8.setOnClickListener { appendToInput("8") }
        binding.button9.setOnClickListener { appendToInput("9") }
        binding.buttonDot.setOnClickListener { appendToInput(".") }

        // Операции
        binding.buttonAddition.setOnClickListener { appendToInput("+") }
        binding.buttonSubtraction.setOnClickListener { appendToInput("-") }
        binding.buttonMultiply.setOnClickListener { appendToInput("×") } // Замените на "×"
        binding.buttonDivision.setOnClickListener { appendToInput("÷") } // Замените на "÷"
        binding.buttonBracketLeft.setOnClickListener { appendToInput("(") }
        binding.buttonBracketRight.setOnClickListener { appendToInput(")") }
    }

    private fun appendToInput(value: String) {
        binding.input.text = "${binding.input.text}$value"
    }

    private fun clearInput() {
        binding.input.text = ""
        binding.output.text = ""
    }

    private fun getExpression(): String {
        return binding.input.text.toString()
    }

    private fun calculateResult() {
        val expressionText = getExpression()

        try {
            val expression = Expression(expressionText)
            val result = expression.calculate()

            if (result.isNaN()) {
                // Показ ошибки
                showError()
            } else {
                // Показ результата
                showSuccessResult(result)
            }
        } catch (e: Exception) {
            showError()
        }
    }

    private fun showError() {
        binding.output.text = "Error"
        binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun showSuccessResult(result: Double) {
        val decimalFormat = DecimalFormat("0.######")
        binding.output.text = decimalFormat.format(result)
        binding.output.setTextColor(ContextCompat.getColor(this, R.color.purple))
    }
}