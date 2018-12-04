package com.atinem.starfishcollector

import com.badlogic.gdx.scenes.scene2d.actions.Actions

class LevelScreen : BaseScreen() {
    private lateinit var turtle: Turtle
    private var win: Boolean = false

    override fun initialize() {
        val ocean = BaseActor(0f,0f,mainStage)
        ocean.loadTexture("water.jpg")
        ocean.setSize(1200f,900f)
        BaseActor.setWorldBounds(ocean)

        Starfish(400f, 400f, mainStage)
        Starfish(500f, 100f, mainStage)
        Starfish(100f, 450f, mainStage)
        Starfish(200f, 250f, mainStage)

        turtle = Turtle(20f, 20f, mainStage)

        Rock(200f,150f,mainStage)
        Rock(100f,300f,mainStage)
        Rock(300f,350f,mainStage)
        Rock(450f,200f,mainStage)
    }

    override fun update(delta: Float) {
        for(rockActor in BaseActor.getList(mainStage, "Rock"))
            turtle.preventOverlap(rockActor)


        for(starFishActor in BaseActor.getList(mainStage, "Starfish")){
            val starfish = starFishActor as Starfish
            if(turtle.overlaps(starfish) && !(starfish).isCollected){
                starfish.collect()

                val whirlpool = Whirlpool(0f,0f, mainStage)
                whirlpool.centerAtActor(starfish)
                whirlpool.setOpacity(0.25f)
            }
        }

        if(BaseActor.count(mainStage, "Starfish") == 0 && !win){
            win = true
            val youWinMessage = BaseActor(0f,0f,uiStage)
            youWinMessage.loadTexture("you-win.png")
            youWinMessage.centerAtPosition(400f, 300f)
            youWinMessage.setOpacity(0f)
            youWinMessage.addAction(Actions.delay(1f))
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1f)))
        }
    }
}