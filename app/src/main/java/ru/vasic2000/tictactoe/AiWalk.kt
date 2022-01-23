package ru.vasic2000.tictactoe

import android.view.View

class AiWalk(private val mainActivity: MainActivity): Runnable {

    override fun run() {
//        Цикл, пока идёт игра
//        Когда GameState == GAME_AI_TURN рисует фигуру за компьютер
        val mainActivity = mainActivity

        while(mainActivity.gameState == GameState.GAME_HUMAN_TURN || mainActivity.gameState == GameState.GAME_AI_TURN) {
            mainActivity.progressBar.post(Runnable {
                mainActivity.progressBar.visibility = View.VISIBLE
            })
//        Типа думаю
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            mainActivity.progressBar.post(Runnable {
                mainActivity.progressBar.visibility = View.VISIBLE
                mainActivity.randomWalk()
            })
        }
    }
}
