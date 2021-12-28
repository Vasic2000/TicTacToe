package ru.vasic2000.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

private var backPressedTime: Long = 0
private lateinit var backToast: Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
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
            return
        } else {
            backToast = Toast.makeText(this, "Press Back to exit again", Toast.LENGTH_SHORT)
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun GameStart() {
        TODO("Not yet implemented")
    }
}