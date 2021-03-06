package ru.vasic2000.tictactoe

import android.view.View
import java.util.*

class GameLogic(private val mainActivity: MainActivity) : Runnable {

    private val random = Random()

    override fun run() {
        showSignAnimation(mainActivity.SIGN_HUMAN)
        gameLoop()
    }

    private fun showSignAnimation(signHuman: Char) {
        if(signHuman == mainActivity.SIGN_X) {
            mainActivity.cross_select_picture.post {
                mainActivity.sounds.play(mainActivity.soundClick, 1f, 1f, 1, 0, 1f)
                mainActivity.cross_select_picture.setImageResource(R.drawable.sign_cross_press)
                mainActivity.showGameScreen()
            }
            //        Показываю 0,4 секунды кого выбрал
            try {
                Thread.sleep(400)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        else {
            mainActivity.zero_select_picture.post {
                mainActivity.sounds.play(mainActivity.soundClick, 1f, 1f, 1, 0, 1f)
                mainActivity.zero_select_picture.setImageResource(R.drawable.sign_zero_press)
                mainActivity.showGameScreen()
            }
            //        Показываю 0,4 секунды кого выбрал
            try {
                Thread.sleep(400)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        drawLevel()
    }

    private fun gameLoop() {
        //        Цикл, пока идёт игра
        while((mainActivity.getGameState() == (GameState.GAME_AI_TURN))or(mainActivity.getGameState() == (GameState.GAME_HUMAN_TURN))) {
            println("inside цикл = " + mainActivity.getGameState())
            if(mainActivity.getGameState() == GameState.GAME_AI_TURN) {
                when (mainActivity.dificulty) {
                    Dificulty.EASY -> {
                        randomWalk()
                    }
                    Dificulty.MEDIUM -> {
                        mediumWalk()
                    }
                    Dificulty.HARD -> {
                        hardWalk()
                    }
                }
            }
        }
        println("Выпал из цикла = " + mainActivity.getGameState())

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

        if (mainActivity.getGameState() == GameState.GAME_DRAW) {
            mainActivity.gameResultImage.post {
                mainActivity.showResultScreen(GameState.GAME_DRAW)
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



        AI_post(x, y, mainActivity.dificulty)
    }

    //  Ход в уровне Hard
    private fun mediumWalk() {
        if (check1Diagonal()) return
        if (check2Diagonal()) return
        if (checkRows()) return
        if (checkColumns()) return
        randomWalk()
    }

    private fun hardWalk() {
        if (checkForWin1Diagonal()) return
        if (checkForWin2Diagonal()) return
        if (checkForWinRows()) return
        if (checkForWinColumns()) return

        if (check1Diagonal()) return
        if (check2Diagonal()) return
        if (checkRows()) return
        if (checkColumns()) return

        if (firstStep()) return

        randomWalk()
    }

    private fun firstStep(): Boolean {
        if((mainActivity.table[0][0] == mainActivity.SIGN_EMPTY) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_EMPTY) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_EMPTY) &&
            (mainActivity.table[0][2] == mainActivity.SIGN_EMPTY) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_EMPTY)) {
            val cell = random.nextInt(5)
            when (cell) {
                0 -> {
                    AI_post(0, 0, Dificulty.HARD)
                    return true
                }
                1 -> {
                    AI_post(2, 0, Dificulty.HARD)
                    return true
                }
                2 -> {
                    AI_post(1, 1, Dificulty.HARD)
                    return true
                }
                3 -> {
                    AI_post(0, 2, Dificulty.HARD)
                    return true
                }
                4 -> {
                    AI_post(2, 2, Dificulty.HARD)
                    return true
                }
            }
        }
        return false
    }

    private fun AI_post(x: Int, y: Int, dificulty: Dificulty) {
        var longitude: Long
        mainActivity.progressBar.post { mainActivity.progressBar.visibility = View.VISIBLE }
        mainActivity.table[x][y] = mainActivity.SIGN_AI

        //  Типа думаю
        longitude = when(dificulty) {
            Dificulty.EASY -> {
                600
            }
            Dificulty.MEDIUM -> {
                1000
            }
            Dificulty.HARD -> {
                1300
            }
        }
        try {
            Thread.sleep(longitude)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        mainActivity.progressBar.post { mainActivity.progressBar.visibility = View.INVISIBLE }
        redrawAI(x, y)
        println(mainActivity.getGameState())

        when {
            mainActivity.checkWin(mainActivity.SIGN_AI) -> mainActivity.setGameState(GameState.GAME_LOOS)
            mainActivity.checkWin(mainActivity.SIGN_HUMAN) -> mainActivity.setGameState(GameState.GAME_WIN)
            mainActivity.isTableFull() -> mainActivity.setGameState(GameState.GAME_DRAW)
        }
    }

    private fun check1Diagonal(): Boolean {

        if((mainActivity.table[0][0] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][2] != mainActivity.SIGN_AI)) {
            AI_post(2,2, Dificulty.MEDIUM)
            return true
        }

        if((mainActivity.table[0][0] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[1][1] != mainActivity.SIGN_AI)) {
            AI_post(1,1, Dificulty.MEDIUM)
            return true
        }

        if((mainActivity.table[1][1] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[0][0] != mainActivity.SIGN_AI)) {
            AI_post(0,0, Dificulty.MEDIUM)
            return true
        }
        return false
    }

    private fun check2Diagonal(): Boolean {

        if((mainActivity.table[0][2] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][0] != mainActivity.SIGN_AI)) {
            AI_post(2,0, Dificulty.MEDIUM)
            return true
        }

        if((mainActivity.table[0][2] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[1][1] != mainActivity.SIGN_AI)) {
            AI_post(1,1, Dificulty.MEDIUM)
            return true
        }

        if((mainActivity.table[1][1] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_HUMAN) &&
            (mainActivity.table[0][2] != mainActivity.SIGN_AI)) {
            AI_post(0,2, Dificulty.MEDIUM)
            return true
        }
        return false
    }

    private fun checkRows(): Boolean {
        for (row in 0..2) {
            if ((mainActivity.table[row][0] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[row][1] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[row][2] != mainActivity.SIGN_AI)
            ) {
                AI_post(row, 2, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[row][0] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[row][1] != mainActivity.SIGN_AI) &&
                (mainActivity.table[row][2] == mainActivity.SIGN_HUMAN)
            ) {
                AI_post(row  , 1, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[row][0] != mainActivity.SIGN_AI) &&
                (mainActivity.table[row][1] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[row][2] == mainActivity.SIGN_HUMAN)
            ) {
                AI_post(row, 0, Dificulty.MEDIUM)
                return true
            }
        }
        return false
    }

    private fun checkColumns(): Boolean {
        for (col in 0..2) {
            if ((mainActivity.table[0][col] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[1][col] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[2][col] != mainActivity.SIGN_AI)
            ) {
                AI_post(2, col, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[0][col] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[1][col] != mainActivity.SIGN_AI) &&
                (mainActivity.table[2][col] == mainActivity.SIGN_HUMAN)
            ) {
                AI_post(1, col, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[0][col] != mainActivity.SIGN_AI) &&
                (mainActivity.table[1][col] == mainActivity.SIGN_HUMAN) &&
                (mainActivity.table[2][col] == mainActivity.SIGN_HUMAN)
            ) {
                AI_post(0, col, Dificulty.MEDIUM)
                return true
            }
        }
        return false
    }

    private fun checkForWin1Diagonal(): Boolean {

        if((mainActivity.table[0][0] == mainActivity.SIGN_AI) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_EMPTY)) {
            AI_post(2,2, Dificulty.HARD)
            return true
        }

        if((mainActivity.table[0][0] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_AI) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_EMPTY)) {
            AI_post(1,1, Dificulty.HARD)
            return true
        }

        if((mainActivity.table[1][1] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][2] == mainActivity.SIGN_AI) &&
            (mainActivity.table[0][0] == mainActivity.SIGN_EMPTY)) {
            AI_post(0,0, Dificulty.HARD)
            return true
        }
        return false
    }

    private fun checkForWin2Diagonal(): Boolean {

        if((mainActivity.table[0][2] == mainActivity.SIGN_AI) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_EMPTY)) {
            AI_post(2,0, Dificulty.HARD)
            return true
        }

        if((mainActivity.table[0][2] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_AI) &&
            (mainActivity.table[1][1] == mainActivity.SIGN_EMPTY)) {
            AI_post(1,1, Dificulty.HARD)
            return true
        }

        if((mainActivity.table[1][1] == mainActivity.SIGN_AI) &&
            (mainActivity.table[2][0] == mainActivity.SIGN_AI) &&
            (mainActivity.table[0][2] == mainActivity.SIGN_EMPTY)) {
            AI_post(0,2, Dificulty.HARD)
            return true
        }
        return false
    }

    private fun checkForWinRows(): Boolean {
        for (row in 0..2) {
            if ((mainActivity.table[row][0] == mainActivity.SIGN_AI) &&
                (mainActivity.table[row][1] == mainActivity.SIGN_AI) &&
                (mainActivity.table[row][2] == mainActivity.SIGN_EMPTY)
            ) {
                AI_post(row, 2, Dificulty.HARD)
                return true
            } else if ((mainActivity.table[row][0] == mainActivity.SIGN_AI) &&
                (mainActivity.table[row][1] == mainActivity.SIGN_EMPTY) &&
                (mainActivity.table[row][2] == mainActivity.SIGN_AI)
            ) {
                AI_post(row  , 1, Dificulty.HARD)
                return true
            } else if ( (mainActivity.table[row][0] == mainActivity.SIGN_EMPTY) &&
                (mainActivity.table[row][1] == mainActivity.SIGN_AI) &&
                (mainActivity.table[row][2] == mainActivity.SIGN_AI)
            ) {
                AI_post(row, 0, Dificulty.HARD)
                return true
            }
        }
        return false
    }

    private fun checkForWinColumns(): Boolean {
        for (col in 0..2) {
            if ((mainActivity.table[0][col] == mainActivity.SIGN_AI) &&
                (mainActivity.table[1][col] == mainActivity.SIGN_AI) &&
                (mainActivity.table[2][col] == mainActivity.SIGN_EMPTY)
            ) {
                AI_post(2, col, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[0][col] == mainActivity.SIGN_AI) &&
                (mainActivity.table[1][col] == mainActivity.SIGN_EMPTY) &&
                (mainActivity.table[2][col] == mainActivity.SIGN_AI)
            ) {
                AI_post(1, col, Dificulty.MEDIUM)
                return true
            } else if ((mainActivity.table[0][col] == mainActivity.SIGN_EMPTY) &&
                (mainActivity.table[1][col] == mainActivity.SIGN_AI) &&
                (mainActivity.table[2][col] == mainActivity.SIGN_AI)
            ) {
                AI_post(0, col, Dificulty.MEDIUM)
                return true
            }
        }
        return false
    }

    private fun redrawAI(x: Int, y: Int) {
        when (x) {
            0 -> when (y) {
                0 -> {
                    mainActivity.cell11.post {
                        if (mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell11.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        } else {
                            mainActivity.cell11.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell12.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell12.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell12.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell13.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell13.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell13.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
            1 -> when (y) {
                0 -> {
                    mainActivity.cell21.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell21.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell21.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell22.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell22.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell22.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell23.post {
                        if (mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell23.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        } else {
                            mainActivity.cell23.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
            2 -> when (y) {
                0 -> {
                    mainActivity.cell31.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell31.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell31.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                1 -> {
                    mainActivity.cell32.post {
                        if (mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell32.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        } else {
                            mainActivity.cell32.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
                2 -> {
                    mainActivity.cell33.post {
                        if(mainActivity.SIGN_AI == mainActivity.SIGN_X) {
                            mainActivity.cell33.setImageResource(R.drawable.cross)
                            mainActivity.sounds.play(mainActivity.soundCross, 1f, 1f, 1, 0, 1f)
                        }
                        else {
                            mainActivity.cell33.setImageResource(R.drawable.zero)
                            mainActivity.sounds.play(mainActivity.soundRound, 1f, 1f, 1, 0, 1f)
                        }
                    }
                    mainActivity.setGameState(GameState.GAME_HUMAN_TURN)
                }
            }
        }
    }
}