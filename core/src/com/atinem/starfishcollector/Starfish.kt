package com.atinem.starfishcollector

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions


class Starfish(x: Float, y: Float, stage: Stage) : BaseActor(x, y, stage) {

    var isCollected: Boolean = false

    init {
        loadTexture("starfish.png")

        val spin = Actions.rotateBy(30f, 1f)
        this.addAction(Actions.forever(spin))

        setBoundaryPolygon(8)
    }

    fun collect(){
        isCollected = true
        clearActions()
        addAction(Actions.fadeOut(1f))
        addAction(Actions.after(Actions.removeActor()))
    }
}

