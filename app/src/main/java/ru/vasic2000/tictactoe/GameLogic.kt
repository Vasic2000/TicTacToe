package ru.vasic2000.tictactoe

import android.view.View
import java.util.*

class GameLogic(private val mainActivity: MainActivity) : Runnable {

    val random = Random()

    override fun run() {

        drawLevel()

//        Цикл, пока идёт игра
        while(mainActivity.gameState == GameState.GAME_AI_TURN || mainActivity.gameState == GameState.GAME_HUMAN_TURN) {
            if(mainActivity.gameState == GameState.GAME_AI_TURN) {
//        Когда GameState == GAME_AI_TURN рисует фигуру за компьютер
                randomWalk()
            }
        }

        mainActivity.rightVerticalLineImage.post {
            mainActivity.rightVerticalLineImage.visibility = View.INVISIBLE
        }
        mainActivity.rightVerticalLineShadowImage.post {
            mainActivity.rightVerticalLineShadowImage.visibility = View.INVISIBLE
        }

    }


    private fun drawLevel() {
        //        Жду и рисую левую вертикальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.leftVerticalLineImage.post {
            mainActivity.leftVerticalLineImage.visibility = View.VISIBLE
        }
        mainActivity.leftVerticalLineShadowImage.post {
            mainActivity.leftVerticalLineShadowImage.visibility = View.VISIBLE
        }

//        Жду и рисую правую вертикальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.rightVerticalLineImage.post {
            mainActivity.rightVerticalLineImage.visibility = View.VISIBLE
        }
        mainActivity.rightVerticalLineShadowImage.post {
            mainActivity.rightVerticalLineShadowImage.visibility = View.VISIBLE
        }

//        Жду и рисую верхнюю горизонтальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.topHorizontalLineImage.post {
            mainActivity.topHorizontalLineImage.visibility = View.VISIBLE
        }
        mainActivity.topHorizontalLineShadowImage.post {
            mainActivity.topHorizontalLineShadowImage.visibility = View.VISIBLE
        }

//        Жду и рисую нижнюю горизонтальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.bottomHorizontalLineImage.post {
            mainActivity.bottomHorizontalLineImage.visibility = View.VISIBLE
        }
        mainActivity.bottomHorizontalLineShadowImage.post {
            mainActivity.bottomHorizontalLineShadowImage.visibility = View.VISIBLE
        }
    }

    //  Проверка не занята ли ячейка
    fun isCellValid(x: Int, y: Int): Boolean {
        return mainActivity.isCellValid(x,y)
    }

    //  Случайный ход
    fun randomWalk() {
        var x: Int
        var y: Int
        do {
            x = random.nextInt(3)
            y = random.nextInt(3)
        } while (!isCellValid(x, y))

        mainActivity.progressBar.post {mainActivity.progressBar.visibility = View.VISIBLE}
        mainActivity.table[x][y] = mainActivity.SIGN_X

//        Типа думаю
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        mainActivity.progressBar.post {mainActivity.progressBar.visibility = View.INVISIBLE}
        redrawCross(x,y)
    }

    fun redrawCross(x: Int, y: Int) {
        when (x) {
            0 -> when (y) {
                0 -> {
                    mainActivity.cell11.post { mainActivity.cell11.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    mainActivity.cell12.post { mainActivity.cell12.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    mainActivity.cell13.post { mainActivity.cell13.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
            }
            1 -> when (y) {
                0 -> {
                    mainActivity.cell21.post { mainActivity.cell21.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    mainActivity.cell22.post { mainActivity.cell22.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    mainActivity.cell23.post { mainActivity.cell23.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
            }
            2 -> when (y) {
                0 -> {
                    mainActivity.cell31.post { mainActivity.cell31.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    mainActivity.cell32.post { mainActivity.cell32.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    mainActivity.cell33.post { mainActivity.cell33.setImageResource(R.drawable.cross) }
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
            }
        }
    }
}

