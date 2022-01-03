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

    lateinit var cell_1_1: ImageView
    lateinit var cell_1_2: ImageView
    lateinit var cell_1_3: ImageView
    lateinit var cell_2_1: ImageView
    lateinit var cell_2_2: ImageView
    lateinit var cell_2_3: ImageView
    lateinit var cell_3_1: ImageView
    lateinit var cell_3_2: ImageView
    lateinit var cell_3_3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        InitImages()
        InitButtons()
        ShowFirstScreen()
    }

    private fun InitImages() {
        backgroundImage = findViewById(R.id.main_background)
        leftVerticalLineShadowImage = findViewById(R.id.left_line_shadow)
        rightVerticalLineShadowImage = findViewById(R.id.right_line_shadow)
        topHorizontalLineShadowImage = findViewById(R.id.top_line_shadow)
        bottomHorizontalLineShadowImage = findViewById(R.id.bottom_line_shadow)
        leftVerticalLineImage = findViewById(R.id.left_line)
        rightVerticalLineImage = findViewById(R.id.right_line)
        topHorizontalLineImage = findViewById(R.id.top_line)
        bottomHorizontalLineImage = findViewById(R.id.bottom_line)
        cell_1_1 = findViewById(R.id.Cell_1_1)
        cell_1_2 = findViewById(R.id.Cell_1_2)
        cell_1_3 = findViewById(R.id.Cell_1_3)
        cell_2_1 = findViewById(R.id.Cell_2_1)
        cell_2_2 = findViewById(R.id.Cell_2_2)
        cell_2_3 = findViewById(R.id.Cell_2_3)
        cell_3_1 = findViewById(R.id.Cell_3_1)
        cell_3_2 = findViewById(R.id.Cell_3_2)
        cell_3_3 = findViewById(R.id.Cell_3_3)
    }

    private fun InitButtons() {
        startGameBtn = findViewById(R.id.buttonStart)
        easyLevelBtn = findViewById(R.id.easyLevelBtn)
        hardLevelBtn = findViewById(R.id.hardLevelBtn)
        impossibleLevelBtn = findViewById(R.id.impossibleLevelBtn)

        BtnStart()
        BtnEasyGame()
        BtnHardGame()
        BtnImpossibleGame()
    }

    private fun ShowFirstScreen() {
        gameState = GameState.FIRST_SCREEN

        backgroundImage.setImageResource(R.drawable.firstscreen)
        leftVerticalLineShadowImage.visibility = View.INVISIBLE
        rightVerticalLineShadowImage.visibility = View.INVISIBLE
        topHorizontalLineShadowImage.visibility = View.INVISIBLE
        bottomHorizontalLineShadowImage.visibility = View.INVISIBLE
        leftVerticalLineImage.visibility = View.INVISIBLE
        rightVerticalLineImage.visibility = View.INVISIBLE
        topHorizontalLineImage.visibility = View.INVISIBLE
        bottomHorizontalLineImage.visibility = View.INVISIBLE

        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE

        startGameBtn.visibility = View.VISIBLE
    }

    private fun ShowSecondScreen() {
        gameState = GameState.SECOND_SCREEN

        backgroundImage.setImageResource(R.drawable.background)
        leftVerticalLineShadowImage.visibility = View.INVISIBLE
        rightVerticalLineShadowImage.visibility = View.INVISIBLE
        topHorizontalLineShadowImage.visibility = View.INVISIBLE
        bottomHorizontalLineShadowImage.visibility = View.INVISIBLE
        leftVerticalLineImage.visibility = View.INVISIBLE
        rightVerticalLineImage.visibility = View.INVISIBLE
        topHorizontalLineImage.visibility = View.INVISIBLE
        bottomHorizontalLineImage.visibility = View.INVISIBLE

        startGameBtn.visibility = View.INVISIBLE

        easyLevelBtn.visibility = View.VISIBLE
        hardLevelBtn.visibility = View.VISIBLE
        impossibleLevelBtn.visibility = View.VISIBLE
    }

    private fun ShowGameScreen() {
        gameState = GameState.GAME_SCREEN

        backgroundImage.setImageResource(R.drawable.background)
        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE
        startGameBtn.visibility = View.INVISIBLE
        DrawLevel()
    }

    //    Методы инициации кнопок
    private fun BtnStart() {
        val btnStart = findViewById<Button>(R.id.buttonStart)
        btnStart.setOnClickListener {
            ShowSecondScreen()
        }
    }

    private fun BtnEasyGame() {
        easyLevelBtn.setOnClickListener {
            ShowGameScreen()
        }
    }

    private fun BtnHardGame() {
        hardLevelBtn.setOnClickListener {
            ShowGameScreen()
        }
    }

    private fun BtnImpossibleGame() {
        impossibleLevelBtn.setOnClickListener {
            ShowGameScreen()
        }
    }

    //    Метод последовательной прорисовки поля, серез ThreadPost
    private fun DrawLevel() {
        val levelDraw = LevelDraw(this)
        val thread = Thread(levelDraw)
        thread.start()
    }

    //    Переопределение кнопки назад
    override fun onBackPressed() {
        when (gameState) {
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
            GameState.SECOND_SCREEN -> {
                ShowFirstScreen()
            }
            GameState.GAME_SCREEN -> {
                ShowSecondScreen()
            }
            GameState.GAME_OVER_SCREEN -> {
                ShowSecondScreen()
            }
        }
    }
}