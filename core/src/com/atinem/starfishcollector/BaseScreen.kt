package com.atinem.starfishcollector

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage


abstract class BaseScreen : Screen {
    protected val mainStage = Stage()
    protected val uiStage = Stage()


    init {
        initialize()
    }

    abstract fun initialize()
    abstract fun update(delta: Float)

    override fun render(delta: Float) {
        uiStage.act()
        mainStage.act()

        update(delta)

        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mainStage.draw()
        uiStage.draw()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun dispose() {

    }
}