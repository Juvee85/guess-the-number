package lopez.juventino.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    var minValue: Int = 0
    var maxValue: Int = 100
    var num:Int = 0
    var won: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val down:Button = findViewById(R.id.down)
        val up:Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText("$num")
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minValue = num
            if (checkLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = "$num"
            } else {
                guessings.setText("Me ganaste :(")
            }
        }

        down.setOnClickListener{
            maxValue = num
            if (checkLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("Me ganaste :(")
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.setText("Adiviné, tu numero es el " + num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility = View.GONE
                resetValues()
            }

        }

    }

    fun checkLimits(): Boolean {
        return minValue != maxValue
    }

    fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }
}