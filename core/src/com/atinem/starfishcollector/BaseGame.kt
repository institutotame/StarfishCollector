package com.atinem.starfishcollector

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label

abstract class BaseGame : Game() {



    init {
        game = this
    }

    override fun create() {
        val inputMultiplexer = InputMultiplexer()
        Gdx.input.inputProcessor = inputMultiplexer

        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"))

        val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        fontParameters.size = 48
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2f
        fontParameters.borderColor  = Color.BLACK
        fontParameters.borderStraight = true
        fontParameters.minFilter = Texture.TextureFilter.Linear
        fontParameters.magFilter = Texture.TextureFilter.Linear

        val customFont = fontGenerator.generateFont(fontParameters)
        labelStyle.font = customFont
    }

    companion object {
        protected var game: BaseGame? = null

        val labelStyle = Label.LabelStyle()

        fun setActiveScreen(screen: BaseScreen) = game?.setScreen(screen)
    }


}