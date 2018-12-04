package com.atinem.starfishcollector

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage

abstract class GameBase : ApplicationAdapter() {
    protected lateinit var mainStage: Stage

    override fun create() {
        mainStage = Stage()
        initialize()
    }

    abstract fun initialize()

    override fun render() {
        val delta = Gdx.graphics.deltaTime
        mainStage.act(delta)
        update(delta)

        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mainStage.draw()
    }

    abstract fun update(delta: Float)
}