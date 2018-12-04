package com.atinem.starfishcollector

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.Stage

class Turtle(x: Float, y: Float, stage: Stage) : BaseActor(x,y,stage) {

    init {
        val fileNames: Array<String> = arrayOf(
                "turtle-1.png",
                "turtle-2.png",
                "turtle-3.png",
                "turtle-4.png",
                "turtle-5.png",
                "turtle-6.png"
        )
        loadAnimationFromFiles(fileNames, 0.1f, true)

        acceleration = ACCELERATION
        maxSpeed = MAX_SPEED
        deceleration = DECELERATION

        setBoundaryPolygon(8)
    }

    override fun act(delta: Float) {
        super.act(delta)
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) accelerateAtAngle(180f)
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) accelerateAtAngle(0f)
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) accelerateAtAngle(90f)
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) accelerateAtAngle(270f)

        applyPhysics(delta)

        isAnimationPaused = !isMoving()

        if(getSpeed() > 0f)
            rotation = getMotionAngle()
    }

    companion object {
        private const val ACCELERATION = 400f
        private const val MAX_SPEED = 100f
        private const val DECELERATION = 400f
    }
}