package com.example.q1
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    public fun sum(a: Int, b: Int): Int {
        return a+b
    }
    public fun multiply(a: Int, b: Int): Int {
        return a*b
    }
    public fun subtract(a: Int, b: Int): Int {
        return a-b
    }
    public fun divide(a: Int, b: Int): Float {
        return a.toFloat()/b.toFloat()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) {
                v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.button)
        val resultTV: TextView = findViewById(R.id.textResult)
        var flag : String = "Sum"
        val firstNumber = findViewById<EditText>(R.id.num1)
        val secondNumber = findViewById<EditText>(R.id.num2)
        val spinnerVal : Spinner = findViewById(R.id.spSelect)
        val options = arrayOf("Sum","Multiplication","Subtraction","Division")
        spinnerVal.adapter =
            ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )
        spinnerVal.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2:
            Int, p3: Long) {
                flag = options.get(p2)
                val firstNumberString = firstNumber.text.toString()
                val secondNumberString = secondNumber.text.toString()
                val x = firstNumberString.toIntOrNull() ?: 0
                val y = secondNumberString.toIntOrNull() ?: 0
                if(flag =="Sum")
                    resultTV.text = "Sum: "+sum(x,y).toString();
                else
                    resultTV.text = "Product: "+multiply(x,y).toString();
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        button.setOnClickListener{ view ->
            var x: Int = firstNumber.text.toString().toInt();
            var y: Int = secondNumber.text.toString().toInt();

            when(flag){
                "Sum" -> resultTV.text = sum(x,y).toString();
                "Multiplication" -> resultTV.text = multiply(x,y).toString();
                "Subtraction" -> resultTV.text = subtract(x,y).toString();
                "Division" -> resultTV.text = divide(x,y).toString();
            }
        }

        val intent = intent

        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {
                val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                firstNumber.setText(sharedText)
                secondNumber.setText(2.toString())
                if (sharedText != null) {
                    Toast.makeText(this, "5+2=7", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}