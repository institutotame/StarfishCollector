package com.atinem.starfishcollector

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage


abstract class BaseScreen : Screen, InputProcessor {
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
        val inputMultiplexer = Gdx.input.inputProcessor as InputMultiplexer
        inputMultiplexer.removeProcessor(this)
        inputMultiplexer.removeProcessor(uiStage)
        inputMultiplexer.removeProcessor(mainStage)
    }

    override fun show() {
        val inputMultiplexer = Gdx.input.inputProcessor as InputMultiplexer
        inputMultiplexer.addProcessor(this)
        inputMultiplexer.addProcessor(uiStage)
        inputMultiplexer.addProcessor(mainStage)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun dispose() {

    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }
}