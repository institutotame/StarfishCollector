package com.atinem.starfishcollector.desktop

import com.atinem.starfishcollector.StarfishGame
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Starfish Collector"
        config.width = 800
        config.height = 600
        LwjglApplication(StarfishGame(), config)
    }
}
