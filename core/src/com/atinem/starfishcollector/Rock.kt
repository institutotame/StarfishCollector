package com.atinem.starfishcollector

import com.badlogic.gdx.scenes.scene2d.Stage


class Rock(x: Float, y: Float, stage: Stage) : BaseActor(x,y,stage) {
    init {
        loadTexture("rock.png")
        setBoundaryPolygon(8)
    }
}