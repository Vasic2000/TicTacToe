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
    }


    private fun drawLevel() {
        //        Жду и рисую левую вертикальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.leftVerticalLineImage.post(Runnable {
            mainActivity.leftVerticalLineImage.visibility = View.VISIBLE
        })
        mainActivity.leftVerticalLineShadowImage.post(Runnable {
            mainActivity.leftVerticalLineShadowImage.visibility = View.VISIBLE
        })

//        Жду и рисую правую вертикальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.rightVerticalLineImage.post(Runnable {
            mainActivity.rightVerticalLineImage.visibility = View.VISIBLE
        })
        mainActivity.rightVerticalLineShadowImage.post(Runnable {
            mainActivity.rightVerticalLineShadowImage.visibility = View.VISIBLE
        })

//        Жду и рисую верхнюю горизонтальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.topHorizontalLineImage.post(Runnable {
            mainActivity.topHorizontalLineImage.visibility = View.VISIBLE
        })
        mainActivity.topHorizontalLineShadowImage.post(Runnable {
            mainActivity.topHorizontalLineShadowImage.visibility = View.VISIBLE
        })

//        Жду и рисую нижнюю горизонтальную палку
        try {
            Thread.sleep(350)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        mainActivity.bottomHorizontalLineImage.post(Runnable {
            mainActivity.bottomHorizontalLineImage.visibility = View.VISIBLE
        })
        mainActivity.bottomHorizontalLineShadowImage.post(Runnable {
            mainActivity.bottomHorizontalLineShadowImage.visibility = View.VISIBLE
        })
    }

    //  Проверка не занята ли ячейка
    fun isCellValid(x: Int, y: Int): Boolean {
        return mainActivity.table[y][x] == mainActivity.SIGN_EMPTY
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
        mainActivity.table[y][x] = mainActivity.SIGN_0

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
                    mainActivity.cell31.setImageResource(R.drawable.cross)
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                1 -> {
                    mainActivity.cell32.setImageResource(R.drawable.cross)
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
                2 -> {
                    mainActivity.cell33.setImageResource(R.drawable.cross)
                    mainActivity.gameState = GameState.GAME_HUMAN_TURN
                }
            }
        }
    }
}

