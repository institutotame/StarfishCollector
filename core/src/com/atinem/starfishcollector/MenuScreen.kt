package com.atinem.starfishcollector

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class MenuScreen : BaseScreen() {
    override fun initialize() {
        val ocean = BaseActor(0f,0f,mainStage)
        ocean.loadTexture("water.jpg")
        ocean.setSize(800f,600f)

        val title = BaseActor(0f,0f,mainStage)
        title.loadTexture("starfish-collector.png")
        title.centerAtPosition(400f,300f)
        title.moveBy(0f,100f)

        val start = BaseActor(0f,0f,mainStage)
        start.loadTexture("message-start.png")
        start.centerAtPosition(400f,300f)
        start.moveBy(0f, -100f)
    }

    override fun update(delta: Float) {
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            StarfishGame.setActiveScreen(LevelScreen())
    }
}