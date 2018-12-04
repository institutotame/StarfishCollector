package com.atinem.starfishcollector

import com.badlogic.gdx.scenes.scene2d.Stage

class Whirlpool(x: Float, y: Float, stage: Stage) : BaseActor(x,y,stage) {
    init {
        loadAnimationFromSheet("whirlpool.png", 2, 5, 0.1f, false)
    }

    override fun act(delta: Float) {
        super.act(delta)
        if(isAnimationFinished()) remove()
    }
}