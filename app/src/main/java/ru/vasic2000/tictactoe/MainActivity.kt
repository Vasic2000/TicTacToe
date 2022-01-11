package ru.vasic2000.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.*
import kotlin.system.exitProcess

private var backPressedTime: Long = 0
private lateinit var backToast: Toast
private var gameScreen: GameScreen = GameScreen.FIRST_SCREEN
private var random: Random = Random()

const val SIGN_X = 'X'
const val SIGN_O = 'O'
const val SIGN_EMPTY = '.'

class MainActivity : AppCompatActivity() {

    private lateinit var gameState : GameState
    private lateinit var dificulty: Dificulty
    private lateinit var backgroundImage: ImageView

    private var table: Array<CharArray>  = Array(3, { CharArray(3) })

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
    lateinit var gameResultImage: ImageView

    private lateinit var cell11: ImageView
    private lateinit var cell12: ImageView
    private lateinit var cell13: ImageView
    private lateinit var cell21: ImageView
    private lateinit var cell22: ImageView
    private lateinit var cell23: ImageView
    private lateinit var cell31: ImageView
    private lateinit var cell32: ImageView
    private lateinit var cell33: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        initImages()
        initButtons()
        showFirstScreen()
    }

    private fun initImages() {
        backgroundImage = findViewById(R.id.main_background)
        leftVerticalLineShadowImage = findViewById(R.id.left_line_shadow)
        rightVerticalLineShadowImage = findViewById(R.id.right_line_shadow)
        topHorizontalLineShadowImage = findViewById(R.id.top_line_shadow)
        bottomHorizontalLineShadowImage = findViewById(R.id.bottom_line_shadow)
        leftVerticalLineImage = findViewById(R.id.left_line)
        rightVerticalLineImage = findViewById(R.id.right_line)
        topHorizontalLineImage = findViewById(R.id.top_line)
        bottomHorizontalLineImage = findViewById(R.id.bottom_line)
        gameResultImage = findViewById(R.id.gameResultScreen)
        cell11 = findViewById(R.id.Cell_1_1)
        cell12 = findViewById(R.id.Cell_1_2)
        cell13 = findViewById(R.id.Cell_1_3)
        cell21 = findViewById(R.id.Cell_2_1)
        cell22 = findViewById(R.id.Cell_2_2)
        cell23 = findViewById(R.id.Cell_2_3)
        cell31 = findViewById(R.id.Cell_3_1)
        cell32 = findViewById(R.id.Cell_3_2)
        cell33 = findViewById(R.id.Cell_3_3)
    }

    private fun initButtons() {
        startGameBtn = findViewById(R.id.buttonStart)
        easyLevelBtn = findViewById(R.id.easyLevelBtn)
        hardLevelBtn = findViewById(R.id.hardLevelBtn)
        impossibleLevelBtn = findViewById(R.id.impossibleLevelBtn)

        btnStart()
        btnEasyGame()
        btnHardGame()
        btnImpossibleGame()

        cell11.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(1,1)) {
                table[0][0] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell12.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(1,2)) {
                table[0][1] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell13.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(1,3)) {
                table[0][2] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell21.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(2,1)) {
                table[1][0] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell22.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(2,2)) {
                table[1][1] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell23.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(2,3)) {
                table[1][2] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell21.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(3,1)) {
                table[2][0] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell22.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(3,2)) {
                table[2][1] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }

        cell23.setOnClickListener {
            if(gameState == GameState.GAME_HUMAN_TURN && isCellValid(3,3)) {
                table[2][2] = SIGN_X
                cell11.setImageResource(R.drawable.cross)
                gameState = GameState.GAME_AI_TURN
                //  Проверка, что Human выигрыал
                if (checkWin(SIGN_X))
                    gameState = GameState.GAME_LOOS
                //  Проверка, что переполнено
                if(isTableFull())
                    gameState = GameState.GAME_DRAW
            }
        }
    }

    private fun showFirstScreen() {
        gameScreen = GameScreen.FIRST_SCREEN
        gameState = GameState.GAME_VAIT

        backgroundImage.setImageResource(R.drawable.firstscreen)

        hideGameElements()

        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE
        gameResultImage.visibility = View.INVISIBLE

        startGameBtn.visibility = View.VISIBLE
    }

    private fun showSecondScreen() {
        gameScreen = GameScreen.SECOND_SCREEN
        gameState = GameState.GAME_VAIT

        backgroundImage.setImageResource(R.drawable.background)

        hideGameElements()
        gameResultImage.visibility = View.INVISIBLE

        startGameBtn.visibility = View.INVISIBLE

        easyLevelBtn.visibility = View.VISIBLE
        hardLevelBtn.visibility = View.VISIBLE
        impossibleLevelBtn.visibility = View.VISIBLE
    }

    private fun showGameScreen() {
        gameScreen = GameScreen.GAME_SCREEN

        backgroundImage.setImageResource(R.drawable.background)
        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE
        startGameBtn.visibility = View.INVISIBLE
        gameResultImage.visibility = View.INVISIBLE

        drawLevel()
        mainGameProcess()
    }

    private fun showResultScreen(gameState: GameState) {
        gameScreen = GameScreen.GAME_OVER_SCREEN
        backgroundImage.setImageResource(R.drawable.background)
        hideGameElements()
        gameResultImage.visibility = View.VISIBLE

        when(gameState) {
            GameState.GAME_WIN -> {
                gameResultImage.setImageResource(R.drawable.win)
            }
            GameState.GAME_LOOS -> {
                gameResultImage.setImageResource(R.drawable.loss)
            }
            GameState.GAME_DRAW -> {
                gameResultImage.setImageResource(R.drawable.draw)
            }
        }
    }

