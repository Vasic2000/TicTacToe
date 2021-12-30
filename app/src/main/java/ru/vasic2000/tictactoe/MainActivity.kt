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
private var gameState: GameState = GameState.FIRST_SCREEN

class MainActivity : AppCompatActivity() {
    lateinit var backgroundImage: ImageView
    private lateinit var easyLevelBtn: Button
    private lateinit var hardLevelBtn: Button
    private lateinit var impossibleLevelBtn: Button
    private lateinit var startGameBtn: Button

    lateinit var leftVerticalLineShadowImage: ImageView
    lateinit var rightVerticalLineShadowImage: ImageView
    lateinit var topHorizontalLineShadowImage: ImageView
    lateinit var bottomHorizontalLineShadowImage: ImageView
    lateinit var leftVerticalLineImage: ImageView
    lateinit var rightVerticalLineImage: ImageView
    lateinit var topHorizontalLineImage: ImageView
    lateinit var bottomHorizontalLineImage: ImageView

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

        leftVerticalLineShadowImage = findViewById(R.id.left_line_shadow)
        rightVerticalLineShadowImage = findViewById(R.id.right_line_shadow)
        topHorizontalLineShadowImage = findViewById(R.id.top_line_shadow)
        bottomHorizontalLineShadowImage = findViewById(R.id.botton_line_shadow)
        leftVerticalLineImage = findViewById(R.id.left_line)
        rightVerticalLineImage = findViewById(R.id.right_line)
        topHorizontalLineImage = findViewById(R.id.top_line)
        bottomHorizontalLineImage = findViewById(R.id.botton_line)

        backgroundImage.setImageResource(R.drawable.firstscreen)
        BtnStart()
    }

    private fun BtnStart() {
        val btnStart = findViewById<Button>(R.id.buttonStart)
        btnStart.setOnClickListener {
            GameStart()
        }
    }

    private fun GameStart() {
        backgroundImage.setImageResource(R.drawable.background)
        easyLevelBtn.visibility = View.VISIBLE
        hardLevelBtn.visibility = View.VISIBLE
        impossibleLevelBtn.visibility = View.VISIBLE
        startGameBtn.visibility = View.INVISIBLE

        BtnEasyGame()
        BtnHardGame()
        BtnImpossibleGame()
    }

    override fun onBackPressed() {
        when(gameState) {
            GameState.FIRST_SCREEN -> {
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
            GameState.SECOND_SCREEN -> {}
            GameState.GAME_SCREEN -> {}
            GameState.GAME_OVER_SCREEN -> {}
        }
    }

    private fun BtnEasyGame() {
        easyLevelBtn.setOnClickListener {
            easyLevelBtn.visibility = View.INVISIBLE
            hardLevelBtn.visibility = View.INVISIBLE
            impossibleLevelBtn.visibility = View.INVISIBLE
            DrawLevel()
        }
    }

    private fun BtnHardGame() {
        hardLevelBtn.setOnClickListener {
            easyLevelBtn.visibility = View.INVISIBLE
            hardLevelBtn.visibility = View.INVISIBLE
            impossibleLevelBtn.visibility = View.INVISIBLE
            DrawLevel()
        }
    }

    private fun BtnImpossibleGame() {
        impossibleLevelBtn.setOnClickListener {
            easyLevelBtn.visibility = View.INVISIBLE
            hardLevelBtn.visibility = View.INVISIBLE
            impossibleLevelBtn.visibility = View.INVISIBLE
            DrawLevel()
        }
    }

    private fun DrawLevel() {
        val levelDraw = LevelDraw(this)
        val thread = Thread(levelDraw)
        thread.start()
    }
}