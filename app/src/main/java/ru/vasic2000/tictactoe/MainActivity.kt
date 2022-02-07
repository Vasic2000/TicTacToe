package ru.vasic2000.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

private var backPressedTime: Long = 0
private lateinit var backToast: Toast
private var gameScreen: GameScreen = GameScreen.FIRST_SCREEN

class MainActivity : AppCompatActivity() {

    @Volatile
    private lateinit var gameState : GameState

    lateinit var dificulty: Dificulty
    private lateinit var backgroundImage: ImageView
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

    lateinit var zero_select_picture: ImageView
    lateinit var cross_select_picture: ImageView

    lateinit var cell11: ImageView
    lateinit var cell12: ImageView
    lateinit var cell13: ImageView
    lateinit var cell21: ImageView
    lateinit var cell22: ImageView
    lateinit var cell23: ImageView
    lateinit var cell31: ImageView
    lateinit var cell32: ImageView
    lateinit var cell33: ImageView

    lateinit var progressBar: ProgressBar

    var table: Array<CharArray> = Array(3, { CharArray(3) })

    val SIGN_EMPTY = '.'
    val SIGN_X = 'X'
    val SIGN_0 = '0'

    var SIGN_HUMAN = 'X'
    var SIGN_AI= '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        zero_select_picture = findViewById(R.id.zero_select_picture)
        cross_select_picture = findViewById(R.id.cross_select_picture)

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
        hardLevelBtn = findViewById(R.id.mediumLevelBtn)
        impossibleLevelBtn = findViewById(R.id.hardLevelBtn)

        progressBar = findViewById(R.id.progressBar)

        btnStart()
        btnEasyGame()
        btnHardGame()
        btnImpossibleGame()

        crossSelect()
        zeroSelect()
        gameResult()