//  Повторяющаяся операция спрятать элементы игрового поля
    private fun hideGameElements() {
        leftVerticalLineShadowImage.visibility = View.INVISIBLE
        rightVerticalLineShadowImage.visibility = View.INVISIBLE
        topHorizontalLineShadowImage.visibility = View.INVISIBLE
        bottomHorizontalLineShadowImage.visibility = View.INVISIBLE
        leftVerticalLineImage.visibility = View.INVISIBLE
        rightVerticalLineImage.visibility = View.INVISIBLE
        topHorizontalLineImage.visibility = View.INVISIBLE
        bottomHorizontalLineImage.visibility = View.INVISIBLE
        cell11.visibility = View.INVISIBLE
        cell12.visibility = View.INVISIBLE
        cell13.visibility = View.INVISIBLE
        cell21.visibility = View.INVISIBLE
        cell22.visibility = View.INVISIBLE
        cell23.visibility = View.INVISIBLE
        cell31.visibility = View.INVISIBLE
        cell32.visibility = View.INVISIBLE
        cell33.visibility = View.INVISIBLE
    }

    //    Методы инициации кнопок
    private fun btnStart() {
        val btnStart = findViewById<Button>(R.id.buttonStart)
        btnStart.setOnClickListener {
            showSecondScreen()
        }
    }

    private fun btnEasyGame() {
        easyLevelBtn.setOnClickListener {
            gameState = GameState.GAME_HUMAN_TURN
            dificulty = Dificulty.EASY
            showGameScreen()
        }
    }

    private fun btnHardGame() {
        hardLevelBtn.setOnClickListener {
            gameState = GameState.GAME_HUMAN_TURN
            dificulty = Dificulty.HARD
            showGameScreen()
        }
    }

    private fun btnImpossibleGame() {
        impossibleLevelBtn.setOnClickListener {
            gameState = GameState.GAME_AI_TURN
            dificulty = Dificulty.IMPOSSIBLE
            showGameScreen()
        }
    }

    //    Метод последовательной прорисовки поля, серез ThreadPost
    private fun drawLevel() {
        cell11.setImageDrawable(null)
        cell11.visibility = View.VISIBLE
        cell12.setImageDrawable(null)
        cell12.visibility = View.VISIBLE
        cell13.setImageDrawable(null)
        cell13.visibility = View.VISIBLE
        cell21.setImageDrawable(null)
        cell21.visibility = View.VISIBLE
        cell22.setImageDrawable(null)
        cell22.visibility = View.VISIBLE
        cell23.setImageDrawable(null)
        cell23.visibility = View.VISIBLE
        cell31.setImageDrawable(null)
        cell31.visibility = View.VISIBLE
        cell32.setImageDrawable(null)
        cell32.visibility = View.VISIBLE
        cell33.setImageDrawable(null)
        cell33.visibility = View.VISIBLE

//  С красотой
        val levelDraw = LevelDraw(this)
        val thread = Thread(levelDraw)
        thread.start()

//  Без красоты
//        leftVerticalLineShadowImage.visibility = View.VISIBLE
//        leftVerticalLineImage.visibility = View.VISIBLE
//        rightVerticalLineShadowImage.visibility = View.VISIBLE
//        rightVerticalLineImage.visibility = View.VISIBLE
//        topHorizontalLineShadowImage.visibility = View.VISIBLE
//        topHorizontalLineImage.visibility = View.VISIBLE
//        bottomHorizontalLineShadowImage.visibility = View.VISIBLE
//        bottomHorizontalLineImage.visibility = View.VISIBLE
    }

    //    Непосредственно игра
    private fun mainGameProcess() {
        // игровая логика
        // инициализация таблицы
        initTable()

        when (dificulty) {
            Dificulty.EASY -> {
                easyGame()
            }
            Dificulty.HARD -> {
                hardGame()
            }
            Dificulty.IMPOSSIBLE -> {
                impossibleGame()
            }
        }
        showResultScreen(gameState)
    }


    private fun easyGame() {
        do {
            if (gameState == GameState.GAME_AI_TURN)
                randomWalk()
        } while (gameState == GameState.GAME_HUMAN_TURN || gameState == GameState.GAME_AI_TURN)
    }

    private fun hardGame() {
        gameState = GameState.GAME_WIN
    }

    private fun impossibleGame() {
        gameState = GameState.GAME_LOOS
    }


    //  Инициализация таблицы пустыми ячейками
    private fun initTable() {
        for (row in 0..2) for (col in 0..2)
            table[row][col] = SIGN_EMPTY
    }

    //  Проверка не занята ли ячейка
    fun isCellValid(x: Int, y: Int): Boolean {
        return table[y][x] == SIGN_EMPTY
    }

    //  Случайный ход
    fun randomWalk() {
        var x: Int
        var y: Int
        do {
            x = random.nextInt(3)
            y = random.nextInt(3)
        } while (!isCellValid(x, y))

        table[y][x] = SIGN_O
        redrawCross(x,y)

    //  Проверка, что AI выигрыал
        if (checkWin(SIGN_O))
            gameState = GameState.GAME_LOOS
    //  Проверка, что переполнено
        if(isTableFull())
            gameState = GameState.GAME_DRAW

    }

    private fun redrawCross(x: Int, y: Int) {
        when(x) {
            0 -> when(y) {
                0 -> {
                    cell11.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    cell12.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    cell13.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
            }
            1 -> when(y) {
                0 -> {
                    cell21.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    cell22.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    cell23.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
            }
            2 -> when(y) {
                0 -> {
                    cell31.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    cell32.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    cell33.setImageResource(R.drawable.cross)
                    gameState = GameState.GAME_HUMAN_TURN
                }
            }
        }
    }

    //  Проверка знака на выирыш
    private fun checkWin(dot: Char): Boolean {
        for (i in 0..2)
            if (table[i][0] == dot && table[i][1] == dot && table[i][2] == dot ||
            table[0][i] == dot && table[1][i] == dot && table[2][i] == dot)
                return true

        return table[0][0] == dot && table[1][1] == dot && table[2][2] == dot ||
                table[2][0] == dot && table[1][1] == dot && table[0][2] == dot
    }

    //  Проверка, что таблица переполнилась
    fun isTableFull(): Boolean {
        for (row in 0..2) for (col in 0..2)
            if (table[row][col] == SIGN_EMPTY) return false
        return true
    }

    //    Переопределение кнопки назад
    override fun onBackPressed() {
        when (gameScreen) {
            GameScreen.FIRST_SCREEN -> {
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
            GameScreen.SECOND_SCREEN -> {
                showFirstScreen()
            }
            GameScreen.GAME_SCREEN -> {
                showSecondScreen()
            }
            GameScreen.GAME_OVER_SCREEN -> {
                showSecondScreen()
            }
        }
    }
}