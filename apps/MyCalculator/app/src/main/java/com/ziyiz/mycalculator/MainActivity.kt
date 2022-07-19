package com.ziyiz.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textViewInput: TextView? = null
    private var isLastDigitNumeric: Boolean = false
    private var isLastDigitDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.textViewInput)
    }

    fun onDigit(view: View) {
        textViewInput?.append((view as Button).text)
        isLastDigitNumeric = true
        isLastDigitDot = false
    }

    fun onClear(view: View) {
        textViewInput?.text = ""
    }

    fun onOperator(view: View) {
        textViewInput?.text?.let {
            if (isLastDigitNumeric && !isOperatorAdded(it.toString())) {
                textViewInput?.append((view as Button).text)
                isLastDigitNumeric = false
                isLastDigitDot = false
            }
        }
    }

    fun onDecimalPoint(view: View) {
        if (isLastDigitNumeric && !isLastDigitDot) {
            textViewInput?.append(".")
            isLastDigitNumeric = false
            isLastDigitDot = true
        }
    }

    fun onEqual(view: View) {
        if (isLastDigitNumeric) {
            var value = textViewInput?.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                if (value.contains("-")) {
                    val splitValue = value.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() - two.toDouble()
                    textViewInput?.text = removeZeroAfterDot(result.toString())
                } else if (value.contains("+")) {
                    val splitValue = value.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    textViewInput?.text = removeZeroAfterDot(result.toString())
                } else if (value.contains("*")) {
                    val splitValue = value.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    textViewInput?.text = removeZeroAfterDot(result.toString())
                } else if (value.contains("/")) {
                    val splitValue = value.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    textViewInput?.text = removeZeroAfterDot(result.toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}