        cellsOnClickListeners()
    }

    private fun gameResult() {
        gameResultImage.setOnClickListener {
            gameState = GameState.GAME_VAIT
            showSecondScreen()
        }
    }

    private fun zeroSelect() {
        zero_select_picture.setOnClickListener {
            SIGN_HUMAN = SIGN_0
            SIGN_AI = SIGN_X
            startGame()
        }
    }

    private fun crossSelect() {
        cross_select_picture.setOnClickListener {
            SIGN_HUMAN = SIGN_X
            SIGN_AI = SIGN_0
            startGame()
        }
    }

    private fun cellsOnClickListeners() {
        cell11.setOnClickListener {
            if (isCellValid(0, 0) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[0][0] = SIGN_HUMAN
                redrawHuman(0, 0)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell12.setOnClickListener {
            if (isCellValid(0, 1) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[0][1] = SIGN_HUMAN
                redrawHuman(0, 1)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell13.setOnClickListener {
            if (isCellValid(0, 2) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[0][2] = SIGN_HUMAN
                redrawHuman(0, 2)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell21.setOnClickListener {
            if (isCellValid(1, 0) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[1][0] = SIGN_HUMAN
                redrawHuman(1, 0)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell22.setOnClickListener {
            if (isCellValid(1, 1) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[1][1] = SIGN_HUMAN
                redrawHuman(1, 1)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell23.setOnClickListener {
            if (isCellValid(1, 2) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[1][2] = SIGN_HUMAN
                redrawHuman(1, 2)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell31.setOnClickListener {
            if (isCellValid(2, 0) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[2][0] = SIGN_HUMAN
                redrawHuman(2, 0)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell32.setOnClickListener {
            if (isCellValid(2, 1) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[2][1] = SIGN_HUMAN
                redrawHuman(2, 1)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
        cell33.setOnClickListener {
            if (isCellValid(2, 2) && (gameState == GameState.GAME_HUMAN_TURN)) {
                table[2][2] = SIGN_HUMAN
                redrawHuman(2, 2)
                if(checkWin(SIGN_HUMAN)) gameState = GameState.GAME_WIN
                else if(isTableFull()) gameState = GameState.GAME_DRAW
                else gameState = GameState.GAME_AI_TURN
                println(gameState)
            }
        }
    }

    private fun showFirstScreen() {
        gameScreen = GameScreen.FIRST_SCREEN
        gameState = GameState.GAME_VAIT

        backgroundImage.setImageResource(R.drawable.firstscreen)

        zero_select_picture.visibility = View.INVISIBLE
        cross_select_picture.visibility = View.INVISIBLE

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

        zero_select_picture.visibility = View.INVISIBLE
        cross_select_picture.visibility = View.INVISIBLE

        hideGameElements()
        gameResultImage.visibility = View.INVISIBLE

        startGameBtn.visibility = View.INVISIBLE

        easyLevelBtn.visibility = View.VISIBLE
        hardLevelBtn.visibility = View.VISIBLE
        impossibleLevelBtn.visibility = View.VISIBLE
    }

    fun showGameScreen() {
        gameScreen = GameScreen.GAME_SCREEN

        backgroundImage.setImageResource(R.drawable.background)
        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE
        startGameBtn.visibility = View.INVISIBLE
        gameResultImage.visibility = View.INVISIBLE

        zero_select_picture.visibility = View.INVISIBLE
        cross_select_picture.visibility = View.INVISIBLE
    }

    private fun showSignSelectScreen() {
        gameScreen = GameScreen.SIGN_SELECT_SCREEN


        backgroundImage.setImageResource(R.drawable.choosingscreen)
        zero_select_picture.setImageResource(R.drawable.sign_zero)
        cross_select_picture.setImageResource(R.drawable.sign_cross)

        easyLevelBtn.visibility = View.INVISIBLE
        hardLevelBtn.visibility = View.INVISIBLE
        impossibleLevelBtn.visibility = View.INVISIBLE
        startGameBtn.visibility = View.INVISIBLE
        gameResultImage.visibility = View.INVISIBLE

        zero_select_picture.visibility = View.VISIBLE
        cross_select_picture.visibility = View.VISIBLE

    }

    fun showResultScreen(gameState: GameState) {
        gameScreen = GameScreen.GAME_OVER_SCREEN
        backgroundImage.setImageResource(R.drawable.background)
        gameResultImage.visibility = View.VISIBLE

        when(gameState) {
            GameState.GAME_WIN -> {
                gameResultImage.setImageResource(R.drawable.win)
                redrawWinCombination(findWinSheme(SIGN_HUMAN), SIGN_HUMAN)
            }
            GameState.GAME_LOOS -> {
                gameResultImage.setImageResource(R.drawable.loss)
                redrawWinCombination(findWinSheme(SIGN_AI), SIGN_AI)
            }
            GameState.GAME_DRAW -> {
                gameResultImage.setImageResource(R.drawable.draw)
            }
        }
    }

    private fun redrawWinCombination(findWinScheme: WinScheme, winSign: Char) {
        //        Отдельный от UI поток с отрисовкой победителя
        val winSchemeDraw = WinSchemeDraw(this, findWinScheme, winSign)
        val winDrawThread = Thread(winSchemeDraw)
        winDrawThread.start()
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
            showSignSelectScreen()
        }
    }

    private fun btnHardGame() {
        hardLevelBtn.setOnClickListener {
            gameState = GameState.GAME_HUMAN_TURN
            dificulty = Dificulty.MEDIUM
            showSignSelectScreen()
        }
    }

    private fun btnImpossibleGame() {
        impossibleLevelBtn.setOnClickListener {
            gameState = GameState.GAME_AI_TURN
            dificulty = Dificulty.HARD
            showSignSelectScreen()
        }
    }

    //    Метод последовательной прорисовки поля, серез ThreadPost
    private fun startGame() {
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
        initTable()

//        Отдельный от UI поток с логикой игры
        val gameLogic = GameLogic(this)
        val thread = Thread(gameLogic)
        thread.start()
    }

    //  Инициализация таблицы пустыми ячейками
    fun initTable() {
        for (row in 0..2) for (col in 0..2)
            table[row][col] = SIGN_EMPTY
    }

    fun redrawHuman(x: Int, y: Int) {
        when (x) {
            0 -> when (y) {
                0 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell11.setImageResource(R.drawable.cross)
                    else
                        cell11.setImageResource(R.drawable.zero)
                }
                1 -> {
                    if(SIGN_HUMAN == SIGN_X)
                    cell12.setImageResource(R.drawable.cross)
                else
                    cell12.setImageResource(R.drawable.zero)
                }
                2 -> {
                    if(SIGN_HUMAN == SIGN_X)
                    cell13.setImageResource(R.drawable.cross)
                else
                    cell13.setImageResource(R.drawable.zero)
                }
            }

            1 -> when (y) {
                0 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell21.setImageResource(R.drawable.cross)
                    else
                        cell21.setImageResource(R.drawable.zero)
                }
                1 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell22.setImageResource(R.drawable.cross)
                    else
                        cell22.setImageResource(R.drawable.zero)
                }
                2 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell23.setImageResource(R.drawable.cross)
                    else
                        cell23.setImageResource(R.drawable.zero)
                }
            }
            2 -> when (y) {
                0 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell31.setImageResource(R.drawable.cross)
                    else
                        cell31.setImageResource(R.drawable.zero)
                }
                1 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell32.setImageResource(R.drawable.cross)
                    else
                        cell32.setImageResource(R.drawable.zero)
                }
                2 -> {
                    if(SIGN_HUMAN == SIGN_X)
                        cell33.setImageResource(R.drawable.cross)
                    else
                        cell33.setImageResource(R.drawable.zero)
                }
            }
        }
    }

    //  Поиск выигрышной комбинации
    fun findWinSheme(dot: Char): WinScheme {
       if(table[0][0] == dot && table[0][1] == dot && table[0][2] == dot) return WinScheme.row1
        if(table[1][0] == dot && table[1][1] == dot && table[1][2] == dot) return WinScheme.row2
        if(table[2][0] == dot && table[2][1] == dot && table[2][2] == dot) return WinScheme.row3

        if(table[0][0] == dot && table[1][0] == dot && table[2][0] == dot) return WinScheme.col1
        if(table[0][1] == dot && table[1][1] == dot && table[2][1] == dot) return WinScheme.col2
        if(table[0][2] == dot && table[1][2] == dot && table[2][2] == dot) return WinScheme.col3

        if(table[0][0] == dot && table[1][1] == dot && table[2][2] == dot) return WinScheme.d1
        if(table[2][0] == dot && table[1][1] == dot && table[0][2] == dot) return WinScheme.d2

    //  Не должен сюда попадать, для тестов
        else return WinScheme.error
    }

    //  Проверка знака на выирыш
    fun checkWin(dot: Char): Boolean {
        for (i in 0..2)
            if (table[i][0] == dot && table[i][1] == dot && table[i][2] == dot ||
                table[0][i] == dot && table[1][i] == dot && table[2][i] == dot
            )
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

    //  Проверка, что ячейка пуста
    fun isCellValid(x: Int, y: Int): Boolean {
        return table[x][y] == SIGN_EMPTY
    }

    @Synchronized
    fun getGameState() : GameState {
        return gameState
    }

    @Synchronized
    fun setGameState(status : GameState) {
        gameState = status
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
            GameScreen.SIGN_SELECT_SCREEN -> {
                showSecondScreen()
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