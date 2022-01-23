package ru.vasic2000.tictactoe

import android.view.View

class LevelDraw(private val mainActivity: MainActivity): Runnable {

    private var table: Array<CharArray>  = Array(3, { CharArray(3) })

    override fun run() {
//        Жду и рисую левую вертикальную палку
        val mainActivity = mainActivity
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
    }
}
