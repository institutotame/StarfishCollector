package com.atinem.starfishcollector

import com.badlogic.gdx.scenes.scene2d.actions.Actions

class StarfishCollector : GameBase() {

    private lateinit var turtle: Turtle

    private lateinit var starfish: Starfish

    private lateinit var ocean: BaseActor



    override fun initialize() {
        ocean = BaseActor(0f,0f,mainStage)
        ocean.loadTexture("water.jpg")
        ocean.setSize(800f,600f)

        starfish = Starfish(380f, 380f, mainStage)

        turtle = Turtle(20f, 20f, mainStage)
    }

    override fun update(delta: Float) {
        if(turtle.overlaps(starfish) && !starfish.isCollected){
            starfish.collect()

            val whirlpool = Whirlpool(0f,0f, mainStage)
            whirlpool.centerAtActor(starfish)
            whirlpool.setOpacity(0.25f)

            val youWinMessage = BaseActor(0f,0f,mainStage)
            youWinMessage.loadTexture("you-win.png")
            youWinMessage.centerAtPosition(400f, 300f)
            youWinMessage.setOpacity(0f)
            youWinMessage.addAction(Actions.delay(1f))
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1f)))
        }
    }
}
