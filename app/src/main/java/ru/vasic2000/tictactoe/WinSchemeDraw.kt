package ru.vasic2000.tictactoe

class WinSchemeDraw (private val mainActivity: MainActivity, winScheme : WinScheme, winSimbol : Char) : Runnable {
    private val winScheme = winScheme
    private val winSign = winSimbol

    override fun run() {
        try {
            Thread.sleep(600)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        when (winScheme) {
            WinScheme.row1 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell12.post {
                        mainActivity.cell12.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell12.post {
                        mainActivity.cell12.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.row2 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell21.post {
                        mainActivity.cell21.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell23.post {
                        mainActivity.cell23.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell21.post {
                        mainActivity.cell21.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell23.post {
                        mainActivity.cell23.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.row3 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell32.post {
                        mainActivity.cell32.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell32.post {
                        mainActivity.cell32.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.col1 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell21.post {
                        mainActivity.cell21.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell21.post {
                        mainActivity.cell21.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.col2 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell12.post {
                        mainActivity.cell12.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell32.post {
                        mainActivity.cell32.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell12.post {
                        mainActivity.cell12.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell32.post {
                        mainActivity.cell32.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.col3 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell23.post {
                        mainActivity.cell23.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell23.post {
                        mainActivity.cell23.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.d1 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell11.post {
                        mainActivity.cell11.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell33.post {
                        mainActivity.cell33.setImageResource(R.drawable.zero_win)
                    }
                }
            }

            WinScheme.d2 -> {
                if (winSign == mainActivity.SIGN_X) {
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.cross_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.cross_win)
                    }
                } else {
                    mainActivity.cell13.post {
                        mainActivity.cell13.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell22.post {
                        mainActivity.cell22.setImageResource(R.drawable.zero_win)
                    }
                    try {
                        Thread.sleep(600)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    mainActivity.cell31.post {
                        mainActivity.cell31.setImageResource(R.drawable.zero_win)
                    }
                }
            }
        }
        println("Отрисовал победителя " + winSign + " и выхожу " + winScheme)
    }
}
