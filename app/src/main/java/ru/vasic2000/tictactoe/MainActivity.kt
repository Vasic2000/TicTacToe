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

private lateinit var backgroundImage : ImageView
private lateinit var easyLevelBtn : Button
private lateinit var hardLevelBtn : Button
private lateinit var impossibleLevelBtn : Button
private lateinit var startGameBtn : Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()

        MakeStartScreen()
    }

    private fun MakeStartScreen() {
        backgroundImage = findViewById(R.id.main_background)
        easyLevelBtn = findViewById(R.id.easyLevelBtn)
        hardLevelBtn = findViewById(R.id.hardLevelBtn)
        impossibleLevelBtn = findViewById(R.id.impossiblrLevelBtn)
        startGameBtn = findViewById(R.id.buttonStart)
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