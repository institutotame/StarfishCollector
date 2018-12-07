package com.atinem.starfishcollector

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer

abstract class BaseGame : Game() {



    init {
        game = this
    }

    override fun create() {
        val inputMultiplexer = InputMultiplexer()
        Gdx.input.inputProcessor = inputMultiplexer
    }

    companion object {
        protected var game: BaseGame? = null

        fun setActiveScreen(screen: BaseScreen) = game?.setScreen(screen)
    }
}