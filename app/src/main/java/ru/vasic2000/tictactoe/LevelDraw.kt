package ru.vasic2000.tictactoe

import android.view.View

class LevelDraw(private val mainActivity: MainActivity): Runnable {

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
    }
}
