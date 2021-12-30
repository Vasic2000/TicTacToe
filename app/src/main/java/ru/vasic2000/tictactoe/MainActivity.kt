package ru.vasic2000.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlin.system.exitProcess

private var backPressedTime: Long = 0
private lateinit var backToast: Toast

class MainActivity : AppCompatActivity() {
    private val backgroundImage : ImageView = findViewById(R.id.main_background)
    private val easyLevelBtn : Button = findViewById(R.id.easyLevelBtn)
    private val hardLevelBtn : Button = findViewById(R.id.hardLevelBtn)
    private val impossibleLevelBtn : Button = findViewById(R.id.impossiblrLevelBtn)
    private val startGameBtn : Button = findViewById(R.id.buttonStart)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        MakeStartScreen()
    }

    private fun MakeStartScreen() {
        BtnStart()
    }

    private fun BtnStart() {
        val btnStart = findViewById<Button>(R.id.buttonStart)
        btnStart.setOnClickListener {
            GameStart()
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            exitProcess(0)
            return
        } else {
            backToast = Toast.makeText(this, "Press Back to exit again", Toast.LENGTH_SHORT)
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun GameStart() {
        backgroundImage.setImageResource(R.drawable.background)

        startGameBtn.visibility = View.INVISIBLE

        easyLevelBtn.visibility = View.VISIBLE
        hardLevelBtn.visibility = View.VISIBLE
        impossibleLevelBtn.visibility = View.VISIBLE
    }
}