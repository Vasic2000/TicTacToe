package ru.vasic2000.tictactoe

import android.view.View
import java.util.*

class GameLogic(private val mainActivity: MainActivity) : Runnable {

    private val random = Random()

    override fun run() {
        drawLevel()
        gameLoop()
    }

    private fun gameLoop() {
        //        Цикл, пока идёт игра
        while((mainActivity.getGameState() == (GameState.GAME_AI_TURN))or(mainActivity.getGameState() == (GameState.GAME_HUMAN_TURN))) {
            println("inside цикл = " + mainActivity.getGameState())
            if (mainActivity.getGameState() == GameState.GAME_AI_TURN) {
                randomWalk()
            }
        }
        println("Выпал из цикла = " + mainActivity.getGameState())

        if (mainActivity.getGameState() == GameState.GAME_DRAW) {
            mainActivity.gameResultImage.post {
                mainActivity.showResultScreen(GameState.GAME_DRAW)
                mainActivity.gameResultImage.visibility = View.VISIBLE
            }
        }

        if (mainActivity.getGameState() == GameState.GAME_WIN) {
            mainActivity.gameResultImage.post {
                mainActivity.showResultScreen(GameState.GAME_WIN)
                mainActivity.gameResultImage.visibility = View.VISIBLE
            }
        }

        if (mainActivity.getGameState() == GameState.GAME_LOOS) {
            mainActivity.gameResultImage.post {
                mainActivity.showResultScreen(GameState.GAME_LOOS)
                mainActivity.gameResultImage.visibility = View.VISIBLE
            }
        }
        println("Выход совсем = " + mainActivity.getGameState())
    }


    private fun drawLevel() {
        //        Жду и рисую левую вертикальную палку
        try {
            Thread.sleep(250)
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
            Thread.sleep(250)
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
            Thread.sleep(250)
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
            Thread.sleep(250)
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
    private fun isCellValid(x: Int, y: Int): Boolean {
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
        mainActivity.table[x][y] = mainActivity.SIGN_AI

    //  Типа думаю
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        mainActivity.progressBar.post {mainActivity.progressBar.visibility = View.INVISIBLE}
        redrawAI(x,y)
        println(mainActivity.getGameState())
        if(mainActivity.checkWin(mainActivity.SIGN_AI)) mainActivity.setGameState(GameState.GAME_LOOS)
        if(mainActivity.isTableFull()) mainActivity.setGameState(GameState.GAME_DRAW)
    }

    private fun redrawAI(x: Int, y: Int) {
        when (x) {
            0 -> when (y) {
                0 -> {
                    mainActivity.cell11.post {
                    if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                        mainActivity.cell11.setImageResource(R.drawable.cross)
                    else
                        mainActivity.cell11.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell12.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell12.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell12.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell13.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell13.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell13.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
            1 -> when (y) {
                0 -> {
                    mainActivity.cell21.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell21.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell21.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell22.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell22.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell22.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell23.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                        mainActivity.cell23.setImageResource(R.drawable.cross)
                    else
                        mainActivity.cell23.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
            2 -> when (y) {
                0 -> {
                    mainActivity.cell31.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell31.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell31.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell32.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell32.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell32.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell33.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X)
                            mainActivity.cell33.setImageResource(R.drawable.cross)
                        else
                            mainActivity.cell33.setImageResource(R.drawable.zero)
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
        }
    }
}