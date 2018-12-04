package com.atinem.starfishcollector

import com.badlogic.gdx.Game
import com.badlogic.gdx.scenes.scene2d.Stage

abstract class GameBase : Game() {
    protected lateinit var mainStage: Stage
    protected lateinit var uiStage: Stage

    init {
        game = this
    }

    companion object {
        protected var game: GameBase? = null

        fun setActiveScreen(screen: BaseScreen) = game?.setScreen(screen)
    }
}