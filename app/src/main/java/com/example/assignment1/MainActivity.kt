package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.mainButton)
        val kilogramsPounds: EditText = findViewById(R.id.firstMeasurement)
        val metersInches: EditText = findViewById(R.id.secondMeasurement)
        val finalResult: TextView = findViewById(R.id.finalResult)


        val spinnerVal: Spinner = findViewById(R.id.unitsSpinner)
        var options = arrayOf("Kilograms/meters", "pounds/inches")
        spinnerVal.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        var selectedOption = "Kilograms/meters"

        button.setOnClickListener { view ->
            var inputtedWeight: Double = kilogramsPounds.text.toString().toDouble();
            var inputtedHeight: Double = metersInches.text.toString().toDouble();

            if (selectedOption == "Kilograms/meters")
                finalResult.text = firstOption(inputtedWeight, inputtedHeight).toString();
            else if(selectedOption == "pounds/inches")
                finalResult.text = secondOption(inputtedWeight, inputtedHeight).toString();
        }

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedOption = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }


        }
    }
}

public fun firstOption(weight: Double, height: Double): String{
    return getBmiCategory((weight / (height * height)))
}

public fun secondOption(weight: Double, height: Double): String {
    return getBmiCategory((703 * weight) / (height * height))
}

public fun getBmiCategory(bmi: Double): String {
    val printOutBMI: Double = String.format("%.2f", bmi).toDouble();
    return when {
        bmi < 18.5 -> "≈$printOutBMI you are Underweight"
        bmi < 25.0 -> "≈$printOutBMI you are Normal weight"
        bmi < 30.0 -> "≈$printOutBMI you are Overweight"
        else -> "≈$printOutBMI you are Obese"
    }
}

