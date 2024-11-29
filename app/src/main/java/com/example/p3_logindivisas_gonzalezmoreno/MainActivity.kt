package com.example.p3_logindivisas_gonzalezmoreno
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputAmount = findViewById<EditText>(R.id.editTextAmount)
        val currencySpinner = findViewById<Spinner>(R.id.spinnerCurrency)
        val convertButton = findViewById<Button>(R.id.buttonConvert)
        val resultTextView = findViewById<TextView>(R.id.textViewResult)

        // Configurar el Spinner con opciones de moneda
        val currencies = arrayOf("USD a EUR", "EUR a USD")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = adapter

        convertButton.setOnClickListener {
            val amountText = inputAmount.text.toString()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese un monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDouble()
            val selectedCurrency = currencySpinner.selectedItem.toString()

            val convertedAmount = when (selectedCurrency) {
                "USD a EUR" -> convertUsdToEur(amount)
                "EUR a USD" -> convertEurToUsd(amount)
                else -> 0.0
            }

            val currencySymbol = if (selectedCurrency == "USD a EUR") "€" else "$"
            resultTextView.text = "Monto convertido: $currencySymbol%.2f".format(convertedAmount)
        }
    }

    private fun convertUsdToEur(usd: Double): Double {
        val exchangeRate = 0.85 // Tasa de cambio de USD a EUR
        return usd * exchangeRate
    }

    private fun convertEurToUsd(eur: Double): Double {
        val exchangeRate = 1.18 // Tasa de cambio de EUR a USD
        return eur * exchangeRate
    }
}

